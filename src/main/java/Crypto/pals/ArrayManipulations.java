package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayManipulations {

    public static int[] join(int[] first, int[]... rest) {
        int[] result = first;
        for (int[] next : rest) {
            result = joinTwo(result, next);
        }
        return result;
    }

    public static byte[] join(byte[] first, byte[]... rest) {
        byte[] result = first;
        for (byte[] next : rest) {
            result = joinTwo(result, next);
        }
        return result;
    }

    public static byte[] extractLastBlock(byte[] raw, int blockLength) {
        return extractBlock(raw, blockLength, countBlocks(raw, blockLength) - 1);
    }

    public static byte[] extractBlock(byte[] raw, int blockLength, int blockIndex) {
        int from = blockLength * (blockIndex);
        int to = Math.min(blockLength * (blockIndex + 1), raw.length);
        return Arrays.copyOfRange(raw, from, to);
    }

    public static void replaceBlock(byte[] dest, byte[] block, int index) {
        System.arraycopy(block, 0, dest, block.length * (index), block.length);
    }

    public static void replaceBlockCutToFit(byte[] dest, byte[] block, int index) {
        int destPosition = block.length * (index);
        int length = Math.min(block.length, dest.length - destPosition);
        System.arraycopy(block, 0, dest, destPosition, length);
    }

    public static void replaceLastBlock(byte[] dest, byte[] block) {
        replaceBlock(dest, block, countBlocks(dest, block.length) - 1);
    }

    public static int transposedBlockLength(byte[] original, int blockLength) {
        return original.length / blockLength + (original.length % blockLength == 0 ? 0 : 1);
    }

    public static byte[] transpose(byte[] original, int blockLength) {
        int newBlockLength = transposedBlockLength(original, blockLength);

        int padding = original.length % blockLength == 0 ? 0 : blockLength - original.length % blockLength;
        byte[] result = new byte[original.length + padding];

        for (int i = 0; i < original.length; i++) {
            byte value = original[i];
            int line = i / blockLength;
            int row = i % blockLength;

            int newPosition = row * newBlockLength + line;
            result[newPosition] = value;
        }
        return result;
    }

    public static void printArrayBits(byte[] content) {
        for (byte b : content) {
            System.out.print(Integer.toBinaryString(b) + " ");
        }
        System.out.println();
    }

    public static void printArrayBits(int[] content) {
        for (int i : content) {
            System.out.print(Integer.toBinaryString(i) + " ");
        }
        System.out.println();
    }

    public static int countBlocks(byte[] raw, int blockLength) {
        int result = raw.length / blockLength;
        result += raw.length % blockLength == 0 ? 0 : 1;
        return result;
    }

    public static List<byte[]> splitBlocksList(byte[] raw, int blockLength) {
        List<byte[]> result = new ArrayList<byte[]>();
        int countBlocks = countBlocks(raw, blockLength);
        for (int i = 0; i < countBlocks; i++) {
            result.add(extractBlock(raw, blockLength, i));
        }
        return result;
    }

    public static int findShortest(List<byte[]> arrays) {
        int minimumLenth = Integer.MAX_VALUE;
        for (byte[] array : arrays) {
            if (array.length < minimumLenth)
                minimumLenth = array.length;
        }
        return minimumLenth;
    }

    public static List<byte[]> copyOfAll(List<byte[]> originals, int newLenth) {
        List<byte[]> result = new ArrayList<byte[]>();
        for (byte[] original : originals) {
            result.add(Arrays.copyOf(original, newLenth));
        }
        return result;
    }

    public static byte[] joinBlocks(List<byte[]> list, int blockLength) {
        byte[] result = new byte[blockLength * list.size()];
        int index = 0;
        for (byte[] bytes : list) {
            if (bytes.length != blockLength)
                throw new IllegalStateException("The list must contain blocks of the same size.");

            replaceBlock(result, bytes, index);
            index++;
        }
        return result;
    }

    public static List<String> bytesToStrings(List<byte[]> bytes) {
        List<String> result = new ArrayList<String>();
        for (byte[] b : bytes) {
            result.add(CodecSupport.toString(b));
        }
        return result;
    }

    public static byte[] castToBytes(int[] ints) {
        byte[] result = new byte[ints.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) ints[i];
        }
        return result;
    }

    public static byte[] intToBytes(int x) {
        byte[] bytes = new byte[4];

        for (int i = 0; x != 0; i++, x >>>= 8) {
            bytes[4 - i - 1] = (byte) (x & 0xFF);
        }

        return bytes;
    }

    public static byte[] bitewiseToBytes(int[] ints) {
        byte[] result = new byte[0];
        for (int i = 0; i < ints.length; i++) {
            result = join(result, intToBytes(ints[i]));
        }
        return result;
    }

    public static int[] bitewiseToIntegers(byte[] bytes) {
        int length = bytes.length / 4 + (bytes.length % 4 == 0 ? 0 : 1);
        int[] result = new int[length];
        for (int i = 0; i < bytes.length; i++) {
            int indx = i / 4;
            result[indx] = result[indx] << 8;
            result[indx] += bytes[i] & 0xff;
        }
        //    if (bytes.length % 4!=0) for (int i = bytes.length; i<bytes.length/1 +1; i++ ) {
        //      int indx=i/4;
        //      result[indx]=result[indx] << 8;
        //    }
        return result;
    }

    public static int[] bitewiseToIntegersBigEnd(byte[] bytes) {
        int length = bytes.length / 4 + (bytes.length % 4 == 0 ? 0 : 1);
        int[] result = new int[length];
        for (int i = 0; i < bytes.length; i++) {
            int indx = i / 4;
            int piece = bytes[i] & 0xff;
            piece = piece << 8 * (i % 4);
            result[indx] += piece;
        }
        return result;
    }

    public static int findFirstDifferingByte(byte[] first, byte[] second) {
        int length = Math.min(first.length, second.length);
        for (int i = 0; i < length; i++) {
            if (first[i] != second[i])
                return i;
        }

        return -1;
    }

    public static byte[] createInitializedArray(int length, byte content) {
        byte[] result = new byte[length];
        Arrays.fill(result, content);
        return result;
    }

    public static byte[] createInitializedArray(int length, char content) {
        return createInitializedArray(length, (byte) content);
    }

    public static byte[] createInitializedArray(int length, int content) {
        return createInitializedArray(length, (byte) content);
    }

    public static byte[] reverse(byte[] input) {
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[input.length - i - 1];
        }
        return result;
    }

    private static int[] joinTwo(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private static byte[] joinTwo(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

}
