package common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {
    private static final String MD5_DIGEST_NAME = "MD5";

    public static String forData(byte[] data) {
        return HexString.forBytes(createDigest().digest(data));
    }

    public static String forAsciiString(String data) {
        return HexString.forBytes(createDigest().digest(data.getBytes(StandardCharsets.US_ASCII)));
    }

    private static MessageDigest createDigest() {
        try {
            return MessageDigest.getInstance(MD5_DIGEST_NAME);
        } catch (NoSuchAlgorithmException error) {
            throw new IllegalStateException(error);
        }
    }
}
