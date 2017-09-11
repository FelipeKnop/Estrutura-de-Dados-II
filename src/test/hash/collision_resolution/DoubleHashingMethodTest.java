package test.hash.collision_resolution;

import hash.collision_resolution.CollisionResolutionMethod;
import hash.collision_resolution.DoubleHashingMethod;

/**
 * Classe de testes para os métodos da classe {@link DoubleHashingMethod}
 */
public class DoubleHashingMethodTest extends CollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected CollisionResolutionMethod getCollisionResolutionMethod() {
        return new DoubleHashingMethod(200);
    }
}
