package com.insight.utils.encrypt;


import com.insight.utils.common.Base64Encryptor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author duxl
 * @date 2017-08-23
 * @remark AES加解密类
 */
public final class AesEncryptor {
    /**
     * 指定AES算法
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认密码算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 密钥key
     */
    private static String defaultKey = "apinaes";
    /**
     * 加密秘钥
     */
    SecretKeySpec secretKeySpec = null;

    /**
     * 默认构造函数
     */
    public AesEncryptor() {
        this(defaultKey);
    }

    /**
     * 使用指定的密钥键
     *
     * @param key 密钥键
     */
    public AesEncryptor(String key) {
        secretKeySpec = this.getSecretKey(key);
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] byteContent = content.getBytes(Charset.defaultCharset());
            byte[] result = cipher.doFinal(byteContent);

            return Base64Encryptor.encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content 密文
     * @return 明文
     */
    public String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] result = cipher.doFinal(Base64Encryptor.decode(content));

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return SecretKeySpec
     */
    private SecretKeySpec getSecretKey(final String key) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(128, new SecureRandom(key.getBytes()));

            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
