public class StudentRecordManagement {
    static class StudentNode {
        int rollNumber;
        String name;
        int age;
        String grade;
        StudentNode next;

        public StudentNode(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    StudentNode head;

    public StudentRecordManagement() {
        this.head = null;
    }

    // Method to add student record at the start
    public void addAtStart(int rollNumber, String name, int age, String grade) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Method to add student record at the end
    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        StudentNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newStudent;
    }

    // Method to add student record at the given position
    public void addAtPosition(int rollNumber, String name, int age, String grade, int position) {
        if (position <= 0) {
            System.out.println("Position not valid.");
            return;
        }
        if (position == 1) {
            addAtStart(rollNumber, name, age, grade);
            return;
        }
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        StudentNode currentNode = head;

        int count = 1;
        while (currentNode != null && count < position - 1) {
            currentNode = currentNode.next;
            count++;
        }
        if (currentNode == null) {
            System.out.println("Position exceeds the list size.");
            return;
        }
        newStudent.next = currentNode.next;
        currentNode.next = newStudent;
    }

    // Method to display all student records
    public void displayStudents() {
        StudentNode currentNode = head;
        if (currentNode == null) {
            System.out.println("Student list is empty.");
            return;
        }
        while (currentNode != null) {
            System.out.println("Roll Number: " + currentNode.rollNumber + ", Name: " + currentNode.name + ", Age: " + currentNode.age + ", Grade: " + currentNode.grade);
            currentNode = currentNode.next;
        }
    }

    // Method to search student record by Roll Number
    public void searchByRollNumber(int rollNumber) {
        StudentNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.rollNumber == rollNumber) {
                System.out.println("Student found.\nRoll Number: " + currentNode.rollNumber + ", Name: " + currentNode.name + ", Age: " + currentNode.age + ", Grade: " + currentNode.grade);
                return;
            }
            currentNode = currentNode.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to update student grade by Roll Number
    public void updateGradeByRollNumber(int rollNumber, String newGrade) {
        StudentNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.rollNumber == rollNumber) {
                String oldGrade = currentNode.grade;
                currentNode.grade = newGrade;
                System.out.println("Grade updated from " + oldGrade + " to " + newGrade + " for Roll Number " + rollNumber);
                return;
            }
            currentNode = currentNode.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to delete student record by Roll Number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            return;
        }
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        StudentNode currentNode = head;
        StudentNode previousNode = null;

        while (currentNode != null && currentNode.rollNumber != rollNumber) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " deleted.");
        if (currentNode == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }
        previousNode.next = currentNode.next;
    }

    public static void main(String[] args) {
        StudentRecordManagement list = new StudentRecordManagement();

        list.addAtStart(1296, "Sehaj", 22, "B");
        list.addAtEnd(1266, "Sanya", 21, "A");
        list.addAtPosition(1236, "Sukriti", 20, "B", 3);

        System.out.println("Students List:");
        list.displayStudents();

        System.out.println("\nSearching student by roll number:");
        list.searchByRollNumber(1296);

        System.out.println("\nUpdating student grade by roll number:");
        list.updateGradeByRollNumber(1296, "A");

        System.out.println("\nDeleting student by roll number:");
        list.deleteByRollNumber(1236);

        System.out.println("\nUpdated Students List:");
        list.displayStudents();
    }
}

/*
Output:
    Students List:
    Roll Number: 1296, Name: Sehaj, Age: 22, Grade: B
    Roll Number: 1266, Name: Sanya, Age: 21, Grade: A
    Roll Number: 1236, Name: Sukriti, Age: 20, Grade: B

    Searching student by roll number:
    Student found.
    Roll Number: 1296, Name: Sehaj, Age: 22, Grade: B

    Updating student grade by roll number:
    Grade updated from B to A for Roll Number 1296

    Deleting student by roll number:
    Student with Roll Number 1236 deleted.

    Updated Students List:
    Roll Number: 1296, Name: Sehaj, Age: 22, Grade: A
    Roll Number: 1266, Name: Sanya, Age: 21, Grade: A
 */