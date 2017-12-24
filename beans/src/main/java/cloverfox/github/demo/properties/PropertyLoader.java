package cloverfox.github.demo.properties;

import org.ini4j.Wini;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.Optional;

@Singleton
public class PropertyLoader {

    @EJB
    PropertyCache propertyCache;

    @EJB
    PropertyFileLoader propertyFileLoader;

    public void loadProperties(){
        String appDataLocation = System.getProperty("user.home");

        String propertyLocation = appDataLocation + "/.demo/config.ini";
        System.out.println("loading properties from " + propertyLocation);

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
                    System.out.println(name + " = " + property);
                    propertyCache.putToCache(name, property);
                }
            } else {
                System.out.println("could not find main section in ini file");
            }
        } else {
            System.out.println("problem getting ini file");
        }
    }
}
