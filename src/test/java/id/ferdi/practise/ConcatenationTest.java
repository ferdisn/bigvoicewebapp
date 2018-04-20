package id.ferdi.practise;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class ConcatenationTest {

    @Ignore
    @Test
    public void testConcat() {
        Concatenation myConcat = new Concatenation();
        String result = myConcat.concat("Hello", "World");
        assertEquals("HelloWorld", result);
    }

}
