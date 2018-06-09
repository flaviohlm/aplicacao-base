package br.gov.to.secad.main.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author whelmison.rodrigues
 */
 
public class PasswordUtil {
 
    public static String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
 
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
         
        return String.format("%032x", hash);
    }
 
}