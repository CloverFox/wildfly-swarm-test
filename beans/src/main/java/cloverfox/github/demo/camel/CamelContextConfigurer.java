package cloverfox.github.demo.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.NamingException;

@Singleton
@Startup
public class CamelContextConfigurer {
    @EJB
    private CamelContextFactory camelContextFactory;

    @PostConstruct
    public void configureCamelContext() {
        System.out.println("CREATING CAMEL CONTEXT");
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
                        .to("ejb:Hello?method=printHi");
            }
        };
        if (camelContext != null) {
            try {
                camelContext.addRoutes(route);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
