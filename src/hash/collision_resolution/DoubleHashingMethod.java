package hash.collision_resolution;

import hash.MultiplicationHash;

/**
 * Classe que extende a classe abstrata {@link AddressingCollisionResolutionMethod AddressingCollisionResolutionMethod}
 * utilizando o algoritmo de rehash visto em aula.
 */
public class DoubleHashingMethod extends AddressingCollisionResolutionMethod {

    public DoubleHashingMethod(int tableSize) {
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
     * Uma variação da implementação padrão do método de rehashing para tratamento de colisão.
     *
     * A partir de 100 iterações, utiliza o método da Sondagem Linear implementado na classe
     * {@link LinearProbingMethod} para a geração de um novo hash. Isso impede que a inserção
     * de um valor fique em um loop infinito ou muito grande onde uma posição para esse valor
     * nunca seja encontrada ou demore demais.
     *
     * @param value Valor a ser gerado o hash
     * @param lastTry Hash gerado na última tentativa, que ocasionou em colisão
     * @param iteration Iteração do tratamento de colisão
     * @return Hash gerado pela função de tratamento
     */
    @Override
    public int resolutionFunction(Long value, int lastTry, int iteration) {
        if (iteration <= 100)
            return (lastTry + iteration * (1 + hashingFunction(value))) % tableSize;
        else
            return new LinearProbingMethod(tableSize).resolutionFunction(value, lastTry, iteration);
    }
}
