package test.hash.collision_resolution;

import hash.collision_resolution.CollisionResolutionMethod;
import hash.collision_resolution.LinearProbingMethod;

/**
 * Classe de testes para os métodos da classe {@link LinearProbingMethod}
 */
public class LinearProbingMethodTest extends CollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected CollisionResolutionMethod getCollisionResolutionMethod() {
        return new LinearProbingMethod(200);
    }
}
