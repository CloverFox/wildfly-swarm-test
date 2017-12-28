package cloverfox.github.demo.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.NamingException;

@Singleton
@Startup
@Slf4j
public class CamelContextConfigurer {
    @EJB
    private CamelContextFactory camelContextFactory;

    @PostConstruct
    public void configureCamelContext() {
        log.debug("creating camel context");
        CamelContext camelContext = null;
        try {
            camelContext = camelContextFactory.createCamelContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        RouteBuilder route = new RouteBuilder() {
            @Override
            public void configure() {
                //for single file
                // from("file://C:/in/?fileName=MyFile.txt&charset=utf-8")
                from("file://C:/in/")
                        .to("ejb:S3Uploader?method=upload");
            }
        };
        if (camelContext != null) {
            try {
                camelContext.addRoutes(route);
                camelContext.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
