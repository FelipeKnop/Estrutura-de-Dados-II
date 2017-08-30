package hash.collision_resolution;

public class OpenAddressingMethod implements CollisionResolutionMethod {

    @Override
    public int resolveFunction(int value, int failedIndex, int iteration, int mod) {
        return (failedIndex + 1) % mod;
    }
}
