import java.util.Enumeration;
import java.util.Hashtable;

// How well is the student doing on the class and individual assignments?

/**
 * This class serves to store a student's data (name, ID#, grades)
 * @author Aaron Howe
 *
 */
public class Students {
    String name;
    String id;
    Hashtable<String, Grades> assignmentGrades;

    /**
     * Method to calculate a student's total final grade in the class
     * @return mathematical operation to calculate final grade as a percentage
     */
    public double myFinalGrade() {
        assignmentGrades = new Hashtable<>();
        Enumeration<String> keys = assignmentGrades.keys();
        double totalPointsPossible = 0.0;
        double actualPointsEarned = 0.0;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Grades value = assignmentGrades.get(key);
            // actualPointsEarned = assignmentGrades
            actualPointsEarned += value.grade;
            // totalPoints = hw1.maxGrade + hw2.maxGrade + ...
            totalPointsPossible += value.sort.getMaxPoints();
        }
        return actualPointsEarned / totalPointsPossible;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }
}
