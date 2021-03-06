package test.hash.collision_resolution;

import hash.collision_resolution.CollisionResolutionMethod;
import hash.collision_resolution.QuadraticProbingMethod;

/**
 * Classe de testes para os métodos da classe {@link QuadraticProbingMethod}
 */
public class QuadraticProbingMethodTest extends CollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected CollisionResolutionMethod getCollisionResolutionMethod() {
        return new QuadraticProbingMethod(200);
    }
}
