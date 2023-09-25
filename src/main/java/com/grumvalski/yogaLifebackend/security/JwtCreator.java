package com.grumvalski.yogaLifebackend.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtCreator {

    public static String createJwt(String subject, String issuer ) {
        try {
            PrivateKey privateKey=JwtCreator.loadPrivateKey();
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(subject)
                    .issuer(issuer)
                    .expirationTime(new Date(new Date().getTime() + 3600 * 1000)) // Scadenza in un'ora
                    .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .build();

            JWSSigner signer = new RSASSASigner(privateKey);

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo per verificare un JWT utilizzando la chiave pubblica
    public static boolean verifyJwt(String jwtString ) {
        try {
            PublicKey publicKey=JwtCreator.loadPublicKey();
            SignedJWT jwt = SignedJWT.parse(jwtString);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);

            return jwt.verify(verifier);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PrivateKey loadPrivateKey() throws Exception {
        try (InputStream inputStream = new ClassPathResource("private_key.pem").getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (IOException e) {
            throw new Exception("Errore durante il caricamento della chiave privata.", e);
        }
    }
    public  static PublicKey loadPublicKey() throws Exception {
        try (InputStream inputStream = new ClassPathResource("public_key.pem").getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (IOException e) {
            throw new Exception("Errore durante il caricamento della chiave pubblica.", e);
        }
    }
}
