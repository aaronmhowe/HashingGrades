import java.util.*;
/**
 * This class serves to store a student's data (name, ID#, grades)
 * @author Aaron Howe
 * @version JDK 21
 */
public class Student {
    SortAssignments sort;
    String name;
    String id;
    Hashtable<String, Float> inputGrades;

    /**
     * Basic Constructor for Student.
     * @param inputGrades Hash Table for assignment grades
     * @param id Student ID
     * @param name Student Name
     */
    public Student(Hashtable<String, Float> inputGrades, String id, String name) {
        this.inputGrades = inputGrades;
        this.name = name;
        this.id = "\"" + id + "\"";
    }

    /**
     * Method to format student data into csv.
     * @param headers ArrayList to organize headers
     * @return student data
     */
    public String getStudentDetails(ArrayList<String> headers) {
        StringBuilder line = new StringBuilder();
        Object[] arr = headers.toArray();
        // Looping array list, excluding Name, ID, and Total
        for (int i = 0; i < headers.size(); i++) {
            String key = (String) arr[i];
            if ((Objects.equals(key, "Name")) || (Objects.equals(key, "#ID")) || (Objects.equals(key, "Total"))) {
                continue;
            }
            // Format each grade with 6 decimal places
            String quotes = String.format("%.6f", inputGrades.get(key));
            quotes = "\"" + quotes + "\"";
            line.append(quotes).append(",");
        }
        // Take the now formatted grades and bring back Student Name and ID
        return getID() + "," + getName() + "," + line.substring(0, line.length() - 1);
    }
    /**
     * Method to return the total possible points for an assignment category.
     * @param category the Assignment category as a string
     * @return the sum of the key, value pairs
     */
    public Float getTotalPoints(String category) {
        // enumeration object generates a series of elements
        // sending those elements to the hashtable object
        Enumeration<String> keys = inputGrades.keys();
        Float points = 0.0f;
        // searching function for the hash
        while (keys.hasMoreElements()) {
            // iterator
            String key = keys.nextElement();
            String reducedKey = key.replaceAll("[0-9]","");
            if (category.equals(reducedKey)) {
                // value of a key
                Float value = inputGrades.get(key);
                // sum up the total possible points for an assignment category
                points += value;
            }
        }
        // return total possible points
        return points;
    }

    /**
     * Method to calculate a student's final grade in the class with proper formatting
     * @param total Total points calculated from every assignment category
     * @return Properly formatted students' final grade
     */
    public String myTotalPoints(Float total) {
        StringBuilder studentGrades = new StringBuilder();
        StringBuilder subString = new StringBuilder();
        Float totalPoints = 0.0f;
        Float finalGrade = 0.0f;
        String floatConverter;
        String[] categories = new String[]{"HW", "Quiz", "E"};
        Float actualPointsEarned = 0.0f;
        studentGrades.append(getID()).append(",").append(getName()).append(",");
        // looping string array containing HW, Quiz, and E
        for (int i = 0; i < categories.length; i++) {
            totalPoints = getTotalPoints(categories[i]);
            // summing up points earned
            actualPointsEarned += totalPoints;
            subString.append(String.format("%.6f", totalPoints)).append(",");
        }
        // calculation for a student's final grade
        finalGrade = (actualPointsEarned / total) * 100.0f;
        // rounding to 2 decimal places
        floatConverter = String.format("%.2f", finalGrade);
        // lazily adding zero's (sorry)
        studentGrades.append("\"").append(floatConverter).append("0000").append("\"").append(",");
        studentGrades.append(subString);
        return studentGrades.toString();
    }

    /**
     * Method to add students' assignments with associated grades to a Hash Table.
     * @param assignmentType type of assignment (hw, quiz, exam)
     * @param assignmentGrade associated grade
     */
    public void addGrade(String assignmentType, Float assignmentGrade) {
        if (Objects.equals(assignmentType, "Name") || Objects.equals(assignmentType, "ID") || Objects.equals(assignmentType, "Total")) {
            return;
        }
        inputGrades.put(assignmentType, assignmentGrade);
    }

    /**
     * Getter method for student name
     * @return Student name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for student ID
     * @return Student ID
     */
    public String getID() {
        return id;
    }
}
