package cloverfox.github.demo.properties;

import org.ini4j.Wini;

import javax.ejb.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Singleton
public class PropertyFileLoader {
    public Optional<Wini> getIniFile(String propertyLocation){
        Wini ini = null;
        try {
            ini = new Wini(new File(propertyLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ini);
    }
}
