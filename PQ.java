import java.util.PriorityQueue;
import java.util.Collections;

class Student implements Comparable<Student> {
    String name;
    int marks;
    
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    // Define how to compare students
    public int compareTo(Student other) {
        // For min-heap based on marks (lowest marks first)
        return this.marks - other.marks;
    }
    
    public String toString() {
        return name + " (" + marks + ")";
    }
}

class NewStudent  {
    String name;
    int marks;
    
    NewStudent(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    
    public String toString() {
        return name + " (" + marks + ")";
    }
}


public class PQ 
{
    public static void main(String[] args) 
    {
        // Create a priority queue for Student objects
        PriorityQueue<NewStudent> pq = new PriorityQueue<>(
            (a,b)-> a.marks - b.marks
        );
        
        // Add some students to the priority queue
        pq.add(new NewStudent("Alice", 85));
        pq.add(new NewStudent("Bob", 70));
        pq.add(new NewStudent("Charlie", 90));
        pq.add(new NewStudent("David", 60));
        
        // Poll students from the priority queue in order of their marks
        System.out.println("Students in order of their marks:");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }   

    }
}
