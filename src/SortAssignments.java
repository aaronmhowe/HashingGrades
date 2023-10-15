import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class serves to calculate the maximum score that can be achieved
 * across each assignment category (homework, quizzes, exams)
 * @author Aaron Howe
 * @version JDK 21
 */
public class SortAssignments {
    String assignmentType;
    // hash table of assignments
    Hashtable<String, Assignments> assignmentList;

    /**
     * Method to return the total possible points for an assignment category
     * @return the sum of the key, value pairs
     */
    public double getMaxPoints() {
        assignmentList = new Hashtable<>();
        // enumeration object generates a series of elements
        // sending those elements to the hashtable object
        Enumeration<String> keys = assignmentList.keys();
        double points = 0.0;
        // searching function for the hash
        while (keys.hasMoreElements()) {
            // iterator
            String key = keys.nextElement();
            // value of a key
            Assignments value = assignmentList.get(key);
            // sum up the total possible points for an assignment category
            points += value.maxGrade;
        }
        // return total possible points
        return points;
    }

    /**
     * Getter method to retrieve a specified assignment type for a student (homework, quiz, exam)
     * @return the assignment type key
     */
    public String getAssignmentType() {
        return assignmentType;
    }

    /**
     * Setter method to set the specified assignment type for a student (homework, quiz, exam)
     * @return the assignment type value
     */
    public Assignments setAssignmentType(Assignments value) {
        assignmentList = new Hashtable<>();
        Enumeration<String> key = assignmentList.keys();
        value = assignmentList.get(key);
        return value;
    }
}
