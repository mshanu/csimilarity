package csimilarity;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class PerfTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        IntStream.range(1, 1200).parallel().forEach(e -> integers.add(e));
    }
}
