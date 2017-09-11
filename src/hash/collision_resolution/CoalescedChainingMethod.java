package hash.collision_resolution;

import hash.MultiplicationHash;

import java.util.ArrayList;

/**
 * Classe que extende a classe abstrata {@link ChainingCollisionResolutionMethod ChainingCollisionResolutionMethod}
 * utilizando o algoritmo de Encadeamento Coalescido visto em aula.
 */
public class CoalescedChainingMethod extends ChainingCollisionResolutionMethod {

    public CoalescedChainingMethod() {
        super();
    }

    public CoalescedChainingMethod(int tableSize) {
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
     * Implementação padrão do método de Encadeamento Coalescido visto em aula.
     *
     * @param value Valor a ser gerado o hash
     * @param lastTry Hash gerado na última tentativa, que ocasionou em colisão
     * @param iteration Iteração do tratamento de colisão
     * @return Hash gerado pela função de tratamento
     */
    @Override
    public int resolutionFunction(Long value, int lastTry, int iteration) {
        LinearProbingMethod linearProbingMethod = new LinearProbingMethod(tableSize);
        int hash = lastTry;
        while (hashTable.get(hash) != null) {
            this.comparisons++;
            lastTry = hash;
            if (hashTable.get(hash).size() == 1)
                hash = linearProbingMethod.resolutionFunction(value, hash, ++iteration);
            else
                hash = hashTable.get(hash).get(1).intValue();
        }
        ArrayList<Long> list = hashTable.get(lastTry);
        list.add((long) hash);
        ArrayList<Long> newList = new ArrayList<>();
        newList.add(value);
        hashTable.set(hash, newList);
        return 0;
    }
}
