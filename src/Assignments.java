import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

// How well is the class doing as a whole on "a" assignment?

/**
 * This class serves to calculate the maximum score that can be achieved
 * on a single assignment
 * @author Aaron Howe
 * @version JDK 21
 */
public class Assignments {
    String assignmentName;
    double maxGrade;
    int index;
    Hashtable<String, Grades> assignmentGrades;

    public Assignments(String name, double maxGrade, int index) {
        this.assignmentName = name;
        this.maxGrade = maxGrade;
        this.index = index;
        this.assignmentGrades = new Hashtable<>();
    }


    /**
     * Method to return the class maximum score of a single assignment
     * @return the sum of each student's score on an assignment
     */
    public double getTotalPoints() {
        assignmentGrades = new Hashtable<>();
        // enumeration of elements given to the hashtable object
        Enumeration<String> keys = assignmentGrades.keys();
        double score = 0.0;
        while (keys.hasMoreElements()) {
            // iterator
            String key = keys.nextElement();
            // value of a key
            Grades value = assignmentGrades.get(key);
            // sum up each student's grade for an assignment
            score += value.grade;
        }
        return score;
    }
    /*
    We want to know John Doe's grade on *assignment name*
     */
}
