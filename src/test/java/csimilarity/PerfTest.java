package csimilarity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

public class PerfTest {
    public static void main(String[] args) {
        ArrayDeque<Object> objects = new ArrayDeque<>();
        objects.push(1);
        objects.push(2);
        objects.push(3);
        objects.forEach(System.out::println);
    }
}
