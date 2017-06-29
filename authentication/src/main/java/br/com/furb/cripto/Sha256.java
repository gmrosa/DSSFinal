package br.com.furb.cripto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {

    public static String getHash(String secret) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            md.update(secret.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String hash = String.format("%064x", new java.math.BigInteger(1, digest));

            return hash;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
