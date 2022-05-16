package panoplie.orgoal.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//SHA-256 암호화 할때 사용
//자세한 코드는 나도 모름, 복붙한거임
public class SHA256 {

    public static String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
