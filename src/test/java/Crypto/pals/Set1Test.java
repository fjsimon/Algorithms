package Crypto.pals;

import lombok.SneakyThrows;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    @Test
    public void challenge4() {

        List<String> lines = readFileSet1Ex4();
        String solution = "Now that the party is jumping\n";
        assertEquals(solution, SingleByteXORCipher.detectOneCharacterXor(lines));

    }

    @Test
    public void challenge5() {

        String result = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272" +
                "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
        assertEquals(result, XOR.xorThem("Burning 'em, if you ain't quick and nimble\n" +
                "I go crazy when I hear a cymbal", "ICE"));

    }

    @Test
    public void challenge6_part1() {

        String str1 = "this is a test";
        String str2 = "wokka wokka!!!";
        // function call
        assertThat(Hamming.distance(str1, str2), is(37));
    }
    @Test
    public void challenge6() {

        String expected = "I'm back and I'm ringin' the bell \n" +
                "A rockin' on the mike while the fly girls yell \n" +
                "In ecstasy in the back of me \n" +
                "Well that's my DJ Deshay cuttin' all them Z's \n" +
                "Hittin' hard and the girlies goin' crazy \n" +
                "Vanilla's on the mike, man I'm not lazy. \n\n" +
                "I'm lettin' my drug kick in \n" +
                "It controls my mouth and I begin \n" +
                "To just let it flow, let my concepts go \n" +
                "My posse's to the side yellin', Go Vanilla Go! \n\n" +
                "Smooth 'cause that's the way I will be \n" +
                "And if you don't give a damn, then \n" +
                "Why you starin' at me \n" +
                "So get off 'cause I control the stage \n" +
                "There's no dissin' allowed \n" +
                "I'm in my own phase \n" +
                "The girlies sa y they love me and that is ok \n" +
                "And I can dance better than any kid n' play \n\n" +
                "Stage 2 -- Yea the one ya' wanna listen to \n" +
                "It's off my head so let the beat play through \n" +
                "So I can funk it up and make it sound good \n" +
                "1-2-3 Yo -- Knock on some wood \n" +
                "For good luck, I like my rhymes atrocious \n" +
                "Supercalafragilisticexpialidocious \n" +
                "I'm an effect and that you can bet \n" +
                "I can take a fly girl and make her wet. \n\n" +
                "I'm like Samson -- Samson to Delilah \n" +
                "There's no denyin', You can try to hang \n" +
                "But you'll keep tryin' to get my style \n" +
                "Over and over, practice makes perfect \n" +
                "But not if you're a loafer. \n\n" +
                "You'll get nowhere, no place, no time, no girls \n" +
                "Soon -- Oh my God, homebody, you probably eat \n" +
                "Spaghetti with a spoon! Come on and say it! \n\n" +
                "VIP. Vanilla Ice yep, yep, I'm comin' hard like a rhino \n" +
                "Intoxicating so you stagger like a wino \n" +
                "So punks stop trying and girl stop cryin' \n" +
                "Vanilla Ice is sellin' and you people are buyin' \n'" +
                "Cause why the freaks are jockin' like Crazy Glue \n" +
                "Movin' and groovin' trying to sing along \n" +
                "All through the ghetto groovin' this here song \n" +
                "Now you're amazed by the VIP posse. \n\n" +
                "Steppin' so hard like a German Nazi \n"+ 
                "Startled by the bases hittin' ground \n" +
                "There's no trippin' on mine, I'm just gettin' down \n" +
                "Sparkamatic, I'm hangin' tight like a fanatic \n" +
                "You trapped me once and I thought that \n" +
                "You might have it \n" + 
                "So step down and lend me your ear \n" +
                "'89 in my time! You, '90 is my year. \n\n" +
                "You're weakenin' fast, YO! and I can tell it \n" +
                "Your body's gettin' hot, so, so I can smell it \n" +
                "So don't be mad and don't be sad \n'" +
                "Cause the lyrics belong to ICE, You can call me Dad \n" +
                "You're pitchin' a fit, so step back and endure \n" +
                "Let the witch doctor, Ice, do the dance to cure \n" +
                "So come up close and don't be square \n" +
                "You wanna battle me -- Anytime, anywhere \n\n" +
                "You thought that I was weak, Boy, you're dead wrong \n" +
                "So come on, everybody and sing this song \n\n" +
                "Say -- Play that funky music Say, go white boy, go white boy go \n" +
                "play that funky music Go white boy, go white boy, go \n" +
                "Lay down and boogie and play that funky music till you die. \n\n" +
                "Play that funky music Come on, Come on, let me hear \n" +
                "Play that funky music white boy you say it, say it \n" +
                "Play that funky music A little louder now \n" +
                "Play that funky music, white boy Come on, Come on, Come on \n" +
                "Play that funky music \n";

        byte[] rawCipher = Base64.decode(readFileSet1Ex6());
        int keySize = getKeySize(rawCipher);
        String decode = CodecSupport.toString(SingleByteXORCipher.decode(rawCipher, keySize));
        assertEquals(expected, decode);

    }

    private static int getKeySize(byte[] rawCipher) {
        double minDistance = Double.MAX_VALUE;
        int keySize = -1;

        for (int guessedKeySize = 2; guessedKeySize <= 40; guessedKeySize++) {
            int blocksCount = rawCipher.length / guessedKeySize - 1;
            double totalDistance = 0;
            for (int basedOnBlock = 0; basedOnBlock < blocksCount; basedOnBlock++) {

                byte[] firstBlock = ArrayManipulations.extractBlock(rawCipher, guessedKeySize, basedOnBlock);
                byte[] secondBlock = ArrayManipulations.extractBlock(rawCipher, guessedKeySize, basedOnBlock + 1);

                int distance = Hamming.distance(firstBlock, secondBlock);
                int maxLength = Math.max(firstBlock.length, secondBlock.length);

                totalDistance += (double) distance / (double) maxLength;

            }

            double averageDistance = totalDistance / blocksCount;
            if (minDistance > averageDistance) {
                minDistance = averageDistance;
                keySize = guessedKeySize;
            }
        }
        return keySize;
    }

    @SneakyThrows
    public List<String> readFileSet1Ex4() {
        return Files.readAllLines(Paths.get("src/test/resources/set1ex4.txt"));
    }

    @SneakyThrows
    public String readFileSet1Ex6() {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/set1ex6.txt")));
    }
}