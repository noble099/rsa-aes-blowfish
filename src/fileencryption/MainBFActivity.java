package fileencryption;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static fileencryption.Blowfish.*;

public class MainBFActivity {
    private String key;
    private String inputdir;
    private String outputdir;

    public MainBFActivity() {
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


    public void doEncryptBFA(){
        try {
            //ENCRYPTION
            byte[] fileByte = getFileByte(inputdir);
            System.out.println("Original:" + fileByte.toString());
            byte[] encryptedFile = encryptBF(fileByte);
            System.out.println("Encrypted: " + encryptedFile.toString());
            FileOutputStream fos = new FileOutputStream(outputdir);
            fos.write(encryptedFile);
            fos.close();
            JOptionPane.showMessageDialog(null, "Blowfish Encryption Success");
        }catch(Exception e){
            //e.printStackTrace();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Blowfish Encryption Error");
        }
    }




    public void doDecryptBFA(){
        try{
            byte[] fileByte1 = getFileByte(inputdir);
            byte[] decryptedFile = decryptBF(fileByte1);
            System.out.println("Decrypted: " + decryptedFile.toString());
            FileOutputStream fos1 = new FileOutputStream(outputdir);
            fos1.write(decryptedFile);
            fos1.close();
            JOptionPane.showMessageDialog(null, "Blowfish Decryption Success");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Blowfish Decryption Error");
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


    private byte[] encryptBF(byte[] originalFileByte){
        byte[] encryptedByte = null;
        try {
            byte[] cipherByte = encrypt(originalFileByte, key);
            encryptedByte = cipherByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedByte;
    }

    private byte[] decryptBF(byte[] cipherByte){
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
