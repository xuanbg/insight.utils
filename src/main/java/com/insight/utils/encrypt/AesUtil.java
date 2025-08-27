package com.insight.utils.encrypt;

import com.insight.utils.Util;
import com.insight.utils.common.Base64Encryptor;
import com.insight.utils.pojo.base.BusinessException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author 宣炳刚
 * @date 2025/8/26
 * @remark
 */
public class AesUtil {

    /**
     * AES加密
     *
     * @param str 待加密字符串
     * @param key 密钥
     * @return 加密字符串
     */
    public static String aesEncrypt(String str, String key) {
        if (Util.isEmpty(str)) {
            return "";
        }

        try {
            var cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
            var bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64Encryptor.encode(bytes);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
