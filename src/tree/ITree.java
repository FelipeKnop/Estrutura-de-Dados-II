package tree;

public interface ITree<T> {

    void insert(T value);

    T search(T value);

    boolean remove(T value);
}
