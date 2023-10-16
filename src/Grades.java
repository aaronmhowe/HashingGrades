import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class serves to collectively store student and assignment info into a nest of hash tables.
 * @author Aaron Howe
 * @version JDK 21
 */
public class Grades {
    Hashtable<String, Student> students;
    Hashtable<String, Float> overallGrade;
    // object stores the numerical grade for a given assignment
    int grade;
    int idNameOffset = 3;

    // https://www.geeksforgeeks.org/hashset-in-java/#
    Set<String> typeHeaders = new HashSet<>();

    public Grades(ArrayList<BufferedReader> files) throws IOException {
        students = new Hashtable<>();
        overallGrade = new Hashtable<>();
        String line;
        String[] overall;
        for (int i = 0; i < files.size(); i++) {
            BufferedReader file = files.get(i);
            // adds commas to the category headers on the csv output
            String[] subHeaders = file.readLine().split(",", 0);
            typeHeaders.addAll(Arrays.asList(subHeaders));
            // adds commas to the overall score on the csv output
            overall = file.readLine().split(",", 0);
            for (int j = idNameOffset; j < overall.length; j++) {
                overallGrade.put(subHeaders[j], Float.parseFloat(overall[j]));
            }
            while ((line = file.readLine()) != null) {

                String[] studentInfo = new String[2];
                float[] gradeElements = new float[line.split(",", 0).length - idNameOffset];
                sanitizeData(studentInfo, gradeElements, line);
                if (students.containsKey(studentInfo[0])) {
                    for (int k = 0; k < gradeElements.length; k++) {
                        students.get(studentInfo[0]).addGrade(subHeaders[k + idNameOffset - 1], gradeElements[k]);
                    }
                } else {
                    Hashtable<String, Float> studentGrades = new Hashtable<>();
                    for (int k = 0; k < gradeElements.length; k++) {
                        studentGrades.put(subHeaders[k + idNameOffset - 1], gradeElements[k]);
                    }
                    students.put(studentInfo[0], new Student(studentGrades, studentInfo[0], studentInfo[1]));
                }
            }
        }
    }

    /**
     * Helper method to re-format the string data for student info
     * @param studentData Student ID and Name
     * @param gradeElements Grades associated with a student
     * @param data a line in the csv
     */
    public void sanitizeData(String[] studentData, float[] gradeElements, String data) {
        String[] studentInfo = data.split(",", 0);
        studentData[0] = studentInfo[0];
        studentData[1] = studentInfo[1] + "," + studentInfo[2];
        for (int k = idNameOffset; k < studentInfo.length; k++) {
            gradeElements[k - idNameOffset] = Float.parseFloat(studentInfo[k]);
        }
    }

    /**
     * Method to transfer data from csv to csv
     * @throws IOException
     */
    public void outputInfo() throws IOException {
        Enumeration<String> keys = students.keys();
        Enumeration<String> overall = overallGrade.keys();
        StringBuilder overallData = new StringBuilder();
        PrintWriter detailsOutputFile = new PrintWriter(new FileWriter("./outputs/myOutput - details.csv"));
        PrintWriter summaryOutputFile = new PrintWriter(new FileWriter("./outputs/myOutput - summary.csv"));
        detailsOutputFile.println(String.join(",", typeHeaders));
        while (overall.hasMoreElements()) {
            String key = overall.nextElement();
            Float info = overallGrade.get(key);
            overallData.append(info).append(",");
        }
        detailsOutputFile.println(overallData);
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Student info = students.get(key);
            detailsOutputFile.println(info.getStudentDetails());

        }
        detailsOutputFile.close();

    }


}
