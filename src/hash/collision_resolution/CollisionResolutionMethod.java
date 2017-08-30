package hash.collision_resolution;

public interface CollisionResolutionMethod {

    default int resolve(Object element, int failedIndex, int iteration, int mod) {
        int value = element.hashCode();
        return resolveFunction(value, failedIndex, iteration, mod);
    }

    int resolveFunction(int value, int failedIndex, int iteration, int mod);
}
