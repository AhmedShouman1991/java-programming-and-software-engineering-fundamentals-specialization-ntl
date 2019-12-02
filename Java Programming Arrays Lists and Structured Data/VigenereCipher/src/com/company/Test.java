package com.company;

import edu.duke.FileResource;

import java.util.Arrays;

public class Test {

    // this Method to test the CaesarCipherClass encrypt and decrypt Methods\\
    public void testCaesarCipherClass() {
        CaesarCipher cc = new CaesarCipher(4);
        FileResource f = new FileResource();
        String massage = f.asString();
        System.out.println("Encrypted Massage : ");
        System.out.println(cc.encrypt(massage));
        System.out.println("\n");
        System.out.println("Decrypted Massage : ");
        System.out.println(cc.decrypt(cc.encrypt(massage)));
    }


    public void testCaesarCracker() {
        CaesarCracker ck = new CaesarCracker('a');
        FileResource f = new FileResource();
        String encrypted = f.asString();
        int key = ck.getKey(encrypted);
        System.out.println("Encryption key = " + key);
        System.out.println("decrypted massage : " );
        System.out.println(ck.decrypt(encrypted));
    }

    public void testVigenereCipher() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        FileResource f = new FileResource();
        String msg = f.asString();
        String encryptedMsg = vc.encrypt(msg);
        System.out.println("vig Encrypted msg : ");
        System.out.println(encryptedMsg);
        System.out.println("\n");
        System.out.println("vig Decrypted msg : ");
        System.out.println(vc.decrypt(vc.encrypt(msg)));
    }

    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource f = new FileResource();
        String enMsg = f.asString();
        int [] key = vb.tryKeyLength(enMsg,4,'e');
        System.out.println(Arrays.toString(key));
    }

    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.breakVigenere());
    }

}
