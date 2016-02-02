package fileencryption;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;

import static fileencryption.AES.*;

/**
 * Created by megasap on 2/1/16.
 */
public class MainAESActivity {
    private String key;
    private String inputdir;
    private String outputdir;

    public MainAESActivity() {
    }

    public void setOutputdir(String outputdir) {
        this.outputdir = outputdir;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setInputdir(String inputdir) {
        this.inputdir = inputdir;
    }


    public void doEncryptAES(){
        try {
            //ENCRYPTION
            byte[] fileByte = getFileByte(inputdir);
            System.out.println("Original:" + fileByte.toString());
            byte[] encryptedFile = encryptAES(fileByte);
            System.out.println("Encrypted: " + encryptedFile.toString());
            FileOutputStream fos = new FileOutputStream(outputdir);
            fos.write(encryptedFile);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void doDecryptAES(){
        try{
            byte[] fileByte1 = getFileByte(inputdir);
            byte[] decryptedFile = decryptAES(fileByte1);
            System.out.println("Decrypted: " + decryptedFile.toString());
            FileOutputStream fos1 = new FileOutputStream(outputdir);
            fos1.write(decryptedFile);
            fos1.close();
        }catch(Exception e){
            e.printStackTrace();
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


    private byte[] encryptAES(byte[] originalFileByte){
        byte[] encryptedByte = null;
        try {
            byte[] cipherByte = encrypt(originalFileByte, key);
            encryptedByte = cipherByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedByte;
    }

    private byte[] decryptAES(byte[] cipherByte){
        byte[] decryptedByte = null;
        try {
            byte[] decipherbyte = decrypt(cipherByte, key);
            decryptedByte = decipherbyte;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedByte;

    }


}
