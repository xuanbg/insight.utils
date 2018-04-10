package com.insight.util.test;

import com.insight.util.Json;
import com.insight.util.encrypt.Encryptor;
import com.insight.util.common.Base64Encryptor;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Base64;

import static java.lang.System.out;

public class EncryptorTest extends TestCase {

    @Test
    public void testEncryptor() throws UnsupportedEncodingException {
        String des3 = Encryptor.des3Encrypt("des3test");
        out.println("des3 enc-----" + des3);
        out.println("des3 dec-----" + Encryptor.des3Decrypt(des3));


        String aes = Encryptor.aesEncrypt("aestest");
        out.println("aes enc-----" + aes);
        out.println("aes dec-----" + Encryptor.aesDecrypt(aes));

        out.println("rsa enc-----");
        String str = Encryptor.rsaEncrypt("test");
        out.println(str);
        out.println("rsa dec-----");
        out.println(Encryptor.rsaDecrypt(str));

        String[] keyPair = Encryptor.getRsaKeyPair();
        out.println("public-----");
        out.println(keyPair[0]);
        out.println("private------");
        out.println(keyPair[1]);

        out.println("rsa enc-----");
        String str2 = Encryptor.rsaEncrypt("test2", keyPair[0]);
        out.println(str2);
        out.println("rsa dec-----");
        out.println(Encryptor.rsaDecrypt(str2, keyPair[1]));


        String base64 = Base64Encryptor.encode("base64test");

        out.println("base64-----" + base64);
        out.println("base64 dec-----" + Base64Encryptor.decode(base64));

        String base642 = Base64Encryptor.encode("中文");
        out.println("base642-----" + base642);
        out.println("base642 dec-----" + Base64Encryptor.decodeToString(base642));

        String base3 = "eyJpZCI6ImQ4NzdkZjRmNGYzMDQ4YzM5NDY0MzY0YmJiYzNjODBhIiwiYWNjb3VudElkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VySWQiOiJmYjM4MWU4YWVhM2M0YmYzYjJjZjMxODNlNzA1MDhhNiIsImRlcHRJZCI6bnVsbCwidXNlck5hbWUiOiJBUk0/Pz8/PyIsInNlY3JldCI6ImY1NDc4NzdiZWVlNzQ3ZDI4MGU4MDQ2MDkwNDY5ZGZiIiwiYXBwSWQiOiI2NGNhZDkyZjE1ZDY0N2MwYjdkZWM4NDAwODFkZjEyNyJ9";
        String base3Decs = Base64Encryptor.decodeToString(base3);
        out.println("base3s dec-----" + base3Decs);

        String accessToken = "{\"id\":\"d877df4f4f3048c39464364bbbc3c80a\",\"accountId\":\"00000000000000000000000000000000\",\"userId\":\"fb381e8aea3c4bf3b2cf3183e70508a6\",\"deptId\":null,\"userName\":\"ARM测试\",\"secret\":\"f547877beee747d280e8046090469dfb\",\"appId\":\"64cad92f15d647c0b7dec840081df127\"}";
        String accessBase64 = Base64Encryptor.encode(accessToken);
        byte[] asBytes = Base64.getDecoder().decode(accessBase64);
//        String base3Dec = new String(asBytes, "GBK");
        String base3Dec = new String(asBytes, Charset.defaultCharset());
        out.println("base3 dec-----" + base3Dec);

        //

        String b2 = "%7b+%22userId%22%3a+%22e123f307c44a4d1aa09d8d52dcdf00fb%22%2c+%22accountId%22%3a+%22222%22%2c+%22userName%22%3a+%22YangLi%22%2c+%22secret%22%3a+%2212e96464af80421395c9f2f2f10fae04%22%2c+%22index%22%3a+0%2c+%22ahead%22%3a+144978789789+%7d";
//        System.out.println("b2-----" + Base64Encryptor.decodeUrl(b2));
        String json = URLDecoder.decode(b2, "utf-8");
        out.println("b2-----" + json);
        out.println("b2-----" + Json.getValue(json, "accountId"));

    }

}
