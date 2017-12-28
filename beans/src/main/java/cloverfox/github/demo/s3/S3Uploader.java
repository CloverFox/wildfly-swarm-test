package cloverfox.github.demo.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.File;

@Singleton
@Slf4j
public class S3Uploader {

    @EJB
    S3ClientConfigurer s3ClientConfigurer;

    @EJB
    S3PutObjectRequestFactory s3PutObjectRequestFactory;

    private AmazonS3 amazonS3Client;

    @PostConstruct
    void configureS3Credentials(){
        amazonS3Client = s3ClientConfigurer.configureS3Credentials();
    }

    public void upload(Exchange exchange) {
        GenericFile body = (GenericFile) exchange.getIn().getBody();

        File bodyFile = (File) body.getFile();

        String filename=bodyFile.getName();

        log.info("uploading" + filename);

        PutObjectRequest putObjectRequest = s3PutObjectRequestFactory.creatPutRequest(filename, bodyFile);

        amazonS3Client.putObject(putObjectRequest);

    }
}
