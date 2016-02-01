package fileencryption;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {
    public static final String IV = "AAAAAAAAAAAAAAAA";

    public AES() {
    }



    public static byte[] encrypt(byte[] originalByte, String key){
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec,new IvParameterSpec(IV.getBytes("UTF-8")));
            cipherText =  cipher.doFinal(originalByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static byte[] decrypt(byte[] encryptedByte, String key){
        byte[] decryptedText = null;
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keyspec,new IvParameterSpec(IV.getBytes("UTF-8")));
            decryptedText = cipher.doFinal(encryptedByte);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    public static void main(String[] args){
            System.out.println(AES.encrypt(Base64.getEncoder().encode("QWERTABCDEFGHaasdasdasdASassdasdIJK".getBytes()), "0123456123456712").toString());

    }



}
