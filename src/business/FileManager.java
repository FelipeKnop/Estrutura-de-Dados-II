package business;

import java.io.*;

public class FileManager {

    /**
     * Lê o arquivo de entrada no formato descrito no arquivo Trabalho1.pdf:
     *
     * 7 -> número de valores de N que se seguem, por linha
     * 1000
     * 5000
     * 10000
     *  .
     *  .
     *  .
     * @param fileName Nome do arquivo a ser lido
     * @return Array de inteiros com os valores de N lidos
     */
    public static int[] readInputFile(String fileName) {
        File file = new File(fileName);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int nAmount = Integer.parseInt(bufferedReader.readLine());
            int[] nValues = new int[nAmount];
            for (int i = 0; i < nAmount; i++)
                nValues[i] = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            return nValues;
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo de entrada. Certifique-se" +
                    " de que o nome dele é " + fileName + " e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
            return null;
        } catch (IOException e) {
            System.out.println("Falha ao ler o arquivo de entrada.\n" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    /**
     * Redireciona para o arquivo de saída tudo que é passado à função
     * {@link System#out#println()}.
     * @param fileName Nome do arquivo a ser escrito
     */
    public static void redirectOutput(String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            System.setOut(new PrintStream(fileOutputStream));
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível criar o arquivo de saída.\n" + e.getMessage());
            System.exit(0);
        }
    }
}
