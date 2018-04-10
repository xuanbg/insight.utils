package com.insight.util.encrypt;

/**
 * @author duxl
 * @date 2017-08-23
 * @remark 加解密工具类
 */
public final class Encryptor {
    /**
     * des3加解密工具类
     * new Des3Encryptor(key)：key为可配置的密钥
     */
    private static Des3Encryptor des3Encryptor = new Des3Encryptor();
    /**
     * aes加解密工具类
     * new AesEncryptor(key)：key为可配置的密钥
     */
    private static AesEncryptor aesEncryptor = new AesEncryptor();
    /**
     * aes加解密工具类
     */
    private static RsaEncryptor rsaEncryptor = new RsaEncryptor();

    private Encryptor() {
    }

    /**
     * 对字符串进行加密
     *
     * @param key 字符串
     * @return des3密文
     */
    public static String des3Encrypt(String key) {
        return des3Encryptor.encrypt(key);
    }

    /**
     * 对密文进行解密
     *
     * @param value 密文
     * @return 明文字符串
     */
    public static String des3Decrypt(String value) {
        return des3Encryptor.decrypt(value);
    }

    /**
     * 对字符串进行加密
     *
     * @param key 字符串
     * @return 密文
     */
    public static String aesEncrypt(String key) {
        return aesEncryptor.encrypt(key);
    }

    /**
     * 对密文进行解密
     *
     * @param value 密文
     * @return 明文字符串
     */
    public static String aesDecrypt(String value) {
        return aesEncryptor.decrypt(value);
    }

    /**
     * 默认rsa签名
     *
     * @param data 字符串
     * @return 密文
     */
    public static String rsaEncrypt(String data) {
        return rsaEncryptor.encryptByPublicKey(data);
    }

    /**
     * 默认rsa解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String rsaDecrypt(String data) {
        return rsaEncryptor.decryptByPrivateKey(data);
    }

    /**
     * 获取rsa密钥对
     *
     * @return KeyPair数组，0为public key，1为private key
     */
    public static String[] getRsaKeyPair() {
        return rsaEncryptor.getInitKeyPair();
    }

    /**
     * rsa签名
     *
     * @param data 要签名的数据
     * @param publicKey 公钥
     * @return 密文
     */
    public static String rsaEncrypt(String data, String publicKey) {
        return rsaEncryptor.encryptByPublicKey(data);
    }

    /**
     * rsa解密
     *
     * @param data 密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String rsaDecrypt(String data, String privateKey) {
        return rsaEncryptor.decryptByPrivateKey(data);
    }
}
