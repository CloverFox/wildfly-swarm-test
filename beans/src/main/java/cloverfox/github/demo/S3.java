package cloverfox.github.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.util.List;

@Slf4j
@Singleton
@Startup
public class S3 {

    @PostConstruct
    public void uploadFileToS3(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2)
                .build();

        List<Bucket> buckets = s3Client.listBuckets();

        String filename = "sigma.jpg";

        s3Client.putObject(new PutObjectRequest("clover-test", filename, new File("C:/in/" + filename)));
    }
}
