// Program to implement RSA Algorithm for Encryption and Decryption of Data

import java.util.Scanner;

class Main{
    public static void main(String args[]){
        int p = 11, q = 13, n = p * q, phi, e, d;
        phi = (p-1) * (q-1);
        e = getCoPrime(phi);
        d = getPrivateKey(e, phi);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Plain Text: ");
        String plainText = sc.nextLine();

        int cipherText[] = encrypt(plainText, e, n);

        System.out.print("Ciphertext: ");
        for (int cipher : cipherText)
            System.out.print(cipher + " ");

        String decryptedText = decrypt(cipherText, d, n);
        System.out.println("\nDecrypted Plain Text: " + decryptedText);
    }

    static int getCoPrime(int phi) {
        int e = 2;

        while (e < phi && gcd(e, phi) != 1)
            e++;

        if (gcd(e, phi) == 1)
            return e;
        return 0;
    }

    static int getPrivateKey(int e, int phi) {
        int d = 0, k = 1;

        while ((d * e) % phi != 1)
            d = (1 + k++ * phi) / e;
        
        return d;
    }

    static int[] encrypt(String plainText, int e, int n) {
        char chars[] = plainText.toCharArray();
        int cipherText[] = new int[chars.length];

        for (int i = 0; i < chars.length; i++)
            cipherText[i] = modPow((int) chars[i], e, n);

        return cipherText;
    }

    static String decrypt(int cipherText[], int d, int n) {
        StringBuilder decryptedText = new StringBuilder();

        for (int c : cipherText) {
            int charValue = modPow(c, d, n);
            decryptedText.append((char) charValue);
        }

        return decryptedText.toString();
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    static int modPow(int base, int exponent, int modulus) {
        int result = 1;
        base = base % modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1) 
                result = (result * base) % modulus;
            
            exponent = exponent >> 1; // Equivalent to exponent /= 2
            base = (base * base) % modulus;
        }
        return result;
    }
}
