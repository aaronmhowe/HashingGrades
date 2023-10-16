import java.util.Enumeration;
import java.util.Hashtable;

// How well is the student doing on the class and individual assignments?

/**
 * This class serves to store a student's data (name, ID#, grades)
 * @author Aaron Howe
 *
 */
public class Student {
    String name;
    String id;
    Hashtable<String, Float> inputGrades;

    public Student(Hashtable<String, Float> inputGrades, String id, String name) {
        this.inputGrades = inputGrades;
        this.name = name;
        this.id = "\"" + id + "\"";
    }

    /**
     * Method to format student data into csv
     * @return a line of student data
     */
    public String getStudentDetails() {
        Enumeration<String> keys = inputGrades.keys();
        StringBuilder line = new StringBuilder();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Float info = inputGrades.get(key);
            String quotes = String.format("%.6f", info);
            quotes = "\"" + quotes + "\"";
            line.append(quotes).append(",");
        }
        return getID() + "," + getName() + "," + line.substring(0, line.length() - 1);
    }

//    /**
//     * Method to calculate a student's total final grade in the class
//     * @return mathematical operation to calculate final grade as a percentage
//     */
//    public double myFinalGrade() {
//        assignmentGrades = new Hashtable<>();
//        Enumeration<String> keys = assignmentGrades.keys();
//        double totalPointsPossible = 0.0;
//        double actualPointsEarned = 0.0;
//        while (keys.hasMoreElements()) {
//            String key = keys.nextElement();
//            Grades value = assignmentGrades.get(key);
//            // actualPointsEarned = assignmentGrades
//            actualPointsEarned += value.grade;
//            // totalPoints = hw1.maxGrade + hw2.maxGrade + ...
//            totalPointsPossible += value.sort.getMaxPoints();
//        }
//        return actualPointsEarned / totalPointsPossible;
//    }

    public void addGrade(String assignmentType, Float assignmentGrade) {
        inputGrades.put(assignmentType, assignmentGrade);
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }
}
