package kong;

import com.sun.org.slf4j.internal.LoggerFactory;
import jdk.internal.instrumentation.TypeMapping;
import org.omg.CORBA.WStringSeqHelper;
import org.apache.logging.log4j.Logger;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;

import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
class test1
{
    //@Target(ElementType.TYPE)//指定定义注解可以用在什么地方
    @Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR})//使注解可以用在多个地方
    public @interface report
    {
        int type()default 0;
        String value()default "";
    }

}
@test1.report("一个人")
class person
{
    private String name;
    private int age;
    @test1.report(type = 18,value = "kong")
    public person(String name,int age)
    {
        this.age =age;
        this.name =name;
    }

    public int getAge() {
        return age;
    }
    public String getName()
    {
        return name;
    }

}

public class zhujie {
    public static void main(String[] args) {
        Logger log = LogManager.getLogger(test.class);
        person kong = new person("kong",18);
        System.out.println(kong.getName()+kong.getAge());
    }

}
