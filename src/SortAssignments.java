import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class serves to calculate the maximum score that can be achieved
 * across each type of assignment (homework, quizzes, exams)
 * @author Aaron Howe
 * @version JDK 21
 */
public class SortAssignments {
    String assignmentType;
    Hashtable<String, Assignments> assignmentList;

    /**
     * Method to return the total possible points for an assignment type
     * @return the sum of the key, value pairs
     */
    public double getMaxScore() {
        assignmentList = new Hashtable<>();
        Enumeration<String> keys = assignmentList.keys();
        double points = 0.0;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Assignments value = assignmentList.get(key);
            points += value.maxGrade;
        }
        return points;
    }

    public String getAssignmentType() {
        return "";
    }

    public String setAssignmentType() {
        return "";
    }

}
