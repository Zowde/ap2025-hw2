package files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    /**
     * Treat the file as an array of (unsigned) 8-bit values and sort them
     * in-place using a bubble-sort algorithm.
     * You may not read the whole file into memory!
     *
     * @param file
     */
    public static void sortBytes(RandomAccessFile file) throws IOException {
        long fileLength = file.length();
        byte b1, b2;
    
        // Bubble sort algorithm
        for (int i = 0; i < fileLength - 1; i++) {
            for (int j = 0; j < fileLength - i - 1; j++) {
                
                // Seek to the current byte (j)
                file.seek(j);
                b1 = file.readByte();
                int unsignedb1 = b1 & 0xFF; // Convert b1 to unsigned
                
                // Seek to the next byte (j + 1)
                file.seek(j + 1);
                b2 = file.readByte();
                int unsignedb2 = b2 & 0xFF; // Convert b2 to unsigned
                
                // If b1 > b2, swap them
                if (unsignedb1 > unsignedb2) {
                    // Swap bytes at positions j and j + 1
                    
                    // Write b2 at position j
                    file.seek(j);
                    file.writeByte(b2);
    
                    // Write b1 at position j + 1
                    file.seek(j + 1);
                    file.writeByte(b1);
                }
            }
        }
    }
    
    

    /**
     * Treat the file as an array of unsigned 24-bit values (stored MSB first) and sort
     * them in-place using a bubble-sort algorithm.
     * You may not read the whole file into memory!
     *
     * @param file
     * @throws IOException
     */
    public static void sortTriBytes(RandomAccessFile file) throws IOException {
        long fileLength = file.length();
        
        // Bubble sort algorithm for 24-bit unsigned values (3 bytes)
        for (int i = 0; i < fileLength - 3; i += 3) {
            for (int j = 0; j < fileLength - i - 3; j += 3) {
                
                // Read the 3 bytes representing the 24-bit unsigned value at position j
                file.seek(j);
                byte b1 = file.readByte();
                byte b2 = file.readByte();
                byte b3 = file.readByte();
                
                // Combine the 3 bytes into a 24-bit unsigned integer
                int unsignedValue1 = ((b1 & 0xFF) << 16) | ((b2 & 0xFF) << 8) | (b3 & 0xFF);
                
                // Read the 3 bytes representing the next 24-bit unsigned value at position j + 3
                file.seek(j + 3);
                byte b4 = file.readByte();
                byte b5 = file.readByte();
                byte b6 = file.readByte();
                
                // Combine the 3 bytes into another 24-bit unsigned integer
                int unsignedValue2 = ((b4 & 0xFF) << 16) | ((b5 & 0xFF) << 8) | (b6 & 0xFF);
                
                // If unsignedValue1 > unsignedValue2, swap the 3 bytes
                if (unsignedValue1 > unsignedValue2) {
                    // Swap bytes at positions j and j + 3
                    
                    // Write the smaller value (unsignedValue2) at position j
                    file.seek(j);
                    file.writeByte(b4);
                    file.writeByte(b5);
                    file.writeByte(b6);
                    
                    // Write the larger value (unsignedValue1) at position j + 3
                    file.seek(j + 3);
                    file.writeByte(b1);
                    file.writeByte(b2);
                    file.writeByte(b3);
                }
            }
        }
    }
}
