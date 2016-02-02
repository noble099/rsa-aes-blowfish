package fileencryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Blowfish {
    static String IV = "AAAAAAAA";

//        static String plaintext = "test text 12"; /*Note null padding*/
//    static String encryptionKey = "0123456789abcdef";
//    public static void main(String [] args) {
//        try {
//
//            System.out.println("==Java==");
//            System.out.println("plain:   " + plaintext);
//
//            byte[] cipher = encrypt(plaintext.getBytes(), encryptionKey);
//
//            System.out.print("cipher:  ");
//            for (int i=0; i<cipher.length; i++)
//                System.out.print(new Integer(cipher[i])+" ");
//            System.out.println("");
//
//            byte[] decrypted = decrypt(cipher, encryptionKey);
//
//            System.out.println("decrypt: " + decrypted.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static byte[] encrypt(byte[] original, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(original);
    }

    public static byte[] decrypt(byte[] cipherText, String encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(cipherText);
    }
}