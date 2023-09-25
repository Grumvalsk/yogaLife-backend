package com.grumvalski.yogaLifebackend.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CrypLocal {
    private static CrypLocal instance = null;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String PROVIDER = "BC";
    private static final String CHIPER_ALG = "AES/GCM/NoPadding";
    private static final String KEY_FACTORY_ALG = "PBKDF2WithHmacSHA256";
    private static final String KEY_SPEC_ALG = "AES";

    private final String key;
    private final String sold;

    public static synchronized void init(String key, String sold) {
        if (instance == null) {
            instance = new CrypLocal(key, sold);
        }
    }

    public static CrypLocal getInstance() {
        return instance;
    }

    private CrypLocal(String key, String sold) {
        this.key = key;
        this.sold = sold;
    }

    public byte[] encrypt(byte[] toEncrypt) throws GeneralSecurityException, IOException {
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTORY_ALG);
        KeySpec spec = new PBEKeySpec(key.toCharArray(), sold.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secretKeySpec = new SecretKeySpec(tmp.getEncoded(), KEY_SPEC_ALG);

        Cipher cipher = Cipher.getInstance(CHIPER_ALG, PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(toEncrypt);

        byte[] encryptedIVAndText = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            baos.write(iv);
            baos.write(encrypted);

            encryptedIVAndText = baos.toByteArray();
        }

        return Base64.getEncoder().encode(encryptedIVAndText);

    }

    public String decrypt(String toDecrypt) {
        try {
            return new String(this.decrypt(toDecrypt.getBytes()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

    public byte[] decrypt(byte[] toDecrypt) throws GeneralSecurityException, IOException {

        byte[] toDecryptNoBase64 = Base64.getDecoder().decode(toDecrypt);

        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        int encryptedSize = toDecryptNoBase64.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];

        try (ByteArrayInputStream bais = new ByteArrayInputStream(toDecryptNoBase64);) {
            bais.read(iv, 0, ivSize);

            bais.read(encryptedBytes, 0, encryptedSize);
        }

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTORY_ALG);
        KeySpec spec = new PBEKeySpec(key.toCharArray(), sold.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secretKeySpec = new SecretKeySpec(tmp.getEncoded(), KEY_SPEC_ALG);

        Cipher cipherDecrypt = Cipher.getInstance(CHIPER_ALG, PROVIDER);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipherDecrypt.doFinal(encryptedBytes);
    }
}
