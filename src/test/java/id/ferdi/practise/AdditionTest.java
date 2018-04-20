package id.ferdi.practise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AdditionTest {
    private Integer firstNumber;
    private Integer secondNumber;
    private Integer expectedResult;
    private Concatenation concatenation;

    public AdditionTest(Integer prmFirstNumber, Integer prmSecondNumber, Integer prmExpectedResult ) {
        super();
        this.firstNumber = prmFirstNumber;
        this.secondNumber = prmSecondNumber;
        this.expectedResult = prmExpectedResult;
    }

    @Before
    public void initialize() {
        concatenation = new Concatenation();
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                {1, 2, 3}, {11, 22, 33}, {111, 222, 333}, {10, 9, 19}, {100, 9, 109}
        });
    }

    @Test
    public void testAddition() {
        System.out.println("Sum of numbers : " + expectedResult);
        assertEquals(expectedResult, concatenation.add(firstNumber,secondNumber));
    }
}
