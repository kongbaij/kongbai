package hanshushibiancheng;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class stream {
    public static void main(String[] args)
    {
        //创建steam
        Stream<String> stream=Stream.of("A","B","C");
        stream.forEach(System.out::println);
        //数组创建
        Stream<Integer> stream1= Arrays.stream(new Integer[]{1,2,3,4});
        //集合创建
        Stream<Integer> stream2= Collections.unmodifiableList(Arrays.asList(1,5,6,5)).stream();
        stream1.forEach(System.out::print);
        System.out.println();
        stream2.forEach(System.out::print);
        //Supplier,可以无限次调用get方法，输出时要限制范围
        Stream<Integer> stream3=Stream.generate(new natual());
        stream3.limit(20).forEach(System.out::print);
        //使用IntSteam,LongStream,DoubleStream可以提高运行效率
        //map
        System.out.println();
        /*Stream<String> stream4=Stream.of("Apple","orange  ","bai ","kong");
        stream4.map(String::trim);//去空格
        stream4.map(String::toLowerCase);//变小写
        stream4.forEach(System.out::print);
        System.out.println(stream2.map(n->n*n));//平方*/
        //filter，过滤不满足条件的元素
        IntStream.of(1,3,5,6,4,2,3)
                .filter(n->n%2!=0)
                .forEach(System.out::print);
        System.out.println();
        //Stream.reduce()则是Stream的一个聚合方法，它可以把一个Stream的所有元素按照聚合函数聚合成一个结果。
        int sum=IntStream.of(1,2,3,4,5,6,7,8,9).reduce(0,(n,m)->n+m);
        System.out.println(sum);
       //map和filter不会发生计算，reduce会
        //排序
        List<String> list=Collections.unmodifiableList(Arrays.asList("apple","orange","pear","kong","bai"))
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);
        //去重
        List<Integer> list1=Collections.unmodifiableList(Arrays.asList(1,1,5,6,6,4,4,5))
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list1);
        //截取
        List<Integer> list2=Collections.unmodifiableList(Arrays.asList(1,2,3,4,5,6,7,8))
                .stream()
                .skip(3)//去掉下标为3之前的数
                .collect(Collectors.toList());
        System.out.println(list2);
        //合并
        Stream<Integer> stream4=Collections.unmodifiableList(Arrays.asList(1,23,54,8)).stream();
        Stream<Integer> stream5=Collections.unmodifiableList(Arrays.asList(5,6)).stream();
        Stream<Integer> i=Stream.concat(stream4,stream5);
        System.out.println(i.collect(Collectors.toList()));
       //并行
        Stream<String> stream6=Collections.unmodifiableList(Arrays.asList("kong","bai","apple","orange")).stream();
         String[] strings=stream6.parallel()
                         .sorted()
                         .toArray(String[]::new);
           System.out.println(Arrays.toString(strings));
           //聚合操作：count()，max()，min()，sum()，average()；
        //其他操作：allMatch()测试是否所有满足条件, anyMatch()//测试是否有一个满足条件, forEach()。
    }
}
class natual implements Supplier<Integer>
{
    int n=0;
    @Override
    public Integer get()
    {
        n++;
        return n;
    }
}
