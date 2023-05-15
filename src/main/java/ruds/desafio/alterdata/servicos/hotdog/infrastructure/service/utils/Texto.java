package ruds.desafio.alterdata.servicos.hotdog.infrastructure.service.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Texto {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String texto;

    public Texto(String texto) {
        this.texto = texto;
    }

    /**
     * Define todas as primeiras letras de cada palavra maiuscula
     *
     * @return
     */
    public Texto primeirasLetrasMaiuscula() {
        try {
            String t = "";
            texto = texto.toLowerCase();
            texto = texto.trim();
            String palavras[] = texto.split(" ");
            for (int i = 0; i < palavras.length; i++) {
                t += palavras[i].substring(0, 1).toUpperCase() + palavras[i].substring(1) + " ";
            }
            texto = t.trim();
        } catch (Exception e) {

        }
        return this;
    }

    /**
     * Remove todos os acentos do texto
     *
     * @return
     */
    public Texto removeAcentuacao() {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return this;
    }

    /**
     * Remove todos os espaços brancos do texto, incluindo \n
     *
     * @return
     */
    public Texto removeEspacos() {
        texto = texto.replaceAll("\\s", "");
        return this;
    }

    /**
     * Remove todos os caracteres não numericos
     *
     * @return
     */
    public Texto removeTudoMenosNumeros() {
        texto = texto.replaceAll("[^\\d.]", "");
        texto = texto.replaceAll("\\.", "");
        return this;
    }

    /**
     * Remove todos os numeros do texto
     *
     * @return
     */
    public Texto removeNumeros() {
        texto = texto.replaceAll("\\d", "");
        return this;
    }

    /**
     * Remove todos os caracteres que não são letras ou numeros
     *
     * @return
     */
    public Texto removeTudoMenosLetrasNumeros() {
        texto = texto.replaceAll("\\W", "");
        return this;
    }

    /**
     * Remove quebras de linhas (\n)
     *
     * @return
     */
    public Texto removeQuebraLinha() {
        texto = texto.replaceAll("\\n", "");
        return this;
    }

    public Texto minusculo() {
        texto = texto.toLowerCase();
        return this;
    }

    public Texto maiusculo() {
        texto = texto.toUpperCase();
        return this;
    }

    public Texto remove(String remover) {
        return remove(remover, "");
    }

    public Texto remove(String remover, String subistituir) {
        texto = texto.replaceAll(remover, subistituir);
        return this;
    }

    public Texto inverter() {
        String novoTexto = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            novoTexto += texto.charAt(i);
        }
        texto = novoTexto;
        return this;
    }

    public Texto md5() {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(texto.getBytes(), 0, texto.length());
            BigInteger i = new BigInteger(1, m.digest());
            texto = String.format("%1$032X", i);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("Erro ao gerar a string MD5 ", ex);
        }
        return this;
    }

    /**
     * Caso o texto for maior que o tamanho informado, o texto é cortado e
     * concatenado com '...' no final
     *
     * @param tamanho
     * @return
     */
    public Texto tamanhoMaximo(int tamanho) {
        if (texto.length() > tamanho) {
            texto = texto.substring(0, tamanho) + "...";
        }
        return this;
    }

    /**
     * Verifica se o texto é nulo ou vazio
     *
     * @return true se o texto for nulo ou vazio, false caso contrário
     */
    public boolean isVazio() {
        return texto == null || texto.isEmpty();
    }

    @Override
    public String toString() {
        return texto;
    }

}
