package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;

import java.util.Arrays;
import java.util.List;

public class SingleByteXORCipher {
    private static final char KEY_MINIMUM = 0;
    private static final char KEY_MAXIMUM = 255;

    public static String decodeOneCharacterXor(String hex) {
        DecodedResult result = decodeAssumeOneLetterLongKey(Hex.decode(hex));
        return CodecSupport.toString(result.assumeClearResult());
    }

    public static String detectOneCharacterXor(List<String> ciphers) {
        int maxScore = -1;
        DecodedResult result = null;

        for (String cipher : ciphers) {
            DecodedResult decoded = decodeAssumeOneLetterLongKey(Hex.decode(cipher));

            if (maxScore < decoded.getAsciiCharactersCount()) {
                maxScore = decoded.getAsciiCharactersCount();
                result = decoded;
            }
        }

        String decodedResult = CodecSupport.toString(result.assumeClearResult());
        System.out.println(decodedResult);
        return decodedResult;
    }

    public static DecodedResult decodeAssumeOneLetterLongKey(byte[] raw) {
        DecodedResult result = new DecodedResult(-1);

        for (char guess = KEY_MINIMUM; guess < KEY_MAXIMUM; guess++) {
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

    public  static byte[] decode(byte[] cipher, int keyLength) {
        byte[] transposedCipher = ArrayManipulations.transpose(cipher, keyLength);
        int transposedBlockLength = ArrayManipulations.transposedBlockLength(cipher, keyLength);

        byte[] joinedSolvedBlocks = new byte[transposedCipher.length];
        for (int index = 0; index * transposedBlockLength < transposedCipher.length; index++) {
            byte[] block = ArrayManipulations.extractBlock(transposedCipher, transposedBlockLength, index);
            DecodedResult decodedResult = SingleByteXORCipher.decodeAssumeOneLetterLongKey(block);
            ArrayManipulations.replaceBlock(joinedSolvedBlocks, decodedResult.getFirst(), index);
        }

        byte[] decoded = ArrayManipulations.transpose(joinedSolvedBlocks, transposedBlockLength);
        decoded = Arrays.copyOf(decoded, cipher.length);
        return decoded;
    }
}
