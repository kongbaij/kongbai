package JUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.*;
class ConfigTest {
   Config config;
    @BeforeEach
    void setUp() {
     this.config=new Config();
    }

    @AfterEach
    void tearDown() {
        config=null;
    }
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testWindows() {
        assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void testLinuxAndMac() {
        assertEquals("/usr/local/test.cfg", config.getConfigFile("test.cfg"));
    }

    @Test
    @Disabled("bug-101")
    void testBug101() {
        // TODO: this test is disabled for bug fixing
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)//不在Windows平台执行的测试
    void testOnNonWindowsOs() {
        // TODO: this test is disabled on windows
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)//只能在Java 9或更高版本执行的测试
    void testOnJava9OrAbove() {
        // TODO: this test is disabled on java 8
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")//只能在64位操作系统上执行的测试
    void testOnlyOn64bitSystem() {
        // TODO: this test is only run on 64 bit system
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true")//需要传入环境变量DEBUG=true才能执行的测试
    void testOnlyOnDebugMode() {
        // TODO: this test is only run on DEBUG=true
    }
}