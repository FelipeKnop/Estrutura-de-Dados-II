package test.hash.collision_resolution;

import hash.collision_resolution.AddressingCollisionResolutionMethod;
import hash.collision_resolution.LinearProbingMethod;

/**
 * Classe de testes para os métodos da classe {@link LinearProbingMethod}
 */
public class LinearProbingMethodTest extends AddressingCollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected AddressingCollisionResolutionMethod getCollisionResolutionMethod() {
        return new LinearProbingMethod(200);
    }
}
