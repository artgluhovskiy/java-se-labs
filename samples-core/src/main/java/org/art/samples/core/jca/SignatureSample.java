package org.art.samples.core.jca;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Simple example of digital signature verification.
 * 1. Generating key pair:
 *
 * keytool -genkeypair -alias securedKeyPair -keyalg RSA -keysize 2048 \
 * -dname "CN=Art" -validity 365 -storetype JKS \
 * -keystore sample_keystore.jks -storepass secured_passcode
 *
 * 2. Optional. Exporting self-signed certificate from the key store file.
 * The cert could be sent to the consumer side.
 *
 * keytool -exportcert -alias securedKeyPair -storetype JKS \
 * -keystore sample_keystore.jks -file \
 * sample_certificate.cer -rfc -storepass secured_passcode
 */
@Slf4j
public class SignatureSample {

    private static final String KEY_STORE_FILE_PATH = "samples-core/src/main/java/org/art/samples/core/jca/sample_keystore.jks";

    private static final String SECURED_PASSCODE = "secured_passcode";

    private static final String SECURED_KEY_PAIR = "securedKeyPair";

    private static final byte[] PAYLOAD = "Hello World".getBytes(StandardCharsets.UTF_8);

    private static final String RSA = "RSA";

    @SneakyThrows
    public static void main(String[] args) {
        // 1. Producer side (private key owner): Loading the private key from the key store
        PrivateKey privateKey;
        try (var keyStoreFile = new FileInputStream(KEY_STORE_FILE_PATH)) {
            var keyStore = KeyStore.getInstance("JKS");
            keyStore.load(keyStoreFile, SECURED_PASSCODE.toCharArray());
            privateKey = (PrivateKey) keyStore.getKey(SECURED_KEY_PAIR, SECURED_PASSCODE.toCharArray());
        }

        // 2. Producer side: Generating payload hash
        var md = MessageDigest.getInstance("SHA-256");
        var consumerPayloadHash = md.digest(PAYLOAD);

        // 3. Producer side: Encrypting the message hash
        var cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] producerSignature = cipher.doFinal(consumerPayloadHash);

        // 4. Consumer side (public key owner): Loading the public key from the key store. The
        PublicKey publicKey;
        try (var keyStoreFile = new FileInputStream(KEY_STORE_FILE_PATH)) {
            var keyStore = KeyStore.getInstance("JKS");
            keyStore.load(keyStoreFile, SECURED_PASSCODE.toCharArray());
            var certificate = keyStore.getCertificate(SECURED_KEY_PAIR);
            publicKey = certificate.getPublicKey();
        }

        // 5. Consumer side: Decrypting payload hash
        cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        var receivedProducerSignature = cipher.doFinal(producerSignature);


        // 6. Consumer side: Generating payload hash
        md = MessageDigest.getInstance("SHA-256");
        var producerPayloadHash = md.digest(PAYLOAD);

        // 7. Consumer side: Verifying signature
        var verified = Arrays.equals(receivedProducerSignature, producerPayloadHash);
        log.info("Signature verification result: {}", verified);
    }
}
