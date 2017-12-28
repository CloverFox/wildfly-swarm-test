package cloverfox.github.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.ini4j.Wini;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Optional;

@Singleton
@Slf4j
@Startup
public class PropertyLoader {

    @EJB
    PropertyCache propertyCache;

    @EJB
    PropertyFileLoader propertyFileLoader;

    @PostConstruct
    public void loadProperties(){
        String appDataLocation = System.getProperty("user.home");

        String propertyLocation = appDataLocation + "/.demo/config.ini";
        log.info("loading properties from " + propertyLocation);

        Optional<Wini> iniOptional;
        Wini ini;

        iniOptional = propertyFileLoader.getIniFile(propertyLocation);

        if(iniOptional.isPresent()){
            ini = iniOptional.get();

            // check for a section, section name is case sensitive
            boolean hasMainSection = ini.keySet().contains("main");

            if (hasMainSection) {
                // list name value pairs within a specific section
                for (String name : ini.get("main").keySet()) {
                    String property = ini.get("main", name);
                    propertyCache.putToCache(name, property);
                }
            } else {
                log.warn("could not find main section in ini file");
            }
        } else {
            log.warn("problem getting ini file");
        }
    }
}
