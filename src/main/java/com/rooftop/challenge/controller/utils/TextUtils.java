package com.rooftop.challenge.controller.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class TextUtils {
    
    public static HashMap<String, Integer> calculateResult(String text, int chars) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        text = text.toLowerCase();
        for (int x = 0; x <= text.length() - chars; x++) {
            String key = text.substring(x, x+chars);
            result.put(key, (result.containsKey(key) ? result.get(key) + 1 : 1));
        }
        return result;
    } 

    public static String calculateHash(String text) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        return sha3Hex;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}