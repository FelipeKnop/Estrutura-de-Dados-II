package test.compression;

import compression.CompressionAlgorithm;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Classe abstrata que implementa os métodos de teste
 * de algoritmos de compressão.
 */
public abstract class CompressionAlgorithmTest {

    private final String TEST_STRING = "\n" +
            "\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dignissim nec eros sit amet blandit. Pellentesque ultrices dolor ut lorem efficitur fermentum. Sed aliquam ornare rutrum. Duis feugiat magna a laoreet faucibus. Aenean vulputate, diam in viverra tempus, diam leo mattis augue, a lobortis purus turpis sit amet massa. Cras finibus ipsum in libero egestas, id pharetra felis egestas. Pellentesque eleifend lorem et accumsan euismod. Aenean ut tellus velit. Aenean ex arcu, ullamcorper et ligula ac, scelerisque pellentesque nibh. Nam vitae dolor vehicula, tincidunt nunc sit amet, lacinia ligula. Phasellus euismod efficitur lobortis.\n" +
            "\n" +
            "Nulla non justo dolor. Nunc ut sem id felis maximus iaculis a vitae justo. Vivamus non metus at ligula volutpat rutrum. Pellentesque sollicitudin vulputate urna, et pretium ante gravida vitae. Aenean bibendum, tortor eget cursus blandit, massa odio iaculis ligula, id maximus felis elit quis neque. Sed vestibulum dui eget aliquet placerat. Phasellus consequat porta tempor. Sed porta eget ex vel pharetra. Nullam a nibh id erat ultrices finibus. Donec laoreet hendrerit velit, a maximus mauris placerat ut. Ut tristique urna mauris, a ullamcorper libero commodo a. Mauris vestibulum magna ut nisl auctor ultrices.\n" +
            "\n" +
            "Sed nisi enim, iaculis a sem non, suscipit laoreet justo. Sed rhoncus lectus blandit mattis porttitor. Maecenas sagittis semper magna sed volutpat. Vestibulum tincidunt rhoncus est. Aliquam euismod vulputate quam sit amet iaculis. Donec euismod, nibh sit amet elementum sagittis, felis mauris gravida urna, at malesuada augue augue ut orci. Pellentesque maximus feugiat diam, sit amet venenatis risus molestie sit amet. Fusce fermentum efficitur rhoncus. Proin ut ante ut justo ultrices finibus. Cras at elementum nunc, in blandit magna. Aliquam vel scelerisque libero, vel lobortis libero. Nunc mattis lorem nisi, elementum tristique arcu suscipit ut. Curabitur tortor tortor, cursus et massa quis, semper tempus velit.\n" +
            "\n" +
            "Integer fringilla ullamcorper suscipit. Phasellus luctus rhoncus libero. Vivamus metus nisl, luctus sed massa eget, rhoncus dapibus elit. Nullam at justo imperdiet, auctor sapien sit amet, ultrices metus. Quisque ut dignissim lorem, sed convallis orci. Nulla vel lacus ac quam tempus facilisis. Sed tempor tortor eu magna congue dictum. Curabitur pretium et risus at maximus. Curabitur porta nisl nec aliquet tempus. Phasellus quis faucibus sapien. Maecenas semper, elit eu porta egestas, sapien quam dapibus lacus, eu consectetur tortor dui sit amet quam.\n" +
            "\n" +
            "Pellentesque velit velit, commodo ac odio tempus, placerat vestibulum diam. Nulla congue, neque vitae ultrices pharetra, dolor dolor gravida nibh, ac hendrerit nulla orci non elit. Praesent vitae mollis orci. Pellentesque blandit, velit sed dapibus gravida, nulla magna ornare nunc, sed mollis leo augue vel quam. Vestibulum ut mi ac quam fringilla mattis ac ut mi. Proin in justo nec arcu tristique pretium quis ut enim. Cras pharetra tellus magna, ac sodales libero finibus vitae. Phasellus vehicula leo vitae lorem scelerisque tempor. ";

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna uma instância
     * de uma classe que implementa a interface {@link CompressionAlgorithm}.
     * @return Instância de uma classe que implementa um algoritmo de compressão
     */
    protected abstract CompressionAlgorithm getCompressionAlgorithm();

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * uma String que representa os bytes que formam o resultado da compressão
     * da String {@link #TEST_STRING} utilizando o algoritmo retornado pela função
     * {@link #getCompressionAlgorithm()}.
     * @return String representando os bytes da resposta do algoritmo
     */
    protected abstract String getResponseBytesString();

    /**
     * Teste de compressão de texto usando os algoritmos de compressão.
     *
     * Chama a função {@link CompressionAlgorithm#compress(byte[])} com a
     * {@link #TEST_STRING} e compara o resultado com o resultado esperado,
     * obtido pela função {@link #getResponseBytesString()}.
     */
    @Test
    public void compressionTest() {
        byte[] testStringBytes = TEST_STRING.getBytes();
        byte[] compressedStringBytes = getCompressionAlgorithm().compress(testStringBytes);
        assertTrue(compareResults(compressedStringBytes, getResponseBytesString()));
    }

    // TODO
    private boolean compareResults(byte[] compressedStringBytes, String responseBytesString) {
        return false;
    }
}
