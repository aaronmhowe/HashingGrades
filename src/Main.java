import java.io.*;

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

//        BufferedReader exam1_Input = new BufferedReader(new FileReader("./exams_1.csv"));
//        BufferedReader exam2_Input = new BufferedReader(new FileReader("./exams_2.csv"));
//        BufferedReader homework1_Input = new BufferedReader(new FileReader("./homework_1.csv"));
//        BufferedReader homework2_Input = new BufferedReader(new FileReader("./homework_2.csv"));
//        BufferedReader quiz1_Input = new BufferedReader(new FileReader("./quizzes_1.csv"));
//        BufferedReader quiz2_Input = new BufferedReader(new FileReader("./quizzes_2.csv"));
//
//        PrintWriter detailsOutput = new PrintWriter(new FileWriter("./output - details.csv"));
//        PrintWriter summaryOutput = new PrintWriter(new FileWriter("./output - summary.csv"));
        args = new String[] {"homework_1.csv", "homework_2.csv" };

        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
