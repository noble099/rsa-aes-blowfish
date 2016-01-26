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
import java.util.Scanner;

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

      while(true){
          System.out.println("Choose 3 type of encryption below:\n");
          System.out.println("(1) RSA , (2) AES , (3) Blowfish");
          Scanner input = new Scanner(System.in);
          String encType = input.nextLine();

          char type = encType.charAt(0);

          switch (type){
              case '1':
                  RSAEncryption();
                  break;
              case '2':
                  System.out.println("Not implemented yet");
//              AESEncryption();
                  break;
              case '3':
                  System.out.println("Not implemented yet");
//              BFEncryption();
                  break;
              default:
                  break;
          }
      }





  }

    public static void RSAEncryption(){
        String pubkeydir = "";
        String privkeydir = "";
        String FILE_DIR = "file/example.txt";
        String OUTPUT_DIR = "";
        System.out.println("Choose (1)encryption or (2)decryption");
        Scanner input = new Scanner(System.in);
        String procedure = input.nextLine();
        char proc = procedure.charAt(0);


//            // Check if the pair of keys are present else generate those.
//            if (!areKeysPresent(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE)) {
//                // Method generates a pair of keys using the RSA algorithm and stores it
//                // in their respective files
//                generateKey(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE);
//            }


        switch(proc){
            case  '1':
                System.out.println("Enter directory to ORIGINAL file");
                FILE_DIR = input.nextLine();
                System.out.println("Enter directory to PUBLIC key");
                pubkeydir = input.nextLine();
                System.out.println("Enter directory to OUTPUT file");
                OUTPUT_DIR = input.nextLine();
            try {
                //ENCRYPTION
                byte[] fileByte = getFileByte(FILE_DIR);
                System.out.println("Original:" + fileByte.toString());
                byte[] encryptedFile = encryptRSA(fileByte,pubkeydir);
                System.out.println("Encrypted: " + encryptedFile.toString());
                FileOutputStream fos = new FileOutputStream(OUTPUT_DIR);
                fos.write(encryptedFile);
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }

                break;
            case   '2':
                try{
                    System.out.println("Enter directory to ENCRYPTED file");
                    FILE_DIR = input.nextLine();
                    System.out.println("Enter directory to PRIVATE key");
                    privkeydir = input.nextLine();
                    System.out.println("Enter directory to OUTPUT file");
                    OUTPUT_DIR = input.nextLine();
                    byte[] fileByte1 = getFileByte(FILE_DIR);
                    byte[] decryptedFile = decryptRSA(fileByte1,privkeydir);
                    System.out.println("Decrypted: " + decryptedFile.toString());
                    FileOutputStream fos1 = new FileOutputStream(OUTPUT_DIR);
                    fos1.write(decryptedFile);
                    fos1.close();
                }catch(Exception e){

                }
                break;

        }




        try {





            //DECRYPTION

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
