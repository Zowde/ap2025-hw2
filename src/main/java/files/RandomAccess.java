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
        // TODO: implement
    }
}
