package com.insight.utils.encrypt;

import com.insight.utils.pojo.base.BusinessException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author duxl
 * @date 2017-08-23
 * @remark 3DES加解密类
 */
public final class Des3Encryptor {
    private final SecretKey secretKey;

    /**
     * 使用指定的密钥键
     *
     * @param key 密钥键
     */
    public Des3Encryptor(String key) {
        try {
            secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "DES");
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 加密指定的字符串
     *
     * @param str 明文
     * @return 字节数组
     */
    public byte[] encrypt(String str) {
        var bytes = str.getBytes(StandardCharsets.UTF_8);
        return encrypt("DES/ECB/PKCS5Padding", bytes);
    }

    /**
     * 加密指定的字节数组
     *
     * @param transformation 转换格式
     * @param bytes          字节数组
     * @return 字节数组
     */
    public byte[] encrypt(String transformation, byte[] bytes) {
        try {
            var encryptCipher = Cipher.getInstance(transformation);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return encryptCipher.doFinal(bytes);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 根据指定的字节数组解密
     *
     * @param bytes 字节数组
     * @return 明文字符串
     */
    public String decrypt(byte[] bytes) {
        return decrypt("DES/ECB/NoPadding", bytes);
    }

    /**
     * 根据指定的加密字符串解密
     *
     * @param str Base64密文字符串
     * @return 明文字符串
     */
    public String decrypt(String str) {
        var bytes = Base64.decodeBase64(str);
        return decrypt("DES/ECB/NoPadding", bytes);
    }

    /**
     * 根据指定的加密字符串解密
     *
     * @param transformation 转换格式
     * @param bytes          字节数组
     * @return 明文
     */
    public String decrypt(String transformation, byte[] bytes) {
        try {
            var decryptCipher = Cipher.getInstance(transformation);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(decryptCipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
