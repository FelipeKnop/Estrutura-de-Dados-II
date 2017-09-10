package hash.collision_resolution;

import hash.HashingAlgorithm;
import hash.MultiplicationHash;

public class DoubleHashingMethod implements CollisionResolutionMethod {

    public int resolveFunction(int value, int failedIndex, int iteration, int mod) {
        HashingAlgorithm rehash = new MultiplicationHash();
        return (failedIndex + iteration*( 1+ rehash.hashFunction(value,mod)));
    }
}
