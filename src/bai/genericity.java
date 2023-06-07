package bai;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class person implements Comparable<person>
{
    private String name;
    private int age;
    public person(String name,int age)
    {
        this.name= name;
        this.age =age;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public int compareTo(person e)//实现接口方法
    {
        return Integer.compare(this.age,e.age);
    }
    public String toString()
    {
        return this.name+" "+this.age;
    }
}
//编写一个泛型类
class Pair1<T>//使用<T extends Number>类似的T只能为Number和Number的子类,Pair1<String>类型会报错
{
    private T first;
    private T last;
    public Pair1(T first,T last)
    {
        this.first=first;
        this.last= last;
    }
    public void setFirst(T first)
    {
        this.first=first;
    }
    public void setLast(T last)
    {
        this.last=last;
    }
    public T getFirst()
    {
        return first;
    }
    public T getLast()
    {
        return last;
    }

    public static <T> T[] createArr(Class<T> cls)//借助Class<T>来创建数组
    {
        return (T[]) Array.newInstance(cls,5);
    }
    @SafeVarargs
    public static <T> T[] asArr(T...o)//创建可变数组
    {
        return o;
    }
}
class pair<T,U>//使用多种泛型
{
   private T name;
   private U age;
   public pair(T name,U age)
   {
       this.name =name;
       this.age =age;
   }
    public T getName()
    {
        return name;
    }
    public U getAge()
    {
        return age;
    }
    //静态泛型方法应该使用其他类型区分
    public static<K,U> pair<K,U> create(K first,U last)
    {
        return new pair<>(first, last);
    }
}
class student extends pair<String,Integer>//子类可以获取父类的泛型类型
{
    public student(String name, Integer age) {
        super(name, age);
    }
}

public class genericity {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(15);//添加元素
        list.add(17);
        list.add(1,3);//添加到指定位置，此添加到下标为1,原来位置的数字向后退一个
        int sum=sum(list);
        Integer n= list.get(2);//获取元素，从下标0开始
        System.out.println(n);
        System.out.println("元素总和："+sum);

        person[] people =new person[]{
                new person("ming",17),
                new person("bai",15),
                new person("kong",18)
        };
        Arrays.sort(people);//不是Comparable类型，会抛出异常
        System.out.println("排序后："+Arrays.toString(people));

        pair<String,Integer> a =new pair<>("xiao",18);
        System.out.println("年龄："+a.getAge()+" "+"姓名:"+a.getName());
        pair<Integer,Double> A= pair.create(1,2.0);
        System.out.println("Integer:"+A.getName()+" "+"Double:"+A.getAge());

        Class<student> clazz = student.class;
        Type t= clazz.getGenericSuperclass();//获取父类的泛型类型,Type跟T差不多,泛型类或泛型方法中定义的类型参数,返回的是<? super T>类型
        if(t instanceof ParameterizedType)//判断一个类对象是否有泛型参数,ParameterizedType是Java中表示参数化类型的接口，也就是带有泛型参数的类型
        {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments();//获取所有泛型类型
            for(Type m:types)
            {
                Type T =m;
                Class<?> typeClass = (Class<?>) T;//依次获取泛型类型
                System.out.println("泛型类型："+typeClass);
            }
        }

        Pair1<Integer> p= new Pair1<>(1,55);
        int n1=add(p);
        set(p);
        System.out.println("n1="+n1);

        Class<String> str =String.class;
        Constructor<String> cons = str.getConstructor(String.class);//获取构造方法
        String s = cons.newInstance("dhjshd");//创建对象并且调用类的构造方法
       @SuppressWarnings("unchecked")
        Pair1<Integer>[] ps=(Pair1<Integer>[]) new Pair1[3];//不能用new直接创建泛型数组，必须要先强制转换
        String[] s1=Pair1.createArr(String.class);//另一种创建数组方法，60
        String[] s2=Pair1.asArr("a","b","c");//64
    }

    static int add(Pair1<? extends Number> p)//extends通配符，可以接收泛型类型为Number和Number所有的子类
    {
         Number first = p.getFirst();
         Number last =p.getLast();
         //p.setFirst(new Integer(first.intValue()+100));不能传入Number的子类
         return first.intValue()+last.intValue();//intValue()是将一个Integer转换为int
    }
    static void set(Pair1<? super Integer> p)//super,可以接收泛型类型为Integer和Integer的父类
    {
        p.setFirst(100);//可以传入Integer的引用
        p.setLast(50);
        //Integer n= p.getFirst();不能获取Integer的引用,只写不能读,除了Object
    }
    static int sum(List<? extends Integer> list)//相对于List<Integer> list,List<? extends Integer>list限制了set的使用该方法只能读取不能修改
    {
        int i;
        int sum=0;
        for(i=0;i<list.size();i++)
        {
            sum=sum+list.get(i);
        }
        return sum;
    }

}
