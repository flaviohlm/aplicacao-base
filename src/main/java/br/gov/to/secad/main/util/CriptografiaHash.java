package br.gov.to.secad.main.util;

import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;

/**
 *
 * @author alex.santos
 */
public class CriptografiaHash implements Serializable {

    static public String codificar(String valor) {
        return Base64.encodeBase64String(valor.getBytes());
    }

    static public String decodificar(String valor) {
        byte[] decoded = Base64.decodeBase64(valor.getBytes());
        return new String(decoded);
    }
}
