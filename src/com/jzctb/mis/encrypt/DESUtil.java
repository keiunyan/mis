package com.jzctb.mis.encrypt;

import java.security.Key;  
import java.security.SecureRandom;
import java.security.Security;
import java.security.Provider;

import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class DESUtil {  
    private Key key = null;  
    public String hexString = "";
    		
    		
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
//	        KeyGenerator _generator = KeyGenerator.getInstance("DES");  
//	        //防止linux下 随机生成key   
//	        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
//	        secureRandom.setSeed(strKey.getBytes());  
//	        //secureRandom.setSeed(new byte[]{0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38});
//	        //secureRandom.setSeed(new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
//	        _generator.init(56,secureRandom);  
//	        this.key = _generator.generateKey();  
//	        _generator = null;  
	        
	    	byte[] kb = new byte[8];
			copyBytes(kb,strKey.getBytes("UTF-8"),8);
	        this.key = new SecretKeySpec(kb,"DES");
	    } catch (Exception e) {  
	        throw new RuntimeException(  
	            "生成key失败。 Cause: " + e);  
	    }  
    }
    
    private void copyBytes(byte[] bs1, byte[] bs2, int len){
    	int i = 0;
    	if(bs2.length<len)
    		len = bs2.length;
    	while(i<len){
    		
    		bs1[i] = bs2[i];
    		i++;
    	}
    }
    
    /** 
    * 加密 String 明文输入 ,String 密文输出 
    */  
    public String encryptStr(String strMing) {  
        byte[] byteMi = null;  
        byte[] byteMing = null;  
        String strMi = "";  
        try {  
            byteMing = strMing.getBytes("UTF8");  
            byteMi = this.encryptByte(byteMing);
            
            /* 打印16进制字符串 */
            String x = "";
            for(byte b : byteMi){
             x += String.format("%02X", b);
            }
            logger.debug("hex = ["+x+"]");
            
            hexString = x;
            strMi = Base64.encodeBase64String(byteMi);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "明文加密失败。 Cause: " + e);  
        } finally {  
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
        byte[] byteMing = null;  
        byte[] byteMi = null;  
        String strMing = "";  
        try {  
            byteMi = Base64.decodeBase64(strMi);
            
            /* 打印16进制字符串 */
            String x = "";
            for(byte b : byteMi){
             x += String.format("%02X", b);
            }
            logger.debug("hex = ["+x+"]");
            
            hexString = x;
            
            byteMing = this.decryptByte(byteMi);  
            strMing = new String(byteMing, "UTF8");  
        } catch (Exception e) {  
            throw new RuntimeException(  
                "密文解密失败。 Cause: " + e);  
        } finally {  
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
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  /*DES/ECB/PKCS5Padding*/
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
/*
		Provider[] ps =  Security.getProviders();
		 for(Provider p : ps){
			 System.out.println(p.getName());
		 }
*/
    }  
    public static Logger logger = Logger.getLogger(DESUtil.class); 
}  
