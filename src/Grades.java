import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class serves to apply a numerical grade between students and assignments
 * @author Aaron Howe
 * @version JDK 21
 */
public class Grades {
    Students students;
    Assignments assignments;
    SortAssignments sort;
    Hashtable<String, Grades> assignmentGrades;
    ArrayList<Integer> studentScores;
    // object stores the numerical grade for a given assignment
    int grade;
    int index;

    /**
     * Constructor for the Grades class; initializes each object
     * @param student object of the student class to access a student's data to assign grading
     * @param assignments object of the assignments class to assign grades to an assignment
     * @param grade scoring object for a given assignment
     */
    public Grades(Students student, Assignments assignments, SortAssignments sort, int grade, ArrayList<Integer> studentScores, Hashtable<String, Grades> assignmentGrades) {
        this.students = student;
        this.assignments = assignments;
        this.sort = sort;
        this.assignmentGrades = assignmentGrades;
        this.studentScores = studentScores;
        this.grade = grade;

    }

    /**
     * getter method for grade points
     * @return numerical grade object
     */
    public double getGrades() {

        return grade;
    }
}
