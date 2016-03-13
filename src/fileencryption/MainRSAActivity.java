
package fileencryption;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import static fileencryption.RSA.*;

public class MainRSAActivity {

    private String privkeydir;
    private String pubkeydir;
    private String inputdir;
    private String outputdir;



    public MainRSAActivity() {
        privkeydir = "keys/private.key";
        pubkeydir = "keys/public.key";
        outputdir = "file/output";
        inputdir = "file/example";

    }

    public  void setPrivkeydir(String privkeydir) {
       this.privkeydir = privkeydir;
    }

    public  void setPubkeydir(String pubkeydir) {
        this.pubkeydir = pubkeydir;
    }

    public void setInputdir(String inputdir) {
        this.inputdir = inputdir;
    }

    public void setOutputdir(String outputdir) {
        this.outputdir = outputdir;
    }



    public void doGenerateKey(){
        generateKey(privkeydir, pubkeydir);

    }

    public void doEncryptRSA(){
        try {
            //ENCRYPTION
            byte[] fileByte = getFileByte(inputdir);
            System.out.println("Original:" + fileByte.toString());
            byte[] encryptedFile = encryptRSA(fileByte);
            System.out.println("Encrypted: " + encryptedFile.toString());
            FileOutputStream fos = new FileOutputStream(outputdir);
            fos.write(encryptedFile);
            fos.close();
            JOptionPane.showMessageDialog(null, "RSA Encryption Success");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "RSA Encryption Error");
        }
    }

    public void doDecryptRSA(){
        try{
            byte[] fileByte1 = getFileByte(inputdir);
            byte[] decryptedFile = decryptRSA(fileByte1);
            System.out.println("Decrypted: " + decryptedFile.toString());
            FileOutputStream fos1 = new FileOutputStream(outputdir);
            fos1.write(decryptedFile);
            fos1.close();
            JOptionPane.showMessageDialog(null, "RSA Decryption Success");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "RSA Decryption Error");
        }
    }


    private byte[] getFileByte(String filePath){
        byte[] fileByte = null;
        Path path = Paths.get(filePath);
        try {
            fileByte = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByte;
    }


  private byte[] encryptRSA(byte[] originalFileByte){
      ObjectInputStream inputStream = null;
      byte[] encryptedByte = null;
      try {
          // Encrypt the string using the public key
          inputStream = new ObjectInputStream(new FileInputStream(pubkeydir));
          final PublicKey publicKey = (PublicKey) inputStream.readObject();
          final byte[] cipherByte = encrypt(originalFileByte, publicKey);
          //System.out.println("Encrypted: " +cipherText.toString());
          encryptedByte = cipherByte;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return encryptedByte;
  }

  private byte[] decryptRSA(byte[] cipherByte){
      ObjectInputStream inputStream = null;
      byte[] decryptedByte = null;
      try {
          // Decrypt the cipher text using the private key.
          inputStream = new ObjectInputStream(new FileInputStream(privkeydir));
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
