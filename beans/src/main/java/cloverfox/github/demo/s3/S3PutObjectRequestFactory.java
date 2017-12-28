package cloverfox.github.demo.s3;

import com.amazonaws.services.s3.model.PutObjectRequest;

import javax.ejb.Singleton;
import java.io.File;

@Singleton
public class S3PutObjectRequestFactory {
    PutObjectRequest creatPutRequest(String filename, File bodyFile){
        return new PutObjectRequest("clover-test", filename, bodyFile);
    }
}
