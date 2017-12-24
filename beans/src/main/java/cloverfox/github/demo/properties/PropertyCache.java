package cloverfox.github.demo.properties;

import org.ehcache.UserManagedCache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.util.Optional;

import static org.ehcache.config.builders.UserManagedCacheBuilder.newUserManagedCacheBuilder;

@Singleton
public class PropertyCache {

    private UserManagedCache<String,String> cache;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void setupCache() {
        cache = newUserManagedCacheBuilder(String.class, String.class)
                .identifier("data-cache")
                .build(true);
        System.out.println("Cache setup is done");
    }

    public Optional<String> getFromCache(String itemName){
        return Optional.ofNullable(cache.get(itemName));
    }

    public void putToCache(String itemName, String itemValue){
        cache.put(itemName, itemValue);
    }

    @PreDestroy
    void tearDown(){
        cache.clear();
        cache.close();
    }
}
