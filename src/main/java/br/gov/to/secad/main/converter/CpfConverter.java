/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.main.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author wandyer.silva
 */
@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        /*
         * Irá converter CPF formatado para um sem pontos e traço.
         * Ex.: 355.245.198-87 torna-se 35524519887.
         */
        String cpf = value;
        if (value != null && !value.equals("")) {
            cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
        }

        return cpf;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        /*
         * Irá converter CPF não formatado para um com pontos e traço.
         * Ex.: 35524519887 torna-se 355.245.198-87.
         */
        String cpf = value.toString();

        if (cpf != null) {
            Integer tamanhoCpf = cpf.trim().length();
            String zeros = "";

            while (tamanhoCpf < 11) {
                zeros = "0".concat(zeros);
                tamanhoCpf++;
            }

            String cpf2 = zeros.concat(cpf.trim());
            return (cpf2.substring(0, 3) + "." + cpf2.substring(3, 6) + "." + cpf2.substring(6, 9) + "-" + cpf2.substring(9, 11));

        }
        return "";
    }
}
