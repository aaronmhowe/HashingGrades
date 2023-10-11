import java.util.Hashtable;

// How well is the class doing as a whole on "a" assignment?

public class Assignments {
    String name;
    double maxGrade;
    double[] scores;
    int index;
    Hashtable<String, Grades> assignmentGrades;

    public Assignments(String name, double maxGrade, double[] scores, int index, Hashtable assignmentGrades) {
        this.name = name;
        this.maxGrade = maxGrade;
        this.scores = scores;
        this.index = index;
        this.assignmentGrades = new Hashtable<>();
    }

    /*
    We want to know John Doe's grade on *assignment name*
     */
}
