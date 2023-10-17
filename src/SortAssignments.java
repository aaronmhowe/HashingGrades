import java.util.*;

/**
 * This class serves to Properly sort the assignments for appearance on the output file as
 * ID -> Name -> HW1, HW2, HW3,..., Quiz1, Quiz2,..., Exam1, Exam2,...
 * @author Aaron Howe
 * @version JDK 21
 */
public class SortAssignments {
    ArrayList<String> studentInfo = new ArrayList<>();

    /**
     * SortAssignment Constructor: Sorting the assignment type headers as:
     * Student Info -> HW -> Quiz -> Exam
     * @param headers the first line of the csv output
     */
    // HW2,HW1,HW4,HW3,HW6,HW5,HW7,E1,E2,E3,Name,Quiz1,Quiz2,Quiz3,Quiz4,Total,ID
    // "#ID","Name","HW1","HW2","HW3","HW4","HW5","HW6","HW7","Quiz1","Quiz2","Quiz3","Quiz4","E1","E2","E3"
    public SortAssignments(List<Object> headers) {
        Hashtable<String, ArrayList<String>> sorting = new Hashtable<>();
        // Default headers into final ArrayList
        studentInfo.add("#ID");
        studentInfo.add("Name");
        // loop List of objects
        for (int i = 0; i < headers.size(); i++) {
            String value = (String) headers.get(i);
            // Removing the digits from each assignment name to be sorted
            String key = value.replaceAll("[0-9]","");
            if (sorting.containsKey(key)) {
                sorting.get(key).add(value);
            } else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(value);
                sorting.put(key, temp);
            }
        }
        // iterating sorting hash table, using collections.sort to sort properly
        Enumeration<String> iter = sorting.keys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            Collections.sort(sorting.get(key));
            studentInfo.addAll(sorting.get(key));
        }
    }



    /**
     * Getter method to retrieve a specified assignment type for a student (homework, quiz, exam)
     * @return the assignment type key
     */
    public ArrayList<String> getStudentInfo() {
        return studentInfo;
    }
}
