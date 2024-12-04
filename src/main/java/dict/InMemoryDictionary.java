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
    private static final long serialVersionUID = 1L; // (because we're extending a serializable class)
     private File file;
    public InMemoryDictionary(File dictFile) {
        // TODO: Implement constructor
        this.file = dictFile;

    }

    @Override
    public void open() throws IOException {
         if(size() == 0)  // assure that the treemap is empty 
         {
            
            Reader reader = new FileReader(file);
            BufferedReader bf = new BufferedReader(reader);
            String line = bf.readLine(); // extract a line from the file
            while (line != null)
            {
             int index  = line.indexOf(":");
             if(index > 0){
            put(line.substring(0, index), line.substring(index+1));
            line = bf.readLine();     
             }
             else
             {
                clear();
                break;
             }
            }

         }
        // TODO Auto-generated method stub
    
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
          FileWriter writer = new FileWriter(file);
          BufferedWriter bw = new BufferedWriter(writer);
       for (Map.Entry<String, String> entry : entrySet())
       {
            bw.write(entry.getKey()+":"+entry.getValue());
            bw.newLine();
         }
         bw.close();

    }
    @Override
    public void clear () {
        if(size() != 0) // assure that the treeset isnt empty 
        {
            super.clear();

        }
}
}