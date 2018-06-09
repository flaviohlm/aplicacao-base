package br.gov.to.secad.main.util;

import java.text.Normalizer;

/**
 * @author wandyer.silva
 */
public class StringUtil {

    private StringUtil() {
    }

    public static String zeroLTrim(String s) {
        if ((s == null) || (s.length() == 0)) {
            return s;
        }

        StringBuffer sb = new StringBuffer(s);
        while (sb.charAt(0) == '0') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }

    public static String rightJustify(String s, int length) {
        return rightJustify(s, length, ' ');
    }

    public static String rightJustify(String s, int length, char c) {
        StringBuffer sb = new StringBuffer((s == null) ? "" : s);
        while (sb.length() < length) {
            sb.insert(0, c);
        }
        return sb.toString();
    }

    public static String removeAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll("\\/","");
        return str;
    }
}
