package com.insight.util.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author duxl
 * @date 2017-09-02
 * @remark RSA加解密类
 */
public final class RsaEncryptor {
    private String KEY_ALGORITHM = "RSA";

    /**
     * 公钥
     */
    private String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCB+hGx/M9UeOAaFqdhCnv2i7YXfJkvxF3vIJhDgVsfsfh1xkb8SBTNJZWesVkSeZELGD+8tPhsABC+lZRIZ0BelUnQ62qc376GwgPeknJf2+KYj+dtryw0/aL1LkZnYlyy/qh1BUDSd1xkiroLRhSnEQMKmedZZSjjjrZYycFQkwIDAQAB";
    /**
     * 私钥
     */
    private String privateKeyString = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIH6EbH8z1R44BoWp2EKe/aLthd8mS/EXe8gmEOBWx+x+HXGRvxIFM0llZ6xWRJ5kQsYP7y0+GwAEL6VlEhnQF6VSdDrapzfvobCA96Scl/b4piP522vLDT9ovUuRmdiXLL+qHUFQNJ3XGSKugtGFKcRAwqZ51llKOOOtljJwVCTAgMBAAECgYAPkpKELLH6gPD4rzYmnkbz5xFOBxOjcXd/qvwTOnBcf8ow8iyXFVvIqHQgq0bsu7vxBFq+lUAeUHOxIeK+OgCwJ2zT6NrD2l3DF3aji7RVa61REIIYQZ6x6bc36rGHNIVYw4W4+e55RHk7qCR1G6K5XuqsOnx+yu19h6UWx+4jAQJBAObivkh2tGv98YSVZQfCq9atFkzU7fN1xg90I+v6c6Jf5RgHWdzHPs0DXIT8L8D2I54/4uAnyYHHqWg/Aq4CHDMCQQCQHWlOW7GC9TjeLISuSvzrsHa559Q//DgkyfSJ2JsAp3j5U+KzYd4qVNqYD5Xgadvx6ckCGoxiyKEjpGFKZ5ohAkBSceEUqx6d+V4Gw/oBnWgTDleKM/aMGyAQYJ57I0GrV6bMv8M87QDl4C9nZU4AD2SEm7d953czGoLJsMZaMHK1AkA2zrip0D1mLJSACjY7gtFFYUsF0KDr5KQFebjCY9ovGYHscHaa+fFf7/1iLys0uY2oJt0HK8mQF/UQdQd7IIOBAkAMEToGODFQhZLU8nz2VaCnr/8LugvR9/GDarIFreKfn4Gbu6SwTEsWHV4kUIggM5sGrCz6jQh04e/7VIQS9lg9";

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RsaEncryptor() {
        publicKey = (RSAPublicKey) getPublicKey(Base64.decodeBase64(publicKeyString));
        privateKey = (RSAPrivateKey) getPrivateKey(Base64.decodeBase64(privateKeyString));
    }

    /**
     * 获取rsa密钥对
     *
     * @return 数组，两个元素，第一个为publickey，第二个为privatekey
     */
    public String[] getInitKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            String[] keyPairString = {Base64.encodeBase64String(publicKey.getEncoded()), Base64.encodeBase64String(privateKey.getEncoded())};

            return keyPairString;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 通过公钥byte[]将公钥还原
     *
     * @param keyBytes
     * @return
     * @throws Exception
     */
    private PublicKey getPublicKey(byte[] keyBytes) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过私钥byte[]将公钥还原
     *
     * @param keyBytes
     * @return
     * @throws Exception
     */
    private PrivateKey getPrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    private String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int key_len = publicKey.getModulus().bitLength() / 8;
        String[] datas = splitString(data, key_len - 11);
        String mi = "";
        for (String s : datas) {
            mi += bcd2Str(cipher.doFinal(s.getBytes()));
        }

        return mi;
    }

    /**
     * 公钥加密
     *
     * @param data      明文
     * @param publicKey base64码
     * @return 密文
     * @throws Exception 异常
     */
    public String encryptByPublicKey(String data, String publicKey) throws Exception {
        return encryptByPublicKey(data, (RSAPublicKey) getPublicKey(Base64.decodeBase64(publicKey)));
    }

    /**
     * 默认公钥加密
     *
     * @param data 明文
     * @return 密文
     */
    public String encryptByPublicKey(String data) {
        try {
            return encryptByPublicKey(data, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 私钥解密
     *
     * @param data 密文
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 异常
     */
    public String decryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int key_len = privateKey.getModulus().bitLength() / 8;
        byte[] bytes = data.getBytes();
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
        String ming = "";
        byte[][] arrays = splitArray(bcd, key_len);
        for (byte[] arr : arrays) {
            ming += new String(cipher.doFinal(arr));
        }

        return ming;
    }

    /**
     * 私钥解密
     *
     * @param data 密文
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 异常
     */
    public String decryptByPrivateKey(String data, String privateKey) throws Exception {
        return decryptByPrivateKey(data, (RSAPrivateKey) getPrivateKey(Base64.decodeBase64(privateKey)));
    }

    /**
     * 默认私钥解密
     *
     * @param data 密文
     * @return 明文
     */
    public String decryptByPrivateKey(String data) {
        try {
            return decryptByPrivateKey(data, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ASCII码转BCD码
     *
     * @param ascii ascii bytearray
     * @param asc_len 长度
     * @return bcd bytearray
     */
    private byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }

        return bcd;
    }

    /**
     * asc转bcd
     *
     * @param asc asc码
     * @return bcd码
     */

    private byte asc_to_bcd(byte asc) {
        if ((asc >= '0') && (asc <= '9')) {
            return (byte) (asc - '0');
        }

        if ((asc >= 'A') && (asc <= 'F')) {
            return (byte) (asc - 'A' + 10);
        }

        if ((asc >= 'a') && (asc <= 'f')) {
            return (byte) (asc - 'a' + 10);
        }

        return (byte) (asc - 48);
    }

    /**
     * BCD转字符串
     *
     * @param bytes bcd bytearray
     * @return 字符串
     */
    private String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }

        return new String(temp);
    }

    /**
     * 拆分字符串为数组
     *
     * @param string 字符串
     * @param len 每组长度
     * @return 字符串数组
     */
    private String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }

        return strings;
    }

    /**
     * 拆分byte数组为二维数组
     *
     * @param data bytearray data
     * @param len 每组长度
     * @return 二维bytearray数组
     */
    private byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }

        return arrays;
    }
}
