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
        int[] value = { 1 };
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
        int[] value = { 1 };
        candidate.foreach(element -> assertEquals(value[0]++, element));
    }

    @Test
    void withCapacity() {
        assertEquals(0, List.withCapacity(15).size());
    }

    @Test
    void foreach() {
        var candidate = List.of(1, 2, 3);
        int[] value = { 1 };
        candidate.foreach(element -> assertEquals(value[0]++, element));
    }

    @Test
    void map() {
        var candidate = List.of(1, 2, 3);
        int[] value = { 1 };
        candidate
                .map(x -> x + 5)
                .foreach(element -> assertEquals(5 + value[0]++, element));
    }

    @Test
    void flatmap() {
    }

    @Test
    void reduce() {
    }

    @Test
    void size() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void add() {
    }

    @Test
    void addAll() {
    }
}