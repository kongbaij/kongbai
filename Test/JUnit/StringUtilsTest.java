package JUnit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 100 })
    void testAbs(int x) {
        assertEquals(x, Math.abs(x));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, -5, -100 })
    void testAbsNegative(int x) {
        assertEquals(-x, Math.abs(x));
    }

    @ParameterizedTest
    @MethodSource//编写一个同名的静态方法来提供测试参数
    void testCapitalize(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    static List<Arguments> testCapitalize() {
        return Collections.unmodifiableList(Arrays.asList(
                Arguments.arguments("abc", "Abc"),//JUnit5提供的静态方法，创建参数
                Arguments.arguments("APPLE", "Apple"),
                Arguments.arguments("gooD", "Good")));
    }

    @ParameterizedTest//每一个字符串表示一行，一行包含的若干参数用,分隔
    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
    void testCapitalizeByCsvSource(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    @ParameterizedTest//测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource,test-capitalize.csv这个文件要放到test目录下
    @CsvFileSource(resources = { "/test-capitalize.csv" })
    void testCapitalizeByCsvFile(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }
}