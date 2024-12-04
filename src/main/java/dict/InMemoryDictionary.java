package dict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import javax.print.DocFlavor.READER;

/**
 * Implements a persistent dictionary that can be held entirely in memory.
 * When flushed, it writes the entire dictionary back to a file.
 * <p>
 * The file format has one keyword per line:
 * <pre>word:def</pre>
 * <p>
 * Note that an empty definition list is allowed (in which case the entry would have the form: <pre>word:</pre>
 *
 * @author talm
 */
public class InMemoryDictionary extends TreeMap<String, String> implements PersistentDictionary {
    private static final long serialVersionUID = 1L;
    private File file;

    public InMemoryDictionary(File dictFile) {
        this.file = dictFile;
    }

    @Override
    public void open() throws IOException {
        try {
            if (size() == 0 && file.exists()) {  // Only load if map is empty and file exists
                FileReader fr = new FileReader(file);
                BufferedReader bf = new BufferedReader(fr);
                String line = bf.readLine();  // Read the first line

                while (line != null) {
                    int index = line.indexOf(":");
                    if (index > 0) {
                        String word = line.substring(index + 1).equals("") ? "" : line.substring(index + 1);
                        put(line.substring(0, index), word);
                    }
                    line = bf.readLine();  // Read the next line
                }
                bf.close();  // Ensure the reader is closed
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle any potential IO errors
        }
    }

    @Override
    public void close() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Write all map entries to the file
            for (Map.Entry<String, String> entry : entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle any potential IO errors
        }
    }

    @Override
    public void clear() {
        super.clear();  // Clears the map in memory

        // Try to clear the file as well by overwriting it with an empty state
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Nothing to write, effectively clears the file
        } catch (IOException e) {
            e.printStackTrace(); // Handle file write errors (consider logging instead)
        }
    }
}
