package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;
import java.util.Arrays;

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

