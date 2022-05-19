package cp;

import java.util.List;

public interface Container<T> {
    boolean add(T item);
    boolean remove(T item);
    List<T> getAll();
}
