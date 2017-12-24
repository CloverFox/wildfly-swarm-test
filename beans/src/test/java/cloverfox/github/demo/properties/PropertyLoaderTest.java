package cloverfox.github.demo.properties;

import org.ini4j.Profile;
import org.ini4j.Wini;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertyLoaderTest {

    @Mock
    private PropertyCache mockPropertyCache;
    @Mock
    private PropertyFileLoader mockPropertyFileLoader;
    @Mock
    private Wini mockIni;
    @Mock
    private Set<String> mockIniKeySet;
    @Mock
    private Profile.Section mockSection;

    @Test
    public void testPropertyLoader(){
        //arrange
        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.propertyCache = mockPropertyCache;
        propertyLoader.propertyFileLoader = mockPropertyFileLoader;

        Optional<Wini> optionalWini= Optional.of(mockIni);

        when(mockPropertyFileLoader.getIniFile(anyString())).thenReturn(optionalWini);
        when(mockIniKeySet.contains("main")).thenReturn(true);
        when(mockIni.keySet()).thenReturn(mockIniKeySet);
        when(mockIni.get("main")).thenReturn(mockSection);
        String[] strings = {"a","b","c"};
        Set<String> mockMainKeySet = new HashSet<>(Arrays.asList(strings));
        when(mockSection.keySet()).thenReturn(mockMainKeySet);

        when(mockIni.get("main", "a")).thenReturn("A");
        when(mockIni.get("main", "b")).thenReturn("B");
        when(mockIni.get("main", "c")).thenReturn("C");

        //act
        propertyLoader.loadProperties();

        //assert
        verify(mockPropertyCache).putToCache("a", "A");
        verify(mockPropertyCache).putToCache("b", "B");
        verify(mockPropertyCache).putToCache("c", "C");
    }
    @Test
    public void testPropertyLoaderFileLoaderFails(){
        //arrange
        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.propertyCache = mockPropertyCache;
        propertyLoader.propertyFileLoader = mockPropertyFileLoader;

        Optional<Wini> optionalWini = Optional.ofNullable(null);
        when(mockPropertyFileLoader.getIniFile(anyString())).thenReturn(optionalWini);

        //act
        propertyLoader.loadProperties();

        //assert
        //find a way of asserting log lines
    }
    @Test
    public void testPropertyLoaderNoMainSection(){
        //arrange
        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.propertyCache = mockPropertyCache;
        propertyLoader.propertyFileLoader = mockPropertyFileLoader;

        Optional<Wini> optionalWini = Optional.of(mockIni);

        when(mockPropertyFileLoader.getIniFile(anyString())).thenReturn(optionalWini);
        //mockito doesn't like this line as its unnecessary, I disagree as we should be sure what the test is doing
        //but fine mockito, have it your way
        //when(mockIniKeySet.contains("main")).thenReturn(false);

        //act
        propertyLoader.loadProperties();

        //assert
        //find a way of asserting log lines
    }

}