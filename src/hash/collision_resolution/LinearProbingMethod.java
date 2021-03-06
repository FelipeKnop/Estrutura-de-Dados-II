package hash.collision_resolution;

import hash.MultiplicationHash;

/**
 * Classe que extende a classe abstrata {@link AddressingCollisionResolutionMethod AddressingCollisionResolutionMethod}
 * utilizando o algoritmo de Sondagem Linear visto em aula.
 */
public class LinearProbingMethod extends AddressingCollisionResolutionMethod {

    public LinearProbingMethod() {
        super();
    }

    public LinearProbingMethod(int tableSize) {
        super(tableSize);
    }

    /**
     * Utiliza o método de hashing da multiplicação para gerar o hash do valor recebido.
     *
     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    @Override
    public int hashingFunction(Long value) {
        return new MultiplicationHash(tableSize).hashingFunction(value);
    }

    /**
     * Implementação padrão do método da Sondagem Linear visto em aula.
     *
     * @param value Valor a ser gerado o hash
     * @param lastTry Hash gerado na última tentativa, que ocasionou em colisão
     * @param iteration Iteração do tratamento de colisão
     * @return Hash gerado pela função de tratamento
     */
    @Override
    public int resolutionFunction(Long value, int lastTry, int iteration) {
        return (lastTry + 1) % tableSize;
    }
}
