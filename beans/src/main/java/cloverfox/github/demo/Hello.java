package cloverfox.github.demo;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

@Singleton
@Startup
public class Hello {
    @PostConstruct
    public void hi(){
        System.out.println("HELLO THERE");
    }

    public String returnHi(){
        return "HI";
    }
}
