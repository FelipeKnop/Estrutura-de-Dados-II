package test.hash.collision_resolution;

import hash.collision_resolution.AddressingCollisionResolutionMethod;
import hash.collision_resolution.DoubleHashingMethod;

/**
 * Classe de testes para os métodos da classe {@link DoubleHashingMethod}
 */
public class DoubleHashingMethodTest extends AddressingCollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected AddressingCollisionResolutionMethod getCollisionResolutionMethod() {
        return new DoubleHashingMethod(200);
    }
}
