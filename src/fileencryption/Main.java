/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencryption;


import static fileencryption.RSA.areKeysPresent;
import static fileencryption.RSA.decrypt;
import static fileencryption.RSA.encrypt;
import static fileencryption.RSA.generateKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megasap
 */
public class Main {

    /**
     * @param args the command line arguments
     */
     /**
   * Test the EncryptionUtil
   */
  public static final String PRIVATE_KEY_FILE = "keys/private.key";
  public static final String PUBLIC_KEY_FILE = "keys/public.key";

  public static void main(String[] args) {

    try {

      // Check if the pair of keys are present else generate those.
      if (!areKeysPresent(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE)) {
        // Method generates a pair of keys using the RSA algorithm and stores it
        // in their respective files
        generateKey(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE);
      }

      final String originalText = "Text to be encrypted ";
      
      byte[] encryptedFile = encryptRSA(originalText,PUBLIC_KEY_FILE);      
      decryptRSA(encryptedFile,PRIVATE_KEY_FILE);

     

      

      // Printing the Original, Encrypted and Decrypted Text
      
      

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    
  
  public static byte[] encryptRSA(String plainText, String publicKeyDir){
      ObjectInputStream inputStream = null;
      byte[] encryptedText = null;
      try {
          // Encrypt the string using the public key
          inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
          final PublicKey publicKey = (PublicKey) inputStream.readObject();
          final byte[] cipherText = encrypt(plainText, publicKey);
          System.out.println("Encrypted: " +cipherText.toString());
          encryptedText = cipherText;
      } catch (Exception e) {
          e.printStackTrace();
      } 
      return encryptedText;
  }
  
  public static void decryptRSA(byte[] cipherText, String privateKeyDir){
      ObjectInputStream inputStream = null;
      try {
          // Decrypt the cipher text using the private key.
          inputStream = new ObjectInputStream(new FileInputStream(privateKeyDir));
          final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
          final String plainText = decrypt(cipherText, privateKey);
          System.out.println("Decrypted: " + plainText);
      } catch (Exception e) {
          e.printStackTrace();
      }
      
  }
}
