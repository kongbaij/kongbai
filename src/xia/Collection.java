package xia;
import java.sql.Connection;
import java.util.*;
enum day
{
    Mon,Tue,Wen,Thu,Fri,Sat,Sun;
}
class ReverseList<T> implements Iterable<T>
{
    private List<T> list=new ArrayList<>();
    public void add(T n)
    {
       list.add(n);
    }
    @Override
    public Iterator<T> iterator()
    {
        return new ReverseIterator(list.size());
    }
    class ReverseIterator implements Iterator<T>
    {
        int index;
        public ReverseIterator(int index)
        {
            this.index=index;
        }
        @Override
        public boolean hasNext()
        {
            return index>0;
        }
        @Override
        public T next()
        {
            index--;
            return ReverseList.this.list.get(index);
        }
    }
}

public class Collection {
    public static void main(String[] args)
    {
         //List<Integer> list =List.of();此方法1.8不能用，此方法不能进行添加，修改等操作
        List<Integer> list =Collections.unmodifiableList(Arrays.asList(1,2,3,4));//使用 Arrays.asList() 方法创建一个可变列表，然后通过 Collections.unmodifiableList() 方法将其转换为不可变列表。这个列表不能进行添加，删除等操作
        System.out.println(list);
        //一般使用迭代器Iterator来遍历，对不同的List都有最高的访问效率
        for(Iterator<Integer> it=list.iterator();it.hasNext(); )//it.hasNext()判断是否有下一个元素
        {
            int a=it.next();//返回下一个元素，一开始判断第0个元素
            System.out.print(a);
        }
        System.out.println();
        //使用for each循环会会自动变成Iterator的遍历
        for(Integer n:list)
        {
            System.out.print(n);
        }
        System.out.println();
        //常用的将List转换成数组的方法
        //传入一个类型相同的数组，并且将内部元素复制到数组中,可以填入不是List<e>定义的类型，如Number，但是不匹配会抛出异常，如String
        Integer[] array=list.toArray(new Integer[4]);
        for(Integer n: array)
        {
            System.out.print(n);
        }
        System.out.println();
        //将一个数组转换成List
        List<Integer> list1= Arrays.asList(array);
        System.out.println(list1);
        System.out.println(list.contains(2));//判断list是否包含某个元素
        System.out.println(list.contains(new Integer(4)));//true
        System.out.println(list);
        System.out.println(list.indexOf(3));//返回某个元素的下标，如果元素不存在返回-1
        //这两个方法是用equals方法比较的，不在list内但是类型相同，也相等也会返回true，对于自己创建的类型如person类型会返回false，需要编写equals方法
        //equals方法条件
        //自反性（Reflexive）：对于非null的x来说，x.equals(x)必须返回true；
        //对称性（Symmetric）：对于非null的x和y来说，如果x.equals(y)为true，则y.equals(x)也必须为true；
        //传递性（Transitive）：对于非null的x、y和z来说，如果x.equals(y)为true，y.equals(z)也为true，那么x.equals(z)也必须为true；
        //一致性（Consistent）：对于非null的x和y来说，只要x和y状态不变，则x.equals(y)总是一致地返回true或者false；
        //使用Objects.equals()可以省略判断字段等于null的麻烦，如果两个都为null则也相等
        //具体使用看lianxi
       person p=new person("kong","bai",18);
       //Map()方法可以通过key来查找对应的value
       Map<String ,person> map=new HashMap<>();
       map.put("key",p);//“key”是key，p是value
       person p1=map.get("key");
       System.out.println(p1==p);//p1和p是同一个实例
       System.out.println(p1);
        //key不可以重复，重复的会被覆盖，value可以重复
        //遍历key可以使用for each循环遍历Map实例的keySet()方法返回的Set集合
        Map<String,Integer> map1=new HashMap<>();
        map1.put("kong",133);
        map1.put("bai",255);
        map1.put("kb",166);
        for(String n:map1.keySet())
        {
            Integer a=map1.get(n);
            System.out.println(n+"="+a);
        }
        //遍历key和value可以使用for each循环遍历Map对象的entrySet()集合
        //如for(Map.Entry<String,Integer> n:map1.entrySet())
        //将自定义类作为key的时候需要覆写hashCode方法,可以使查询速度更快，如果hashCode返回值一样则会将所有value放在同一个List内，之后又要通过循环查找到对应value，影响查找速度
        Map<person,person> map2=new HashMap<>(100);//可以自定义数组大小，实际长度使2*100
        //对于枚举类的可以使用Java集合库提供的一种EnumMap，不需要计算hasCode(),效率最高
        Map<day,String> map3=new EnumMap<>(day.class);
        map3.put(day.Mon,"星期一");
        map3.put(day.Fri,"星期五");
        map3.put(day.Sun,"星期天");
        System.out.println(map3);
        System.out.println(map3.get(day.Sun));
        //TreeMap会按照首字母大小顺序遍历
        Map<String,Integer> map4=new TreeMap<>();
        map4.put("apple",1);
        map4.put("pear",2);
        map4.put("orange",3);
        for(String n:map4.keySet())
        {
            System.out.println(n);
        }
        //如果key是自定义的类需要在创建的同时指定一个排序方法
        Map<student,Integer> map5=new TreeMap<>(new Comparator<student>(){
            public int compare(student s1, student s2)
        {
            return s1.name.compareTo(s2.name);
        }
    });
        map5.put(new student("xiao"),1);
        map5.put(new student("kong"),2);
        map5.put(new student("bai"),3);
        System.out.println(map5.get(new student("kong")));
        for(student n:map5.keySet())
        {
            System.out.println(n);
        }
        //Set用于储存不重复的元素集合
        Set<String> set=new HashSet<>();
        set.add("apple");//添加元素
        set.add("pear");
        set.add("organ");
        set.add("avhf");
        System.out.println(set.add("apple"));//不能添加相同元素
        set.remove("apple");//删除元素
        System.out.println(set.contains("apple"));//判断元素是否存在
        System.out.println(set);//输出没有顺序关系
        //TreeSet是有序的，跟TreeMap一样，自定义的类型需要添加比较方法
        Set<String> set1=new TreeSet<>();
        set1.add("apple");
        set1.add("pear");
        set1.add("organ");
        set1.add("avhf");
        System.out.println(set1);
        //Queue相同操作不同返回值
        //              throw Exception 返回false或null
        //添加元素到队尾	    add(E e)	boolean offer(E e)//一般不会把null添加到队列中，否则不好判断是获取到null还是空
        //取队首元素并删除 	E remove()	E poll()
        //取队首元素但不删除	E element()	E peek()
        Queue<String> q=new LinkedList<>();
         q.add("apple");
         q.offer("pear");
         q.add("banana");
         System.out.println(q.poll());
         System.out.println(q.poll());
         System.out.println(q.peek());
         System.out.println(q.peek());
         //PriorityQueue可以给元素设定优先级，让那个特定元素先被或取到
         //如下有普通用户和vip用户的排列
          Queue<user> queue=new PriorityQueue<>(new userComparator());
          queue.offer(new user("xiao","A11"));
          queue.offer(new user("bai","v11"));
          queue.offer(new user("kong","A10"));
          queue.offer(new user("hei","v1"));
             for(int i=0;i<4;i++) {
                 System.out.println(queue.poll());
             }
             //Deque 双端队列
        //   	                Queue	                Deque
        //添加元素到队尾	 add(E e) / offer(E e)	    a ddLast(E e) / offerLast(E e)
        //取队首元素并删除	   E remove() / E poll()	E removeFirst() / E pollFirst()
        //取队首元素但不删除	E element() / E peek()	E getFirst() / E peekFirst()
        //添加元素到队首	          无	            addFirst(E e) / offerFirst(E e)
        //取队尾元素并删除	          无	            E removeLast() / E pollLast()
        //取队尾元素但不删除	      无	            E getLast() / E peekLast()
        Deque<String> deque=new LinkedList<>();
             deque.offerLast("apple");
             deque.offerFirst("pear");
             deque.offerLast("banana");
             System.out.println(deque.pollFirst());
             System.out.println(deque.pollLast());
             //栈是一种后进先出（LIFO：Last In First Out）的数据结构，即最后进Stack的元素一定最早出Stack，可以用deque实现
          //把元素压栈：push(E)/addFirst(E)；
        //把栈顶的元素“弹出”：pop()/removeFirst()；
        //取栈顶元素但不弹出：peek()/peekFirst()；
        //具体使用看练习

        //使用的迭代器可以加快遍历速度，自己编写的集合类要实现for each遍历需要集合类实现Iterable接口，该接口要求返回一个Iterator对象；用Iterator对象迭代集合内部数据。
         ReverseList<Integer> list2=new ReverseList<>();
         list2.add(1);
         list2.add(2);
         list2.add(3);
         for(Integer n:list2)
         {
             System.out.println(n);
         }

         //Collections
        //排序
        List<String> list3=new ArrayList<>();
         list3.add("pear");
         list3.add("bai");
         list3.add("apple");
         list3.add("organ");
         System.out.println("排序前："+list3);
         Collections.sort(list3);
         System.out.println("排序后："+list3);
         //随机顺序
        Collections.shuffle(list3);
        System.out.println("打乱后："+list3);
        //不可变集合
        //封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
        //封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
        //封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)
        //对原来可变集合进行改变仍然会影响不可变集合
        List<String> list4=Collections.unmodifiableList(list3);
        //一般要丢掉对原始集合的引用
        list3=null;
        //再对原始集合改变会抛出异常
        //list3.remove(3);
        System.out.println(list4);
        //Collections还提供了一组方法，可以把线程不安全的集合变为线程安全的集合：
        //变为线程安全的List：List<T> synchronizedList(List<T> list)
        //变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
        //变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)
    }
}
class userComparator implements Comparator<user>
{
    public int compare(user o1, user o2) {
        if(o1.number.charAt(0)==o2.number.charAt(0))
        {
            //使用compareTo的比较方式会按字符串的方式来逐个比较，A2会比A10大
            return Integer.parseInt(o1.number.substring(1))>Integer.parseInt(o2.number.substring(1))?1:-1;
        }
        else if(o1.number.charAt(0)=='v')
        {
            return -1;
        }
        else
            return 1;
    }
}
class user
{
    public final String name;
    public final String number;

    public user(String name,String number) {
        this.name = name;
        this.number=number;
    }
    public String toString()
    {
        return name+":"+number;
    }

}
class student
{
    public String name;
    public student(String name)
    {
        this.name=name;
    }
    public String toString()
    {
        return "student:"+name;
    }
}
