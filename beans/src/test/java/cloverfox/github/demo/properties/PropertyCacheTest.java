package cloverfox.github.demo.properties;

import org.ehcache.Status;
import org.ehcache.UserManagedCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PropertyCacheTest {

    @Test
    public void testCacheCreation() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        PropertyCache propertyCache = new PropertyCache();

        //act
        propertyCache.setupCache();

        //assert
        Field field = propertyCache.getClass().getDeclaredField("cache");
        field.setAccessible(true);
        UserManagedCache o = (UserManagedCache) field.get(propertyCache);

        assertNotNull(o);
        assertEquals(Status.AVAILABLE, o.getStatus());
    }

    @Test
    public void testAddingAndRetrievingToCache(){
        //arrange
        PropertyCache propertyCache = new PropertyCache();

        //act
        propertyCache.setupCache();

        propertyCache.putToCache("a", "A");
        propertyCache.putToCache("b", "B");


        //assert
        Optional<String> a = propertyCache.getFromCache("a");
        Optional<String> b = propertyCache.getFromCache("b");
        assertTrue(a.isPresent());
        assertEquals("A", a.get());
        assertTrue(b.isPresent());
        assertEquals("B", b.get());
    }

    @Test
    public void testCacheTearDown() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        PropertyCache propertyCache = new PropertyCache();

        //act
        propertyCache.setupCache();

        propertyCache.tearDown();

        //assert
        Field field = propertyCache.getClass().getDeclaredField("cache");
        field.setAccessible(true);
        UserManagedCache o = (UserManagedCache) field.get(propertyCache);

        assertEquals(Status.UNINITIALIZED, o.getStatus());
    }

}