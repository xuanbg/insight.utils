package com.insight.utils.common;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author duxl
 * @date 2017-09-04
 * @remark Java8 Base64编解码
 */
public final class Base64Encryptor {

    private Base64Encryptor() {
    }

    /**
     * base64编码
     *
     * @param str 要编码的字符串
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        if (str == null || str.isEmpty()){
            return null;
        }

        return Base64.getEncoder().encodeToString(str.getBytes(Charset.defaultCharset()));
    }

    /**
     * base64编码
     *
     * @param bytes 要进行base64编码的byteArray
     * @return base64码
     */
    public static String encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0){
            return null;
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base64解码
     *
     * @param base64Str 要解码的字符串
     * @return 字符串
     */
    public static String decodeToString(String base64Str) {
        if (base64Str == null || base64Str.isEmpty()){
            return null;
        }

        byte[] asBytes = Base64.getDecoder().decode(base64Str);

        return new String(asBytes, Charset.defaultCharset());
    }

    /**
     * base64解码
     *
     * @param base64Encoded base64码
     * @return bytearray
     */
    public static byte[] decode(String base64Encoded) {
        if (base64Encoded == null || base64Encoded.isEmpty()){
            return null;
        }

        return Base64.getDecoder().decode(base64Encoded);
    }

    /**
     * 对url进行base64编码
     *
     * @param url url
     * @return url encoded
     */
    public static String encodeUrl(String url) {
        if (url == null || url.isEmpty()){
            return null;
        }

        return Base64.getUrlEncoder().encodeToString(url.getBytes(Charset.defaultCharset()));
    }

    /**
     * 对base64编码的url进行解码
     *
     * @param urlDecoded base64编码的url
     * @return url
     */
    public static String decodeUrl(String urlDecoded) {
        if (urlDecoded == null || urlDecoded.isEmpty()){
            return null;
        }

        byte[] asBytes = Base64.getUrlDecoder().decode(urlDecoded);

        return new String(asBytes, Charset.defaultCharset());
    }

    /**
     * mime的base64编码
     *
     * @param mime mime
     * @return mime encoded
     */
    public static String encodeMime(String mime) {
        if (mime == null || mime.isEmpty()){
            return null;
        }

        return Base64.getMimeEncoder().encodeToString(mime.getBytes(Charset.defaultCharset()));
    }

    /**
     * 对base64编码过的mime进行解码
     *
     * @param mimeEncoded mime encoded
     * @return mime
     */
    public static String decodeMime(String mimeEncoded) {
        if (mimeEncoded == null || mimeEncoded.isEmpty()){
            return null;
        }

        byte[] asBytes = Base64.getMimeDecoder().decode(mimeEncoded);

        return new String(asBytes, Charset.defaultCharset());
    }

    /**
     * 将对象实例进行序列化（base64编码）
     *
     * @param obj bean
     * @return base64码
     */
    public static String encode(Object obj) {
        if (obj == null){
            return null;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            oos.close();
            bos.close();

            return encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将base64编码过的对象反序列化为对象实例
     *
     * @param str base64码
     * @return bean
     */
    public static Object decodeToObject(String str) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(decode(str));
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            bis.close();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
