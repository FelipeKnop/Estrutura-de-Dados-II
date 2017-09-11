package test.hash.collision_resolution;

import hash.collision_resolution.CollisionResolutionMethod;
import hash.collision_resolution.SeparateChainingMethod;

/**
 * Classe de testes para os métodos da classe {@link SeparateChainingMethod}
 */
public class SeparateChainingMethodTest extends CollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected CollisionResolutionMethod getCollisionResolutionMethod() {
        return new SeparateChainingMethod(200);
    }
}
