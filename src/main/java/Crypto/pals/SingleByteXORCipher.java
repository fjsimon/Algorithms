package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;

public class SingleByteXORCipher {
    private static char keyMinimum = 0;
    private static char keyMaximum = 255;

    public static String decodeOneCharacterXor(String hex) {
        DecodedResult result = decodeAssumeOneLetterLongKey(Hex.decode(hex));
        return CodecSupport.toString(result.assumeClearResult());
    }

    public static DecodedResult decodeAssumeOneLetterLongKey(byte[] raw) {
        DecodedResult result = new DecodedResult(-1);

        for (char guess = keyMinimum; guess < keyMaximum; guess++) {
            byte[] decoded = XOR.xor(raw, guess);

            int score = ASCII.countCharacters(decoded);

            if (result.getAsciiCharactersCount() == score) {
                result.addPossibleResult(decoded);
            } else if (result.getAsciiCharactersCount() < score) {
                result = new DecodedResult(score);
                result.addPossibleResult(decoded);
            }
        }
        return result;
    }
}
