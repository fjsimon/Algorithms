package Crypto;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RSACryptoTest {

    @Test
    public void rsa_message_test() throws Exception {

        RSACrypto.generateKeys();

        String secretMessage = "Keep It For You";
        String encryptedMsg = RSACrypto.encrypt(secretMessage);
        String decryptedMsg = RSACrypto.decrypt(encryptedMsg);
        assertThat(secretMessage, is(decryptedMsg));
    }

}