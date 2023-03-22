package Crypto.pals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Set1Test {

    @Test
    public void challenge1_HexToBase64() {

        assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t",
                Base64Converter.toBase64From("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"));
    }

    @Test
    public void challenge1_Base64ToHex() {

        assertEquals("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d",
                Base64Converter.toHexFrom("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"));
    }

    @Test
    public void challenge2() {

        assertEquals("746865206b696420646f6e277420706c6179",
                XOR.xorHex("1c0111001f010100061a024b53535009181c",
                        "686974207468652062756c6c277320657965"));
    }

    @Test
    public void challenge3() {

        assertEquals("Cooking MC's like a pound of bacon",
                SingleByteXORCipher.decodeOneCharacterXor("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"));
    }
}