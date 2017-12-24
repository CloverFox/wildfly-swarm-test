package cloverfox.github.demo.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.component.ejb.EjbComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.ejb.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
public class CamelContextFactory {
    public CamelContext createCamelContext() throws NamingException {
        CamelContext camelContext = new DefaultCamelContext();

        EjbComponent ejb = new EjbComponent();

        // create a context with java:module and add it to the EjbComponent
        // this means we can just to "from:ejb:Bean?method"
        Context ctx = (Context) new InitialContext().lookup("java:module");

        ejb.setContext(ctx);

        camelContext.addComponent("ejb", ejb);

        return camelContext;
    }
}