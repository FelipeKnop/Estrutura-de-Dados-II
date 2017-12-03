package compression;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * Classe que auxilia na escrita de dados binários em arquivo.
 */
public class BinaryOutputStream {

    /**
     * BufferedOutputStream para o arquivo de saída.
     */
    private final BufferedOutputStream out;

    private int buffer;
    private int n;

    public BinaryOutputStream(BufferedOutputStream out) {
        this.out = out;
    }

    private void writeBit(boolean bit) {
        buffer <<= 1;
        if (bit)
            buffer |= 1;
        n++;
        if (n == 8)
            clearBuffer();
    }

    private void writeByte(int x) {
        assert x >= 0 && x < 256;

        if (n == 0) {
            try {
                out.write(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    private void clearBuffer() {
        if (n == 0)
            return;

        if (n > 0)
            buffer <<= (8 - n);
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }

    public void flush() {
        clearBuffer();
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        flush();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(boolean x) {
        writeBit(x);
    }

    public void write(byte x) {
        writeByte(x & 0xff);
    }

    public void write(int x) {
        writeByte((x >>> 24) & 0xff);
        writeByte((x >>> 16) & 0xff);
        writeByte((x >>> 8) & 0xff);
        writeByte((x) & 0xff);
    }

    /**
     * Escreve os últimos r bits do int no arquivo de saída.
     * @param x int a ser escrito
     * @param r quantidade de bits relevantes do int
     */
    public void write(int x, int r) {
        assert r >= 1 && r <= 32;
        assert x >= 0 && x < (1 << r);

        if (r == 32) {
            write(x);
            return;
        }

        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public void write(char x) {
        assert x < 256;
        writeByte(x);
    }

    /**
     * Escreve o char de r bits no arquivo de saída.
     * @param x char a ser escrito
     * @param r quantidade de bits relevantes do char
     */
    public void write(char x, int r) {
        assert r >= 0 && r <= 16;
        assert x < (1 << r);

        if (r == 8) {
            writeByte(x);
            return;
        }

        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public void write(String s) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i));
    }

    /**
     * Escreve a String com caracteres de r bits no arquivo de saída.
     * @param s String a ser escrita
     * @param r quantidade de bits relevantes dos caracteres da String
     */
    public void write(String s, int r) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i), r);
    }
}
