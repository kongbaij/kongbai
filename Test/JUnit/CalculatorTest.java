package JUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
 Calculator calculator;
    @BeforeEach//初始化，BeforeAll对静态变量
    void setUp() {
        this.calculator=new Calculator();
    }

    @AfterEach//清理资源，AfterAll对静态变量
    void tearDown() {
    this.calculator=null;
    }

    @Test
    void add() {
        assertEquals(100, this.calculator.add(10));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    void sub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }
}