/**
 * This class serves to apply a numerical grade between students and assignments
 * @author Aaron Howe
 * @version JDK 21
 */
public class Grades {
    Students students;
    Assignments assignments;
    SortAssignments sort;
    int grade;

    /**
     * Constructor for the Grades class; initializes each object
     * @param student object of the student class to access a student's data to assign grading
     * @param assignments object of the assignments class to
     * @param grade
     */
    public Grades(Students student, Assignments assignments, SortAssignments sort, int grade) {
        this.students = student;
        this.assignments = assignments;
        this.sort = sort;
        this.grade = grade;
    }
}
