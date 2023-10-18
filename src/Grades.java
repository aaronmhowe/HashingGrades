import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Aaron Howe
 * PA2: Streams and Hash Tables
 */

/**
 * This class serves to collectively store student and assignment data using a hash tables, hash sets, and ArrayLists.
 * Data is then parsed and output into a master gradebook .csv, and summary gradebook .csv
 * @author Aaron Howe
 * @version JDK 21
 */
public class Grades {
    // instance of SortAssignments
    SortAssignments sort;
    // Hash table containing student data
    Hashtable<String, Student> students;
    // Hash table containing the total grade values of the details output (HW1, HW2, HW3,...)
    Hashtable<String, Float> overallGrade;
    // Hash table containing the total grade values of the summary output (Homework, Quizzes, Exams)
    Hashtable<String, Float> summaryOverallHeaders = new Hashtable<>();
    // integer offset for each index after ID and Name
    int idNameOffset = 3;

    // https://www.geeksforgeeks.org/hashset-in-java/#
    HashSet<String> typeHeaders = new HashSet<>();

    /**
     * Constructor for the Grades class reads the input .csv files from an ArrayList.
     * @param files the ArrayList of input .csv data.
     * @throws IOException when a file cannot be read.
     */
    public Grades(ArrayList<BufferedReader> files) throws IOException {
        students = new Hashtable<>();
        overallGrade = new Hashtable<>();
        String line;
        // string array holding students' overall scores for given assignments categories
        String[] overall;
        // loop through the ArrayList
        for (int i = 0; i < files.size(); i++) {
            BufferedReader file = files.get(i);
            // adds the category headers with associated commas
            String[] subHeaders = file.readLine().split(",", 0);
            typeHeaders.addAll(Arrays.asList(subHeaders));
            // looping through overall to add students' overall scores to the overallGrade hash table
            overall = file.readLine().split(",", 0);
            for (int j = idNameOffset; j < overall.length; j++) {
                overallGrade.put(subHeaders[j], Float.parseFloat(overall[j]));
            }
            // while loop reading lines of data from the input .csv files
            while ((line = file.readLine()) != null) {
                // student Name and ID are of array index length 2
                String[] studentInfo = new String[2];
                // array of grade values
                float[] gradeElements = new float[line.split(",", 0).length - idNameOffset];
                // Helper method call to re-format data in the output
                sanitizeData(studentInfo, gradeElements, line);
                // if the student hash table contains keys, loop, get, and add the associated grade and assignment
                if (students.containsKey(studentInfo[0])) {
                    for (int k = 0; k < gradeElements.length; k++) {
                        // call on "addGrade" from class Student.java adds grades for a student to a hash table
                        students.get(studentInfo[0]).addGrade(subHeaders[k + idNameOffset - 1], gradeElements[k]);
                    }
                } else {
                    // if the student hash table does not contain a key, populate a new hash table, parse, add to students
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
     * Helper method to re-format the string data for student info from the inputs
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
     * Method to transfer data from Hash Tables to ArrayLists to csv
     * @throws IOException when file cannot be read
     */
    public void outputInfo() throws IOException {
        // Enumeration object "enumerates" Hash Tables for iteration
        Enumeration<String> keys = students.keys();
        Enumeration<String> overall = overallGrade.keys();
        // Building strings for the summary data output
        StringBuilder overallData = new StringBuilder();
        // File writer objects for details and summary
        PrintWriter detailsOutputFile = new PrintWriter(new FileWriter("./outputs/myOutput - details.csv"));
        PrintWriter summaryOutputFile = new PrintWriter(new FileWriter("./outputs/myOutput - summary.csv"));
        // Removing headers as set up for sorting algorithms
        typeHeaders.remove("Name");
        typeHeaders.remove("ID");
        typeHeaders.remove("Total");
        // initialize instance of SortAssignments class
        sort = new SortAssignments(Arrays.asList(typeHeaders.toArray()));
        // ArrayList for sorting the headers, call on getStudentInfo() from SortAssignments
        ArrayList<String> newSort = sort.getStudentInfo();
        for (int i = 0; i < newSort.size(); i++) {
            if (i != (newSort.size() - 1)) {
                detailsOutputFile.print("\"" + (newSort.get(i)) + "\"" + ",");
            } else if (i == (newSort.size() - 1)){
                detailsOutputFile.println("\"" + (newSort.get(i)) + "\"");
            }
        }
        overallData.append("\"\",\"Overall\",");
        // sorting for student info
        for (int i = 0; i < newSort.size(); i++) {
            if (newSort.get(i).equals("Name") || newSort.get(i).equals("#ID")) {
                continue;
            }
            Float info = overallGrade.get(newSort.get(i));
            overallData.append("\"").append(info.toString()).append("\"").append(",");
        }
        // write summary overall headers ("#ID","Name","Final Grade",) to summary output, see method below
        summaryOutputFile.println(summaryHeaders());
        Float total = 0.0f;
        StringBuilder overallHeaders = new StringBuilder();
        overallHeaders.append("\"\",\"Overall\",\"\",");
        // loop overallGrade to condense assignment grade values to their overall grade
        while (overall.hasMoreElements()) {
            String key = overall.nextElement();
            String reducedKey = key.replaceAll("[0-9]", "");
            if (summaryOverallHeaders.containsKey(reducedKey)) {
                Float variable = summaryOverallHeaders.get(reducedKey);
                variable += (overallGrade.get(key));
                summaryOverallHeaders.put(reducedKey, variable);
            } else {
                summaryOverallHeaders.put(reducedKey, (overallGrade.get(key)));
            }
            total += overallGrade.get(key);
        }
        // loop summaryOverallHeaders to condense summary output assignment category overall grade totals
        // ("","Overall","Homework",)
        Enumeration<String> overallSub = summaryOverallHeaders.keys();
        while (overallSub.hasMoreElements()) {
            String key = overallSub.nextElement();
            overallHeaders.append("\"").append(Math.round(summaryOverallHeaders.get(key))).append("\"").append(",");
        }
        summaryOutputFile.println(overallHeaders);
        detailsOutputFile.println(overallData);
        // write student info to output
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Student info = students.get(key);
            summaryOutputFile.println(info.myTotalPoints(total));
            detailsOutputFile.println(info.getStudentDetails(newSort));
        }
        detailsOutputFile.close();
        summaryOutputFile.close();
    }

    // lazy method to add in the myOutput - summary.csv headers
    public String summaryHeaders() {
        // this runs at O(1)
        return "\"#ID\",\"Name\",\"Final Grade\",\"Homework\",\"Quizzes\",\"Exams\"";
    }
}
