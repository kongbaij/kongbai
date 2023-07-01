package JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
//错误时会抛出异常
    @Test
    void testFact() {
        assertEquals(1, JUnit.fact(1));
        assertEquals(2, JUnit.fact(2));
        assertEquals(6,JUnit.fact(3));
        assertEquals(36288,JUnit.fact(10));
        assertEquals(2432902008176640000L, JUnit.fact(20));
        System.out.println("123");
    }

}