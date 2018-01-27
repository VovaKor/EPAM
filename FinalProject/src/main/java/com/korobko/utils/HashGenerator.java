package com.korobko.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vova Korobko
 */
public final class HashGenerator {
    private static final Logger logger = LoggerFactory.getLogger(HashGenerator.class);
    private HashGenerator() {
    }

    private static final String SHA_512 = "SHA-512";

    /**
     * Performs the hash computation with the SHA-512 algorithm.
     * @param passFromJsp password as a string
     * @return the resulting hash value as {@code String}
     */
    public static String generateSHA512(String passFromJsp) {
        String strHashCode = null;
        try {
            MessageDigest objSHA = MessageDigest.getInstance(SHA_512);
            byte[] bytSHA = objSHA.digest(passFromJsp.getBytes());
            BigInteger intNumber = new BigInteger(1, bytSHA);
            StringBuffer stringBuffer = new StringBuffer(intNumber.toString(16));

            // pad with 0 if the hexa digits are less then 128.
            while (stringBuffer.length() < 128) {
                stringBuffer.insert(0, "0");
            }
            strHashCode = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Wrong algorithm name", e);
        }
        return strHashCode;
    }
}
