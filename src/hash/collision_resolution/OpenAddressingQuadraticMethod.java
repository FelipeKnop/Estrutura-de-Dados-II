package hash.collision_resolution;


public class OpenAddressingQuadraticMethod implements CollisionResolutionMethod {

    @Override
    public int resolveFunction(int value, int failedIndex, int iteration, int mod) {
        return (failedIndex + iteration*iteration) % mod;
    }
}
