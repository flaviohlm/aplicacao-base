package br.gov.to.secad.api.main.enumerator;

import java.io.Serializable;

/**
 *
 * @author wandyer.silva
 */
public enum MainRestMethods implements Serializable {
    TOKEN("token"),
    VALIDATE_TOKEN("validateToken"),
    VALIDATE_HASH_KEY("validateHashKey"),
    FOTO_USUARIO("fotoUsuario"),
    STATUS("status");

    private final String label;

    private MainRestMethods(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
