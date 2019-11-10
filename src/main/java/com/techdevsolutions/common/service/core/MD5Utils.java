package com.techdevsolutions.common.service.core;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String stringToHash) throws NoSuchAlgorithmException {
        return MD5Utils.md5(stringToHash.getBytes());
    }

    public static String md5(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
    }
}
