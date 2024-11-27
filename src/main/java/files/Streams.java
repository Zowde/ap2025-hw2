package files;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

public class Streams {
    /**
     * Read from an InputStream until a quote character (") is found, then read
     * until another quote character is found and return the bytes in between the two quotes.
     * If no quote character was found return null, if only one, return the bytes from the quote to the end of the stream.
     *
     * @param in
     * @return A list containing the bytes between the first occurrence of a quote character and the second.
     */
    // dfsdfsdfsdf22222
        public static List<Byte> getQuoted(InputStream in) throws IOException {
            // TODO: Implement
             int data;
             List<Byte> ls = new ArrayList<>();
            while( (data = in.read()) != '"' && data != -1 ){
                // reading the stream until " appear or the end of stram 
            }
            if(data == -1)// " occur zero times 
                return null;
            while( (data = in.read()) != '"' && data != -1 ){
                ls.add((byte)data);
            }
            return ls;
        }


    /**
     * Read from the input until a specific string is read, return the string read up to (not including) the endMark.
     *
     * @param in      the Reader to read from
     * @param endMark the string indicating to stop reading.
     * @return The string read up to (not including) the endMark (if the endMark is not found, return up to the end of the stream).
     */
    /////asdasdadamsdasdas///
    // adam // 
    //  a    //
    public static String readUntil(Reader in, String endMark) throws IOException {
        // we will read each char to st and check if the last 4 chars are enmark
        StringBuilder st = new StringBuilder(endMark.length());
       int ch;
      String temp ="";
        while ((ch = in.read()) != -1) {
            
            st.append((char)ch);
        if(st.length() >= endMark.length() ){
           temp = st.substring(st.length()-endMark.length(), st.length());
            if(temp.compareTo(endMark) == 0) 
            {
                return st.substring(0,st.length()-endMark.length());
            } 
          }
        }
              return st.toString();
        
    }
    /**
     * Copy bytes from input to output, ignoring all occurrences of badByte.
     *
     * @param in
     * @param out
     * @param badByte
     */
    public static void filterOut(InputStream in, OutputStream out, byte badByte) throws IOException {
        // TODO: Implement
    }

    /**
     * Read a 40-bit (unsigned) integer from the stream and return it. The number is represented as five bytes,
     * with the most-significant byte first.
     * If the stream ends before 5 bytes are read, return -1.
     *
     * @param in
     * @return the number read from the stream
     */
    public static long readNumber(InputStream in) throws IOException {
        // TODO: Implement
        return 0;
    }
}
