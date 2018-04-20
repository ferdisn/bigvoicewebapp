package id.ferdi.practise;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConcatenationTest2 {

    @Test
    public void concat() {
        Concatenation myConcat = new Concatenation();
        String result = myConcat.concat("Ferdi", "Saptanera");
        assertEquals("FerdiSaptanera", result);
    }
}