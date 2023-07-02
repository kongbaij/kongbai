package hanshushibiancheng;
import java.util.Arrays;
import java.util.Comparator;
public class Lambda_lianxi {
    public static void main(String[] args)
    {
        //Lambda
        String[] array = new String[] { "apple", "Orange", "banana", "Lemon" };
        // 请使用忽略大小写排序，并改写为Lambda表达式:
        Arrays.sort(array,(s1,s2)->
        {
            return s1.compareToIgnoreCase(s2);
        });
        System.out.println(String.join(", ", array));
        //方法引用
        String[] s = new String[] { "apple", "Orange", "banana", "Lemon" };
        // 请使用忽略大小写排序，并改写为方法引用:
        Arrays.sort(s,String::compareToIgnoreCase);
        System.out.println(String.join(", ", array));
    }
}
