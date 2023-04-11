package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;

class Hamming {
    // function to calculate Hamming distance
    public static int distance(String first, String second) {
        return distance(CodecSupport.toBytes(first), CodecSupport.toBytes(second));
    }

    public static int distance(byte[] first, byte[] second) {
        int minLength = Math.min(first.length, second.length);
        int maxLength = Math.max(first.length, second.length);

        int result = 0;
        for (int i = 0; i < minLength; i++) {
            int xor = first[i] ^ second[i];
            result += Integer.bitCount(xor);
        }

        result += 8 * (maxLength - minLength);

        return result;
    }
}

