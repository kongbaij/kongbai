package Task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    public void test_String() {
        MyStack<String> myStack=new MyStack<>();
        myStack.push("dhd");
        myStack.push("sdg");
        myStack.push("55");
        assertEquals("55",myStack.pop());
        assertEquals("sdg",myStack.top());
        assertEquals("sdg",myStack.pop());
        assertEquals("dhd",myStack.pop());
        assertTrue(myStack.deque.isEmpty());
    }

    @Test
    void test_Integer() {
        MyStack<Integer> myStack=new MyStack<>();
        myStack.push(55);
        myStack.push(16);
        assertEquals(16,myStack.pop());
        assertEquals(55,myStack.top());
        assertEquals(55,myStack.pop());
        assertTrue(myStack.deque.isEmpty());
    }

    @Test
    void test_Double() {
        MyStack<Double> myStack=new MyStack<>();
        myStack.push(11.6);
        myStack.push(55.32);
        assertEquals(55.32,myStack.pop());
        assertEquals(11.6,myStack.top());
        assertEquals(11.6,myStack.pop());
        assertTrue(myStack.deque.isEmpty());
    }
}