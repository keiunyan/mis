package com.jzctb.mis.encrypt;

import java.security.Key;  
import java.security.SecureRandom;  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import org.apache.commons.codec.binary.Base64;

public class DESUtil {  
    private Key key;  

    public DESUtil() {  

    }  

    public DESUtil(String str) {  
        setKey(str); // 生成密匙  
    }  

    public Key getKey() {  
        return key;  
    }  

    public void setKey(Key key) {  
        this.key = key;  
    }  

    /** 
    * 根据参数生成 KEY   
    */  
    public void setKey (String strKey) {  
    try {  
        KeyGenerator _generator = KeyGenerator.getInstance("DES");  
        //防止linux下 随机生成key   
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
        secureRandom.setSeed(strKey.getBytes());  
          
        _generator.init(56,secureRandom);  
        this.key = _generator.generateKey();  
        _generator = null;  
    } catch (Exception e) {  
        throw new RuntimeException(  
            "生成key失败。 Cause: " + e);  
    }  
    }

    /** 
    * 加密 String 明文输入 ,String 密文输出 
    */  
    public String encryptStr(String strMing) {  
        byte[] byteMi = null;  
        byte[] byteMing = null;  
        String strMi = "";  
        Base64 base64 = new Base64();
        try {  
            byteMing = strMing.getBytes("UTF8");  
            byteMi = this.encryptByte(byteMing);  
            strMi = base64.encodeBase64String(byteMi);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "明文加密失败。 Cause: " + e);  
        } finally {  
            base64 = null;  
            byteMing = null;  
            byteMi = null;  
        }  
        return strMi;  
    }  

    /** 
    * 解密 以 String 密文输入 ,String 明文输出 
    * 
    * @param strMi 
    * @return 
    */  
    public String decryptStr(String strMi) {  
        Base64 base64 = new Base64();
        byte[] byteMing = null;  
        byte[] byteMi = null;  
        String strMing = "";  
        try {  
            byteMi = base64.decodeBase64(strMi);  
            byteMing = this.decryptByte(byteMi);  
            strMing = new String(byteMing, "UTF8");  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "密文解密失败。 Cause: " + e);  
        } finally {  
            base64 = null;  
            byteMing = null;  
            byteMi = null;  
        }  
        return strMing;  
    }  

    /** 
    * 加密以 byte[] 明文输入 ,byte[] 密文输出 
    * 
    * @param byteS 
    * @return 
    */  
    private byte[] encryptByte(byte[] byteS) {  
        byte[] byteFina = null;  
        Cipher cipher;  
        try {  
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            byteFina = cipher.doFinal(byteS);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "字节加密失败。 Cause: " + e);  
        } finally {  
            cipher = null;  
        }  
        return byteFina;  
    }  

    /** 
    * 解密以 byte[] 密文输入 , 以 byte[] 明文输出 
    * 
    * @param byteD 
    * @return 
    */  
    private byte[] decryptByte(byte[] byteD) {  
        Cipher cipher;  
        byte[] byteFina = null;  
        try {  
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            byteFina = cipher.doFinal(byteD);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "字节解密失败。 Cause: " + e);  
        } finally {  
            cipher = null;  
        }  
        return byteFina;  
    }  

    public static void main(String[] args) throws Exception {  
        DESUtil des = new DESUtil("12345678");  
        String str1 = "1234567890ABCDEF";  
        // DES 加密字符串  
        String str2 = des.encryptStr(str1);  
        // DES 解密字符串  
        String deStr = des.decryptStr(str2);  
        System.out.println(" 加密前： " + str1);  
        System.out.println(" 加密后： " + str2);  
        System.out.println(" 加密后长度： " + str2.length());  
        System.out.println(" 解密后： " + deStr);  
    }  
}  
