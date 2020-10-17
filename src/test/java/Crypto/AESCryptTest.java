package Crypto;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AESCryptTest {


    @Test
    public void aes_test_encrypt() throws Exception {

        AESCrypt aes = new AESCrypt("k33p1TS3cret");

        String source = "This is my secret.";
        InputStream inputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8.name()));
        OutputStream outputStream = new ByteArrayOutputStream();

        aes.encrypt(2, inputStream, outputStream);

        String finalString = new String(outputStream.toString());

        System.out.println(finalString);
    }


}