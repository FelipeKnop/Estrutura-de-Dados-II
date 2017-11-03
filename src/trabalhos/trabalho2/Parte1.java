package trabalhos.trabalho2;

import business.TweetFileReader;
import tree.AVLTree;
import tree.BenchmarkTreeStructure;
import tree.RedBlackTree;

import static business.FileManager.readInputFile;
import static business.FileManager.redirectOutput;

/**
 * Classe para a execução das funções especificadas na Parte 1 do Trabalho 2.
 */
public class Parte1 {

    /**
     * Método main que lê os valores dos arquivos de entrada, cria
     * O TweetFileReader que lê o arquivo de Tweets, redireciona
     * a saída para o arquivos de saida e executa as funções pedidas
     * na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        TweetFileReader tweetFileReader = TweetFileReader.create("tweets.txt");
        assert tweetFileReader != null;
        int[] nValues = readInputFile("entradaInsercao.txt");
        assert nValues != null;
        redirectOutput("saidaInsercao.txt");
        insercao(nValues, tweetFileReader);
        nValues = readInputFile("entradaBusca.txt");
        assert nValues != null;
        redirectOutput("saidaBusca.txt");
        busca(nValues, tweetFileReader);
        nValues = readInputFile("entradaRemocao.txt");
        assert nValues != null;
        redirectOutput("saidaRemocao.txt");
        remocao(nValues, tweetFileReader);
    }

    private static void insercao(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n-------------- Árvore AVL --------------");
        BenchmarkTreeStructure.benchmarkInsertTweets(new AVLTree<>(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- Árvore Vermelho e Preta --------------");
        BenchmarkTreeStructure.benchmarkInsertTweets(new RedBlackTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore Splay --------------");
//        BenchmarkTreeStructure.benchmarkInsertTweets(new SplayTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore B --------------");
//        BenchmarkTreeStructure.benchmarkInsertTweets(new BTree<>(), nValues, tweetFileReader);
    }

    private static void busca(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n-------------- Árvore AVL --------------");
        BenchmarkTreeStructure.benchmarkSearchTweets(new AVLTree<>(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- Árvore Vermelho e Preta --------------");
        BenchmarkTreeStructure.benchmarkSearchTweets(new RedBlackTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore Splay --------------");
//        BenchmarkTreeStructure.benchmarkSearchTweets(new SplayTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore B --------------");
//        BenchmarkTreeStructure.benchmarkSearchTweets(new BTree<>(), nValues, tweetFileReader);
    }

    private static void remocao(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n-------------- Árvore AVL --------------");
        BenchmarkTreeStructure.benchmarkRemoveTweets(new AVLTree<>(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- Árvore Vermelho e Preta --------------");
        BenchmarkTreeStructure.benchmarkRemoveTweets(new RedBlackTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore Splay --------------");
//        BenchmarkTreeStructure.benchmarkRemoveTweets(new SplayTree<>(), nValues, tweetFileReader);
//        System.out.println("\n\n-------------- Árvore B --------------");
//        BenchmarkTreeStructure.benchmarkRemoveTweets(new BTree<>(), nValues, tweetFileReader);
    }
}
