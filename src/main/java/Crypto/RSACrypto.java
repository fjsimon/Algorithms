package Crypto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSACrypto {

    public static void generateKeys() throws NoSuchAlgorithmException, IOException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(publicKey.getEncoded());
        }

        try (FileOutputStream fos = new FileOutputStream("private.key")) {
            fos.write(privateKey.getEncoded());
        }
    }

    public static void main(String... args) throws Exception {

        generateKeys();
    }
}
