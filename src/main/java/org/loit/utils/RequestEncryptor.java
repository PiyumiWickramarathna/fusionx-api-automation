package org.loit.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RequestEncryptor {

  public static void generateKeyPair() {
    try {
      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA/NONE/PKCS1Padding", "BC");
      kpg.initialize(2048);
      KeyPair kp = kpg.generateKeyPair();
      Key pub = kp.getPublic();
      Key pvt = kp.getPrivate();

      byte[] pubKeyByteString = pub.getEncoded();
      System.out.println("Public Key Byte: " + Arrays.toString(pubKeyByteString));
      String pubKeyBase64 = Base64.getEncoder().encodeToString(pubKeyByteString);
      byte[] pvtKeyByteString = pvt.getEncoded();
      String pvtKeyBase64 = Base64.getEncoder().encodeToString(pvtKeyByteString);
      System.out.println("Public Key: " + pubKeyBase64);
      System.out.println("Private Key: " + pvtKeyBase64);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchProviderException e) {
        throw new RuntimeException(e);
    }
  }

  // Encrypt using Public Key
  public static byte[] encrypt(byte[] data, PrivateKey privateKey) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", "BC");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return cipher.doFinal(data);
  }

  // Decrypt using Private Key
  public static byte[] decrypt(byte[] data, PublicKey publicKey) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance("RSA", "BC");
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    return cipher.doFinal(data);
  }

  public static PublicKey getPublicKeyFromBytes(byte[] publicKeyBytes) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
    return keyFactory.generatePublic(new PKCS8EncodedKeySpec(publicKeyBytes));
  }

}
