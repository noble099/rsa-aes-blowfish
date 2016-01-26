/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencryption;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;

import static fileencryption.RSA.*;


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

      String FILE_DIR = "file/example.txt";
      String ENCRYPTION_OUTPUT_DIR = "file/output";
      String DECRYPTION_OUTPUT_DIR = "file/output2";

     byte[] fileByte = getFileByte(FILE_DIR);

      System.out.println("Original:" + fileByte.toString());

//
//      //example of RSA
    try {

      // Check if the pair of keys are present else generate those.
      if (!areKeysPresent(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE)) {
        // Method generates a pair of keys using the RSA algorithm and stores it
        // in their respective files
        generateKey(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE);
      }


        //ENCRYPTION
      byte[] encryptedFile = encryptRSA(fileByte,PUBLIC_KEY_FILE);
        System.out.println("Encrypted: " + encryptedFile.toString());
        FileOutputStream fos = new FileOutputStream(ENCRYPTION_OUTPUT_DIR);
        fos.write(encryptedFile);
        fos.close();


        //DECRYPTION
        byte[] decryptedFile = decryptRSA(encryptedFile,PRIVATE_KEY_FILE);
        System.out.println("Decrypted: " + decryptedFile.toString());
        FileOutputStream fos1 = new FileOutputStream(DECRYPTION_OUTPUT_DIR);
        fos1.write(decryptedFile);
        fos1.close();

//      decryptRSA(encryptedFile,PRIVATE_KEY_FILE);








      // Printing the Original, Encrypted and Decrypted Text



    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    

    public static byte[] getFileByte(String filePath){
        byte[] fileByte = null;
        Path path = Paths.get(filePath);
        try {
            fileByte = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByte;
    }


  public static byte[] encryptRSA(byte[] originalFileByte, String publicKeyDir){
      ObjectInputStream inputStream = null;
      byte[] encryptedByte = null;
      try {
          // Encrypt the string using the public key
          inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
          final PublicKey publicKey = (PublicKey) inputStream.readObject();
          final byte[] cipherByte = encrypt(originalFileByte, publicKey);
          //System.out.println("Encrypted: " +cipherText.toString());
          encryptedByte = cipherByte;
      } catch (Exception e) {
          e.printStackTrace();
      } 
      return encryptedByte;
  }
  
  public static byte[] decryptRSA(byte[] cipherByte, String privateKeyDir){
      ObjectInputStream inputStream = null;
      byte[] decryptedByte = null;
      try {
          // Decrypt the cipher text using the private key.
          inputStream = new ObjectInputStream(new FileInputStream(privateKeyDir));
          final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
          final byte[] decipherbyte = decrypt(cipherByte, privateKey);
          decryptedByte = decipherbyte;
          //System.out.println("Decrypted: " + plainText);
      } catch (Exception e) {
          e.printStackTrace();
      }

      return decryptedByte;
      
  }
}
