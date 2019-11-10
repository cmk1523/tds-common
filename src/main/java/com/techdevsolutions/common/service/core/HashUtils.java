package com.techdevsolutions.common.service.core;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static String md5(File file) throws IOException, NoSuchAlgorithmException {
        if (!file.exists()) {
            throw new IOException("File does not exist: " + file.getAbsolutePath());
        }

        byte[] bytes = new byte[(int) file.length()];

        return md5(bytes);
    }

    public static String md5(byte[] bytes) throws IllegalArgumentException, NoSuchAlgorithmException {
        return HashUtils.hash("MD5", bytes);
    }

    public static String sha1(byte[] bytes) throws IllegalArgumentException, NoSuchAlgorithmException {
        return HashUtils.hash("SHA-1", bytes);
    }

    public static String sha256(byte[] bytes) throws IllegalArgumentException, NoSuchAlgorithmException {
        return HashUtils.hash("SHA-256", bytes);
    }

    public static String sha512(byte[] bytes) throws IllegalArgumentException, NoSuchAlgorithmException {
        return HashUtils.hash("SHA-512", bytes);
    }

    public static String hash(String type, byte[] bytes) throws IllegalArgumentException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException("type is null or empty");
        }

        MessageDigest md = MessageDigest.getInstance(type);
        md.update(bytes);
        return HashUtils.bytesToHexString(md.digest());
    }

    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes is null");
        }

        return DatatypeConverter.printHexBinary(bytes).toUpperCase();
    }
}
