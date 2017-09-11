package test.hash.collision_resolution;

import hash.collision_resolution.CoalescedChainingMethod;
import hash.collision_resolution.CollisionResolutionMethod;

/**
 * Classe de testes para os métodos da classe {@link CoalescedChainingMethod}
 */
public class CoalescedChainingMethodTest extends CollisionResolutionMethodTest {

    @Override
    protected int getAmountOfValues() {
        return 200;
    }

    @Override
    protected CollisionResolutionMethod getCollisionResolutionMethod() {
        return new CoalescedChainingMethod(200);
    }
}
