package JUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void fact() {
        assertEquals(1, JUnit.fact1(0));
        assertEquals(1, JUnit.fact1(1));
        assertEquals(2, JUnit.fact1(2));
        assertEquals(6, JUnit.fact1(3));
        assertEquals(3628800,JUnit.fact1(10));
        assertEquals(2432902008176640000L, JUnit.fact1(20));
    }
    @Test
    void testNegative() {
        //测试成功表示捕获到异常
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                JUnit.fact1(-1);
            }
        } );
        //函数式编程法，不用编写匿名类
        assertThrows(IllegalArgumentException.class, () -> {
            JUnit.fact1(-1);
        });
        assertThrows(ArithmeticException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                JUnit.fact1(21);
            }
        });
    }
}