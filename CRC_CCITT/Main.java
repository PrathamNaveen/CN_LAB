// Program to implement Error Detection using CRC_CCITT (16-bits)

// Certainly! Let's explain why the CRC function is performing these specific operations:

// Initialization:

// polynomial = 0x1021: The polynomial represents the mathematical polynomial used in the CRC calculation. In the CRC-CCITT algorithm, 0x1021 is the predefined polynomial. It's used to perform XOR operations to check and correct errors in the data.

// crc = 0xFFFF: crc is initialized with 0xFFFF to set the initial state of the CRC calculation. This value is commonly used as the starting point for CRC calculations in various standards.

// Data Processing Loop:
// The for loop processes each character in the data string one at a time, which is a common approach in CRC calculations for error detection in data transmission.

// Converting Character to Byte Value:

// byteValue = (int)c: This step converts each character to its corresponding integer byte value. It's necessary to work with the binary representation of the data.
// XOR Operation:

// crc ^= (byteValue << 8) & 0xFFFF: The XOR operation combines the current state of the CRC (crc) with the left-shifted byte value (byteValue << 8). The left shift effectively appends the 8 bits of the byte value to the right side of the current CRC. The & 0xFFFF is used to ensure that the result remains a 16-bit value.

// Why?: This step effectively adds the next 8 bits of the data to the CRC calculation, which is necessary to compute the CRC accurately. It combines the current CRC state with the data in a way that can be later checked for errors.

// Bitwise CRC Calculation Loop:
// The loop processes each bit of the current byte value.

// Checking the Most Significant Bit (MSB):

// if ((crc & 0x8000) != 0): This condition checks the MSB of the CRC. If it's set (i.e., 1), it implies that the polynomial's degree aligns with the current CRC state, indicating that a division operation is needed to align the polynomial with the data.

// Why?: When the MSB of the CRC is 1, it means that the polynomial's degree lines up with the data, and a division-like operation is needed to continue the CRC calculation. This step determines whether a polynomial division operation is required.

// CRC Update based on MSB:

// crc = (crc << 1) ^ polynomial: When the MSB is set (1), the CRC is shifted left by one bit (simulating a division by x) and XORed with the polynomial.

// Why?: This step effectively performs a polynomial division operation, aligning the polynomial with the data in the CRC calculation. It ensures that the CRC reflects the division of the data by the polynomial.

// CRC Update if MSB is 0:

// else: If the MSB is not set (0), it means no polynomial division is necessary.

// crc <<= 1: In this case, the CRC is simply shifted left by one bit, effectively multiplying it by x.

// Why?: When the MSB is not set, it implies that no division is needed. Instead, the CRC is shifted left to align with the next bit of data.

// Bitwise CRC Calculation Conclusion:

// crc &= 0xFFFF: After processing all 8 bits, this step ensures that the CRC remains a 16-bit value, as per the standard for CRC-CCITT.

// Why?: This step makes sure that the CRC value stays within the limits of a 16-bit value, ensuring that it doesn't exceed that size.

// Returning CRC Value:

// return Integer.toString(crc): Finally, the calculated CRC value is returned as a string, which can be appended to the original data for transmission.

// Why?: The returned CRC is used to verify data integrity at the receiver's end, allowing for the detection of any errors that might have occurred during data transmission.

// In summary, the CRC function is designed to calculate a 16-bit CRC value using bitwise operations and a predefined polynomial (0x1021). The operations are performed to align the polynomial with the data and ensure that the CRC can be used to detect errors in transmitted data.

import java.util.Scanner;

class Main{

    static String CRC(String data){
        int polynomial = 0x1021, crc=0xFFFF;
        for (char c : data.toCharArray()){
            int byteValue = (int)c;
            crc ^= (byteValue << 8) & 0xFFFF;
            for (int i=0;i<8;i++){
                if ((crc & 0x8000) != 0)
                    crc = (crc << 1) ^ polynomial;
                else
                    crc <<= 1;
            }
            crc &= 0xFFFF;
        }
        return Integer.toString(crc);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // Sender Side
        System.out.print("Enter Data: ");
        String data = sc.nextLine().trim();
        String crc = CRC(data);
        String sentData = data + crc;
        System.out.print("Transmitted Data: " + sentData);

        //Received Data
        System.out.print("\nEnter Received Data: ");
        String receivedData = sc.nextLine().trim();
        String receivedMessage = receivedData.substring(0, receivedData.length() - 5);
        String receivedCRC = receivedData.substring(receivedData.length() - 5);
        String checkCRC = CRC(receivedMessage);

        if (checkCRC.equals(receivedCRC))
            System.out.println("Data is intact\nReceived Message: " + receivedMessage);
        else
            System.out.println("Data corrupted\nMessage Discarded");
    }
}