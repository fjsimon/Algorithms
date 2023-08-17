package Crypto.hash;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

import java.time.Duration;
import java.time.Instant;

public class PasswordArgon2Jvm {

    public static void main(String[] args) {

        test1();
        
        System.out.println();
        
        test2();
    }

	private static void test1() {
		// default argon2i, salt 16 bytes, hash length 32 bytes.
        Argon2 argon2 = Argon2Factory.create();

        char[] password = "Hello World".toCharArray();

        Instant start = Instant.now();  // start timer

        try {
            // iterations = 10
            // memory = 64m
            // parallelism = 1
            String hash = argon2.hash(22, 65536, 1, password);
            System.out.println(String.format("Encoded hash is '%s'.", hash));

            // argon2 verify hash
            /*if (argon2.verify(hash, password)) {
                System.out.println("Hash matches password.");
            }*/

            //int iterations = Argon2Helper.findIterations(argon2, 1000, 65536, 1);
            //System.out.println(iterations);

        } finally {
            // Wipe confidential data
            argon2.wipeArray(password);
        }

        Instant end = Instant.now();    // end timer        
        System.out.println(String.format(
                "Process took %f s",
                Duration.between(start, end).toMillis() / 1024.0
                ));
	}
    
    private static void test2() {
        String password = "Hello World!";
        Instant beginHash = Instant.now();

        Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
        System.out.println(String.format("Creating hash for password '%s'.", password));

        String hash = argon2.hash(4, 1024 * 1024, 8, password);
        System.out.println(String.format("Encoded hash is '%s'.", hash));

        Instant endHash = Instant.now();
        System.out.println(String.format(
            "Process took %f s",
            Duration.between(beginHash, endHash).toMillis() / 1024.0
            ));

        Instant beginVerify = Instant.now();
        System.out.println("Verifying hash...");

        boolean success = argon2.verify(hash, password);
        System.out.println(success ? "Success!" : "Failure!");

        Instant endVerify = Instant.now();
        System.out.println(String.format(
            "Process took %f s",
            Duration.between(beginVerify, endVerify).toMillis() / 1024.0
            ));
    }
}