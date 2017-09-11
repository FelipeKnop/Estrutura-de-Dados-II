package test.hash.collision_resolution;

import hash.collision_resolution.AddressingCollisionResolutionMethod;
import hash.collision_resolution.QuadraticProbingMethod;

/**
 * Classe de testes para os métodos da classe {@link QuadraticProbingMethod}
 */
public class QuadraticProbingMethodTest extends AddressingCollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected AddressingCollisionResolutionMethod getCollisionResolutionMethod() {
        return new QuadraticProbingMethod(200);
    }
}
