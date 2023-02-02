package hu.ponte.hr.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SignService {

  @Autowired
  Environment environment;
  public  String signSHA256RSA(String input) throws Exception {
    PrivateKey privateKey=getPrivateKeyFromFile();
    byte[] dataBytes  = input.getBytes();

    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initSign(privateKey);
    signature.update(dataBytes);
    byte[] signatureBytes = signature.sign();

    byte[] signatureEncodedBytes  = Base64.getEncoder().encode(signatureBytes);
    return new String(signatureEncodedBytes);
  }

  private PrivateKey getPrivateKeyFromFile( )
          throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] key = Files.readAllBytes(Paths.get(environment.getProperty("private.key")));

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
    PrivateKey finalKey = keyFactory.generatePrivate(keySpec);
    return finalKey;
  }

}
