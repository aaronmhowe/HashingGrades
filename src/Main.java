
import java.io.*;
import java.util.ArrayList;

/**
 * Main class to run the application method
 * @author Aaron Howe
 * @version JDK 21
 */
public class Main {
    /**
     * Main application method to produce the file output for the hashed
     * graded assignments
     * @param args array of command line arguments
     * @throws IOException throws when a file cannot be written
     */
    public static void main(String[] args) throws IOException {
        ArrayList<BufferedReader> fileContents = new ArrayList<>();

        for (String arg : args) {
            BufferedReader input = new BufferedReader(new FileReader(arg));
            fileContents.add(input);
        }
        Grades gradeClass = new Grades(fileContents);
        gradeClass.outputInfo();
    }
}
