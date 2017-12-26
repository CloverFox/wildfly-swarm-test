package cloverfox.github.demo;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@Slf4j
public class Hello {

    private int i = 0;

    public String returnHi(){
        i = ++i;
        printHi(i);
        return "HI" + i;
    }

    private void printHi(int i){
        log.debug("HI " + i + " HAS BEEN CALLED");
    }
}
