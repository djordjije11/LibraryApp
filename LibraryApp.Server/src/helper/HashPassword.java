package helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Djordjije
 */
public class HashPassword {
    private static final String ALGORITHM_MD5 = "MD5";
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
        md.update(password.getBytes());
        byte[] byteResultArray = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : byteResultArray) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
