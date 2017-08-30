package hash;

public interface HashingAlgorithm {

    default int hash(Object element, int mod) {
        int value = element.hashCode();
        return hashFunction(value, mod);
    }

    int hashFunction(int value, int mod);
}
