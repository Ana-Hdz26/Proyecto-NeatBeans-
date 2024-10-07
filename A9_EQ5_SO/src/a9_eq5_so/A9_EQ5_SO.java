package a9_eq5_so;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class A9_EQ5_SO {
    public static void main(String[] args) throws Exception {
        // Claves RSA
        KeyPair keyPair = generateKeyPair();

        // Obtención de la clave privada y pública del par de claves
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Obtención del mensaje del usuario
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe tu mensaje: ");
        String mensaje = scan.nextLine();

        // Firmar y verificar el mensaje
        byte[] firma = signMessage(mensaje, privateKey);
        boolean verificado = verifySignature(mensaje, firma, publicKey);

        // Mostrar resultados
        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Firma generada: " + Base64.getEncoder().encodeToString(firma));
        System.out.println("Firma verificada: " + verificado);
    }

    // Método para generar un par de claves RSA
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Método para firmar un mensaje con RSA
    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    // Método para verificar la firma de un mensaje con RSA
    public static boolean verifySignature(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        return verifier.verify(signature);
    }
}

