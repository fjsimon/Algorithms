package Crypto;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RSACryptoTest {

    @Test
    public void rsa_text_message_test() {

        RSACrypto.generateKeys();

        String secretMessage = "Keep It For You";
        String encryptedMsg = RSACrypto.encrypt(secretMessage);
        String decryptedMsg = RSACrypto.decrypt(encryptedMsg);
        assertThat(secretMessage, is(decryptedMsg));
    }

    @Test
    @SneakyThrows
    public void rsa_text_files_test() {

        Path tempFile = Files.createTempFile("temp", "txt");
            
        Files.write(tempFile, "Keep It For You".getBytes());

        byte[] fileBytes = Files.readAllBytes(tempFile);

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, RSACrypto.getPublicKey());

        byte[] encryptedFileBytes = encryptCipher.doFinal(fileBytes);

        try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
            stream.write(encryptedFileBytes);
        }

        encryptedFileBytes = Files.readAllBytes(tempFile);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, RSACrypto.getPrivateKey());
        byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes);

        try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
            stream.write(decryptedFileBytes);
        }

        String fileContent =  new String(Files.readAllBytes(tempFile));        
        assertThat("Keep It For You", is(fileContent));
    }

}