package cloverfox.github.demo.camel;

import cloverfox.github.demo.s3.S3ClientConfigurer;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.component.ejb.EjbComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Singleton
@Slf4j
public class CamelContextFactory {

    @EJB
    private S3ClientConfigurer s3ClientConfigurer;


    public CamelContext createCamelContext() throws NamingException {
        CamelContext camelContext = new DefaultCamelContext();

        EjbComponent ejb = new EjbComponent();

        // create a context with java:module and add it to the EjbComponent
        // this means we can just to "from:ejb:Bean?method"
        Context ctx = (Context) new InitialContext().lookup("java:module");

        ejb.setContext(ctx);

        camelContext.addComponent("ejb", ejb);

        AmazonS3 s3Credentials = s3ClientConfigurer.configureS3Credentials();
        List<Bucket> bucketList = s3Credentials.listBuckets();

        for (Bucket bucket : bucketList) {
            log.info(bucket.getName());
        }

        return camelContext;
    }
}