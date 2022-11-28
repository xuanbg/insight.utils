package com.insight.utils.encrypt;

import com.insight.utils.pojo.base.BusinessException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * @author duxl
 * @date 2017-08-23
 * @remark 3DES加解密类
 */
public final class Des3Encryptor {
    /**
     * 密钥key
     */
    private static final String DEFAULT_KEY = "apindes3";

    /**
     * 加密密码功能对象
     */
    private final Cipher encryptCipher;

    /**
     * 解密密码功能对象
     */
    private final Cipher decryptCipher;


    /**
     * 默认构造函数
     */
    public Des3Encryptor() {
        this(DEFAULT_KEY);
    }

    /**
     * 使用指定的密钥键
     *
     * @param strKey 密钥键
     */
    public Des3Encryptor(String strKey) {
        try {
            DESKeySpec desKey = new DESKeySpec(strKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);

            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 加密指定的字符串
     *
     * @param str 明文
     * @return 密文
     */
    public String encrypt(String str) {
        try {
            byte[] byteMing = str.getBytes(Charset.defaultCharset());
            byte[] byteMi = encryptCipher.doFinal(byteMing);
            return Base64.encodeBase64String(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据指定的加密字符串解密
     *
     * @param strMi 密文
     * @return 明文
     */
    public String decrypt(String strMi) {
        try {
            byte[] byteMi = Base64.decodeBase64(strMi);
            byte[] byteMing = decryptCipher.doFinal(byteMi);


            return new String(byteMing, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据指定的字节数组，构造一个密钥
     *
     * @param array 字节数组
     * @return 密钥
     */
    private Key getKey(byte[] array) {
        byte[] arrayTemp = new byte[8];
        int length = array.length;

        System.arraycopy(array, 0, arrayTemp, 0, Math.min(length, 8));

        return new SecretKeySpec(arrayTemp, "DES");
    }
}
