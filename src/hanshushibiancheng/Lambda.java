package hanshushibiancheng;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args)
    {
        //原方法,利用匿名类编写
        String[] s=new String[]{"apple","orange","bai","banana"};
       //Arrays.sort(s, new Comparator<String>() {
            //@Override
            //public int compare(String o1, String o2) {
                //return o1.compareTo(o2);
           // }
        //});
        System.out.println(Arrays.toString(s));
        //单方法接口使用Lambda转换,参数是(s1, s2)，参数类型可以省略，因为编译器可以自动推断出String类型。-> { ... }表示方法体
        Arrays.sort(s,(s1,s2)->{
            return s1.compareTo(s2);
        });
        //如果只有一行也可以： (s1, s2) -> s1.compareTo(s2)
        System.out.println(Arrays.toString(s));
        //只定义了单方法的接口称之为FunctionalInterface，用注解@FunctionalInterface标记,例如Callable接口，Comparator接口
        //引用静态方法，只要方法签名和一个接口一致就可以，方法签名一致要求参数一样和返回类型相同
        Arrays.sort(s,Lambda::cmp);
        //Arrays.sort(s,String::compareTO)String类中的compareTO方法也可以，他有一个隐藏的参数this
        System.out.println(String.join(",",s));
        //引用构造方法例子：String转换成person,避免使用foreach循环一个个填充给person
        List<String> list= Collections.unmodifiableList(Arrays.asList("Bob","kong","bai"));
        List<person> people=list.stream().map(person::new).collect(Collectors.toList());
        System.out.println(people);
    }
    static int cmp(String s1,String s2)
    {
        return s1.compareTo(s2);
    }
}
class person
{
    private String name;
    public person(String name)
    {
        this.name=name;
    }
    public String toString()
    {
        return "person:"+name;
    }
}

