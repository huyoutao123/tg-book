package org.tg.book.common.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaKeyUtils {
    public static RSAPublicKey getPublicKey(String base64String) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] b = Base64.getDecoder().decode(base64String);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(keySpec);
        return (RSAPublicKey) key;
    }

    public static RSAPrivateKey getPrivateKey(String base64String) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] b = Base64.getDecoder().decode(base64String);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePrivate(keySpec);
        return (RSAPrivateKey)key;
    }
}
