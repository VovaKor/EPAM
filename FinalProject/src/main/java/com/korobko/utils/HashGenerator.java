package com.korobko.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vova Korobko
 */
public final class HashGenerator {
    private HashGenerator() {
    }

    private static final String SHA_512 = "SHA-512";

    public static String generateSHA512(String passFromJsp) {
        MessageDigest objSHA = null;
        try {
            objSHA = MessageDigest.getInstance(SHA_512);
        } catch (NoSuchAlgorithmException e) {
            //todo
        }
        byte[] bytSHA = objSHA.digest(passFromJsp.getBytes());
        BigInteger intNumber = new BigInteger(1, bytSHA);
        String strHashCode = intNumber.toString(16);

        // pad with 0 if the hexa digits are less then 128.
        while (strHashCode.length() < 128) {
            strHashCode = "0" + strHashCode;
        }
        return strHashCode;
    }
}
