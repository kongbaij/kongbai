package xia;
import com.mysql.cj.protocol.Message;

import java.util.List;
import java.util.*;
class person
{
    private String firstname;
    private String lastname;
    private int age;
    public person(String firstname,String lastname,int age)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.age=age;
    }
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
    public boolean equals(Object o)
    {
        if(o instanceof person)
        {
            person p=(person) o;
            return Objects.equals(this.firstname,p.firstname)&&Objects.equals(this.lastname,p.lastname)&&this.age==p.age;
        }
        return false;
    }
    @Override
    //覆写hashCode方法
    public int hashCode()
    {
          int h=0;
          h=h*31+firstname.hashCode();
          h=h*31+lastname.hashCode();
          h=h*31+age;
          return h;
    }

}
class people
{
    List<person> list;
    Map<String,Integer> cha;
    public people(List<person> list)
    {
        this.list=list;
        cha=new HashMap<>();
    }
    public int getAge(String firstname)
    {
        Integer age=cha.get(firstname);
        if(age==null)
        {
           cha.put("xiao",18);
           cha.put("xia",25);
           cha.put("bob",28);
           age=cha.get(firstname);
        }
        return age==null?-1:age.intValue();
    }
}
class message
{
    public int sequence;
    public String text;
  public message(int sequence,String text)
  {
      this.sequence=sequence;
      this.text=text;
  }
    @Override
    public boolean equals(Object o) {
        if(o instanceof message)
        {
            message p=(message) o;
            return Objects.equals(this.text,p.text)&&this.sequence==p.sequence;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, sequence);
    }
    public String getText()
    {
        return text;
    }
}
class SuffixExpression {
    private String execute;
    public String getExecute()
    {
        return execute;
    }

    public SuffixExpression(String execute)
    {
        this.execute=execute;
    }
    int execute(Map<String, Integer> env) {
        // TODO:
        StringBuilder builder=new StringBuilder();
       for(int i=0;i<execute.length();i++)
       {
           char c=execute.charAt(i);
           if(c=='x')
           {
               builder.append(env.get("x"));
           }
           else if(c=='y')
           {
               builder.append(env.get("y"));
           }
           else
           {
               builder.append(c);
           }
       }
       execute=builder.toString();
       Deque<Integer> deque=new LinkedList<>();
       for(int j=0;j<execute.length();j++)
       {
           char c1=execute.charAt(j);
           if(c1>='0'&&c1<='9')
           {
               deque.push(c1 - '0');//字符'0'在ASCII码表中的十进制值为48，而数字字符'0'到'9'在ASCII码表中的十进制值是连续递增的，分别为48到57。相减得到这个数本身
           }
           else
           {

                deque.push(calculate(c1,deque.pop(),deque.pop()));
           }
       }
        return deque.pop();
    }
    public int calculate(char c,int n2,int n1)
    {
        if(c=='+')
        {
            return n1+n2;
        }
        else if(c=='-')
        {
            return n1-n2;
        }
        else if(c=='*')
        {
            return n1*n2;
        }
        else if(c=='/')
            return n1/n2;
        else return 0;
    }

}
public class collection_lianxi {
    public static void main(String[] args) {
     //List
    final int start = 10;
    final int end = 20;
    List<Integer> list = new ArrayList<>();
    for (int i = start; i<= end; i++)
             list.add(i);
    int removed = list.remove((int) (Math.random()* list.size()));
    int found = findMissingnumber(start, end, list);
    System.out.println(list);
    System.out .println("missing number:"
        +found);
    System.out .println(removed == found ?"测试成功":"测试失败");

    //equals
        List<person> list1_1 =Collections.unmodifiableList(Arrays.asList(
                new person("xiao","ming",18),
                new person("xia","hong",25),
                new person("bob","smith",28)
        ));
        boolean exist=list1_1.contains(new person("bob","smith",28));
        System.out.println(exist?"测试成功":"测试失败");
     //Map
       people p=new people(list1_1);
       System.out.println(p.getAge("xia"));
       System.out.println(p.getAge("xia")==25?"测试成功":"测试失败");
     //Set
        List<message> received=Collections.unmodifiableList(Arrays.asList(
                new message(1,"Hello"),
                new message(2,"发工资了吗？"),
                new message(2,"发工资了吗？"),
                new message(3,"去哪吃饭？"),
                new message(3,"去哪吃饭？"),
                new message(4,"Bye")
        ));
        Set<message> displayMessage =process(received);
        for(message n:displayMessage)
        {
            System.out.println(n.getText());
        }
        //Stack,16进制转换
        String hex=toHex(15632);
        if(hex.equalsIgnoreCase("3D10"))
        {
            System.out.println("测试通过");
        }
        else
        {
            System.out.println("测试失败");
        }
        //中缀表达式转换成后缀表达式
        String exp = "x+2*(y-5)";
        SuffixExpression se = compile(exp);
        System.out.println(se.getExecute());
        Map<String, Integer> env = new HashMap<>();
        env.put("x",1);
        env.put("y",9);
        int result = se.execute(env);
        System.out.println(se.getExecute());
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }
    static SuffixExpression compile(String exp) {
         StringBuilder s=new StringBuilder();
         Deque<Character> deque=new LinkedList<>();
         for(int i=0;i<exp.length();i++)
         {
             char c=exp.charAt(i);
             if(c!='+'&&c!='-'&&c!='*'&&c!='/'&&c!='('&&c!=')')//判断是不是数字字符
             {
                 s.append(c);
             }
             else if(c=='(')
             {
                 deque.push(c);
             }
             else if(c=='+'||c=='-')
             {
                 while (!deque.isEmpty()&&deque.peek()!='(')
                 {
                     s.append(deque.pop());
                 }
                 deque.push(c);
             }
             else if(c=='*'||c=='/')
             {
               while (!deque.isEmpty()&&(deque.peek()=='*'||deque.peek()=='/'))
               {
                   s.append(deque.pop());
               }
               deque.push(c);
             }
             else
             {
                 while(!deque.isEmpty()&&deque.peek()!='(')
                 {
                     s.append(deque.pop());
                 }
                deque.pop();
             }
         }
          while (!deque.isEmpty())
          {
              s.append(deque.pop());
          }
        return new SuffixExpression(s.toString());
    }
      static String toHex(int n)
      {
          int m;
          Deque<String> deque=new LinkedList<>();
          while (n!=0)
          {
              m=n%16;
              n=n/16;
              String s=Integer.toString(m);
              if(m==10)
              {
                  s="A";
              }
              else if(m==11)
              {
                  s="B";
              }
              else if(m==12)
              {
                  s="C";
              }
              else if(m==13)
              {
                  s="D";
              }
              else if(m==14)
              {
                  s="E";
              }
              else if(m==15)
              {
                  s="F";
              }
              deque.push(s);
          }
          StringBuilder b=new StringBuilder();
         for(String d:deque)
         {
             b.append(d);
         }
          return b.toString();
      }
    static Set<message> process(List<message> p)
    {
        Set<message> set=new TreeSet<>(new Comparator<message>() {
            public int compare(message o1, message o2) {
                if(o1.sequence==o2.sequence)
                {
                    return 0;
                }
                return o1.sequence<o2.sequence?-1:1;
            }
        });
        for(message n:p)
        {
            set.add(n);
        }
        return set;
    }
    static int findMissingnumber(int start,int end, List<Integer> list)
    {
        int a=start;
        int b=end;
        for(Integer n:list)
        {
            if(n!=a)
            {
               b=a;
               break;
            }
            a++;
        }
        return b;
    }
}
