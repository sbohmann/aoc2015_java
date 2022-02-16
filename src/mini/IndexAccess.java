package mini;

public interface IndexAccess<T> extends Countable {
    T get(int index);
}
