package cloverfox.github.demo.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import javax.ejb.Singleton;

@Singleton
public class S3ClientConfigurer{

    public AmazonS3 configureS3Credentials() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2)
                .build();
    }


}
