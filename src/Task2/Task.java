package Task2;
import java.util.*;
import java.util.Deque;
import java.util.LinkedList;

class MyStack<T>
{
    Deque<T> deque=new LinkedList<>();
    public void push(T s)
    {
        deque.push(s);
    }
    public T pop()
    {
        return deque.pop();
    }
    public T top()
    {
        return deque.peek();
    }
}

public class Task {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        MyStack<String> myStack=new MyStack<>();
        System.out.println("请输入入栈元素(输入stop停止入栈)：");
        String input=in.next();
        while (!input.equals("stop"))
        {
            myStack.push(input);
            input=in.next();
        }
        System.out.println("出栈："+myStack.pop());
        System.out.println("返回栈顶元素："+myStack.top());
    }

}
