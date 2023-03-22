package Crypto.pals;

import org.apache.shiro.codec.CodecSupport;

import java.util.ArrayList;
import java.util.List;

public class DecodedResult {
    private List<byte[]> possibleResults = new ArrayList<byte[]>();
    private int asciiCharactersCount;

    public DecodedResult(int asciiCharactersCount) {
        super();
        this.asciiCharactersCount = asciiCharactersCount;
    }

    public int possiblesCount() {
        return possibleResults.size();
    }

    public byte[] getFirst() {
        return possibleResults.get(0);
    }

    public byte[] get(int index) {
        return possibleResults.get(index);
    }

    public void addPossibleResult(byte[] possible) {
        possibleResults.add(possible);
    }

    public String getResultAsString() {
        return CodecSupport.toString(possibleResults.get(0));
    }

    public int getAsciiCharactersCount() {
        return asciiCharactersCount;
    }

    public byte[] assumeClearResult() {
        if (possiblesCount()>1)
            throw new IllegalStateException("Too many solutions found.");

        return getFirst();
    }

}