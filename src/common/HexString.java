package common;

public class HexString {
    private static final char[] HEX_CHARACTERS = new char[16];

    static {
        for (var index = 0; index < 16; ++index) {
            HEX_CHARACTERS[index] = Character.forDigit(index, 16);
        }
    }

    public static String forBytes(byte[] data) {
        StringBuilder sb = new StringBuilder(2 * data.length);
        for (byte value : data) {
            sb.append(HEX_CHARACTERS[(value >>> 4) & 0xf]);
            sb.append(HEX_CHARACTERS[value & 0xf]);
        }
        return sb.toString();
    }

    public static String forByte(byte value) {
        return "" + HEX_CHARACTERS[(value >>> 4) & 0xf] + HEX_CHARACTERS[value & 0xf];
    }
}
