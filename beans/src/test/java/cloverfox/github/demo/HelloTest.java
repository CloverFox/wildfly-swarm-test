package cloverfox.github.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloTest {

    @Mock
    private Hello hello;

    @Test
    public void testHello(){
        //arrange
        when(hello.returnHi()).thenReturn("testHi");

        //act
        String helloText = hello.returnHi();

        //assert
        assertEquals("testHi", helloText);

    }
}