package kong;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Inherited;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
//@Target定义注解可以用在什么地方
    @Inherited//使子类继承父类的定义的注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR})//使注解可以用在多个地方
    @interface report
    {
        int type()default 0;
        String value()default "";
    }
@Repeatable(errors.class)//使一个注解可以多次使用
@Retention(RetentionPolicy.RUNTIME)//定义注解生命周期,不定义默认为class
    @Target(ElementType.METHOD)
@interface error
{
    String value()default "";
    int lie()default 0;
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//定义一个error注解的容器
@interface errors
{
    error[] value();
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface range
{
    int min()default 0;
    int max()default 255;
}
@report
class person
{
    @range(min = 1,max = 4)
    private String name;
    @range(min=1,max=100)
    private int age;
    public person(String name,int age)
    {
        this.age =age;
        this.name =name;
    }
    @report(type = 18,value = "kong")
    public int getAge() {
        return age;
    }
    @error(value = "bai")
    public String getName()
    {
        return name;
    }
    public void check(person p)throws IllegalArgumentException,IllegalAccessException //定义注解的检查方法
    {
        //遍历所有字段
         for(Field field:p.getClass().getDeclaredFields())//getFields()返回公共字段,getClass()返回原来的类,getDeclaredFields返回所有类型
         {
             //获取字段定义的range
               range r =field.getAnnotation(range.class);
               //如果range存在
              if(r!=null)
              {
                  //获取字段的值
                  Object value = field.get(p);
                 //如果是String
                  if(value instanceof String)
                  {
                      if((((String) value).length()<r.min())||((String) value).length()>r.max())
                      {
                          throw new IllegalArgumentException("Invalid name: "+p.getName());
                      }
                  }
                  //如果是int
                  else if(value instanceof Integer)
                  {
                      if((Integer)value<r.min()||(Integer)value>r.max())
                          throw new IllegalArgumentException("Invalid age: "+p.getAge());
                  }
              }
         }
    }
}
class student extends person//默认也定义了report
{
    public student(String name,int age)
    {
        super(name,age);
    }
}
public class Annotation{
    public static void main(String[] args) {
        //读取方法的注解
        Class<person> kong = person.class;
        Method[] m = kong.getMethods();
        for(Method n:m)
        {
           if(n.isAnnotationPresent(report.class))
           {
               report r =n.getAnnotation(report.class);
                       System.out.println(r.type());
           }
          else if(n.isAnnotationPresent(error.class))
           {
               error e =n.getAnnotation(error.class);
                       System.out.println(e.value());
           }
        }
        //判断person是否有report这个注解
        if(kong.isAnnotationPresent(report.class)) {
            System.out.println("12");
            //获取注解
            report re = person.class.getAnnotation(report.class);
            int type = re.type();
            System.out.println(type);
            try{
                person bai = new person("minjk",18);
                bai.check(bai);
                System.out.println(bai.getName()+" "+bai.getAge());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }
    }

}

