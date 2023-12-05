
/**
 * Creates the BitOutputStream object and 
 * defines properties and functions of this
 * class which include printing bits onto 
 * a file.
 * 
 * @author Nandita Raj Kumar
 * @since September 4, 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class BitOutputStream {

    FileOutputStream output;
    int oneByte = 0b00000000;
    int bitPosition = 0;

    /**
     * Creates a BitOutputStream to write bits to the file.
     * 
     * @param f for a file name
     * @throws FileNotFoundException
     */
    BitOutputStream(File f) throws FileNotFoundException {

        output = new FileOutputStream(f);

    }

    /**
     * Writes a single bit onto a file.
     * 
     * @param bit representing a single bit
     * @throws IOException
     */
    void writeBit(char bit) throws IOException {

        if (bit != '0' && bit != '1') {
            throw new IllegalArgumentException("Not a bit");
        }
        int bitInt = Character.getNumericValue(bit);

        oneByte = oneByte << 1;

        oneByte = oneByte | bitInt;

        bitPosition++;

        if (bitPosition == 8) {
            output.write(oneByte);
            oneByte = 0;
            bitPosition = 0;

        }
    }

    /**
     * Writes a string of bits onto a file.
     * 
     * @param bitString representinga a string of bits
     * @throws IOException
     */
    void writeBits(String bitString) throws IOException {

        for (int i = 0; i < bitString.length(); i++) {
            writeBit(bitString.charAt(i));
        }

    }

    /**
     * Closes the output stream.
     * 
     * @throws IOException
     */
    void close() throws IOException {
        while (bitPosition != 0) {
            writeBit('0');
        }

        output.close();

    }

}
