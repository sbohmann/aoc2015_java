package mini;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    @Test
    void of() {
        var candidate = List.of(1, 2, 3);
        assertEquals(3, candidate.size());
        assertEquals(1, candidate.get(0));
        assertEquals(2, candidate.get(1));
        assertEquals(3, candidate.get(2));
        int[] value = {1};
        candidate.foreach(element -> assertEquals(value[0]++, element));
    }

    @Test
    void copyOf() {
        var original = List.of(1, 2, 3);
        assertEquals(3, original.size());
        var candidate = List.copyOf(original);
        assertEquals(3, candidate.size());
        assertEquals(1, candidate.get(0));
        assertEquals(2, candidate.get(1));
        assertEquals(3, candidate.get(2));
        int[] value = {1};
        candidate.foreach(element -> assertEquals(value[0]++, element));
    }

    @Test
    void withCapacity() {
        assertEquals(0, List.withCapacity(15).size());
    }

    @Test
    void foreach() {
        var candidate = List.of(1, 2, 3);
        int[] value = {1};
        candidate.foreach(element -> assertEquals(value[0]++, element));
    }

    @Test
    void map() {
        var candidate = List.of(1, 2, 3);
        int[] value = {1};
        candidate
                .map(x -> x + 5)
                .foreach(element -> assertEquals(5 + value[0]++, element));
    }

    @Test
    void flatmap() {
        var candidate = List.of(1, 2, 3);
        int[] value = {2, 4, 8, 4, 8, 16, 6, 12, 24};
        int[] index = {0};
        candidate
                .flatmap(x -> List.of(x * 2, x * 4, x * 8))
                .foreach(element -> assertEquals(value[index[0]++], element));
    }

    @Test
    void reduce() {
        var candidate = List.of(1, 2, 3);
        assertEquals(18, candidate.reduce(3, (a, b) -> a * b));
    }

    @Test
    void size() {
        var candidate = List.of(1, 2, 3);
        assertEquals(3, candidate.size());
        candidate.add(5);
        candidate.add(7);
        assertEquals(5, candidate.size());
    }

    @Test
    void get() {
        var candidate = List.of(1, 2, 3);
        assertEquals(2, candidate.get(1));
    }

    @Test
    void set() {
        var candidate = List.of(1, 2, 3);
        candidate.set(1, 17);
        assertEquals(17, candidate.get(1));
    }

    @Test
    void add() {
        var candidate = List.of(1, 2, 3);
        candidate.add(17);
        candidate.add(27);
        assertEquals(5, candidate.size());
        assertEquals(17, candidate.get(3));
    }

    @Test
    void addAll() {
        var candidate = List.of(1, 2, 3);
        candidate.addAll(List.of(17, 27));
        assertEquals(5, candidate.size());
        assertEquals(17, candidate.get(3));
    }
}
