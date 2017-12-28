package cloverfox.github.demo.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.file.GenericFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class S3UploaderTest {


    @Mock
    private Exchange mockExchange;
    @Mock
    private Message mockMessage;
    @Mock
    private GenericFile mockGenericFile;
    @Mock
    private File mockFile;
    @Mock
    private AmazonS3 mockAmaonS3Client;
    @Mock
    private S3ClientConfigurer mockS3ClientConfigurer;
    @Mock
    private S3PutObjectRequestFactory mockS3PutObjectRequestFactory;
    @Mock
    private PutObjectRequest mockPutObjectRequest;

    private String testFileName = "testfile.filetype";

    @Test
    public void testS3Uploader(){
        //arrange
        S3Uploader s3Uploader = new S3Uploader();

        when(mockS3ClientConfigurer.configureS3Credentials()).thenReturn(mockAmaonS3Client);
        s3Uploader.s3ClientConfigurer = mockS3ClientConfigurer;

        when(mockS3PutObjectRequestFactory.creatPutRequest(testFileName, mockFile)).thenReturn(mockPutObjectRequest);
        s3Uploader.s3PutObjectRequestFactory = mockS3PutObjectRequestFactory;

        when(mockExchange.getIn()).thenReturn(mockMessage);
        when(mockMessage.getBody()).thenReturn(mockGenericFile);
        when(mockGenericFile.getFile()).thenReturn(mockFile);
        when(mockFile.getName()).thenReturn(testFileName);

        //act
        s3Uploader.configureS3Credentials();
        s3Uploader.upload(mockExchange);

        //assert
        verify(mockAmaonS3Client).putObject(mockPutObjectRequest);

    }

}