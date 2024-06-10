import org.example.Factorial;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {
    @Test
    public void testFactorialOfZero() {
        assertEquals(Factorial.calculate(0), 1);
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(Factorial.calculate(1), 1);
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(Factorial.calculate(5), 120);
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        String expectedMessage = "Number must be non-negative";

        try {
            Factorial.calculate(-1);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
