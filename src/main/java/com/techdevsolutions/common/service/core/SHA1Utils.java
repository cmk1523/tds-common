package com.techdevsolutions.common.service.core;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Utils {
    public static String sha1(String stringToHash) throws NoSuchAlgorithmException {
        return SHA1Utils.sha1(stringToHash.getBytes());
    }

    public static String sha1(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(bytes);
        return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
    }
}
