public class TaskScheduler {
    static class Task {
        int taskId;
        String taskName;
        int priority;
        String dueDate;
        Task next;

        public Task(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Task ID: " + taskId + ", Task Name: " + taskName + ", Priority: " + priority + ", Due Date: " + dueDate;
        }
    }

    Task head;
    Task currentTask;

    public TaskScheduler() {
        this.head = null;
        this.currentTask = null;
    }

    // Method to add task at the start
    public void addTaskAtStart(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            currentTask = head;
        }
        else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            head = newTask;
            temp.next = head;
        }
    }

    // Method to add task at the end
    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            currentTask = head;
        }
        else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    // Method to add task at the given position
    public void addTaskAtPosition(int taskId, String taskName, int priority, String dueDate, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            if (position == 1) {
                head = newTask;
                head.next = head;
                currentTask = head;
            }
            else {
                System.out.println("List is empty.");
                return;
            }
        }
        else if (position == 1) {
            addTaskAtStart(taskId, taskName, priority, dueDate);
        }
        else {
            Task temp = head;
            int count = 1;
            while (count < position - 1 && temp.next != head) {
                temp = temp.next;
                count++;
            }
            if (temp.next == head && count != position - 1) {
                System.out.println("Position exceeds list size.");
                return;
            }
            newTask.next = temp.next;
            temp.next = newTask;
        }
    }

    // Method to remove task by Id
    public void removeTaskById(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task current = head;
        Task previous = null;
        do {
            if (current.taskId == taskId) {
                if (current == head) {
                    if (current.next == head) {
                        head = null;
                        currentTask = null;
                    }
                    else {
                        Task temp = head;
                        while (temp.next != head) {
                            temp = temp.next;
                        }
                        head = current.next;
                        temp.next = head;
                        if(currentTask == current){
                            currentTask = head;
                        }
                    }
                }
                else {
                    previous.next = current.next;
                    if(currentTask == current){
                        currentTask = previous.next;
                    }
                }
                System.out.println("Task with ID " + taskId + " removed.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);
        System.out.println("Task with ID " + taskId + " not found.");
    }

    // Method to view next task
    public void viewNextTask() {
        if (currentTask == null) {
            System.out.println("Task list is empty.");
            return;
        }
        System.out.println("Current Task: " + currentTask);
        currentTask = currentTask.next;
    }

    // Method to display all tasks
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task temp = head;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Method to search task by priority
    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority + ".");
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTaskAtStart(1, "Coding", 1, "2024-12-15");
        scheduler.addTaskAtEnd(2, "Grocery Shopping", 2, "2024-12-16");
        scheduler.addTaskAtPosition(3, "Prepare Presentation", 3, "2024-12-17", 2);
        scheduler.addTaskAtPosition(4,"Schedule Doctor's Appointment", 4, "2024-12-18", 1);

        System.out.println("All Tasks:");
        scheduler.displayAllTasks();

        System.out.println("\nView Next Task:");
        scheduler.viewNextTask();

        System.out.println("\nView Next Task:");
        scheduler.viewNextTask();

        System.out.println("\nRemove Task by ID 2:");
        scheduler.removeTaskById(2);

        System.out.println("\nAll Tasks after removal:");
        scheduler.displayAllTasks();

        System.out.println("\nSearch by Priority 1:");
        scheduler.searchTaskByPriority(1);
    }
}

/*
Output:
    All Tasks:
    Task ID: 4, Task Name: Schedule Doctor's Appointment, Priority: 4, Due Date: 2024-12-18
    Task ID: 1, Task Name: Coding, Priority: 1, Due Date: 2024-12-15
    Task ID: 3, Task Name: Prepare Presentation, Priority: 3, Due Date: 2024-12-17
    Task ID: 2, Task Name: Grocery Shopping, Priority: 2, Due Date: 2024-12-16

    View Next Task:
    Current Task: Task ID: 1, Task Name: Coding, Priority: 1, Due Date: 2024-12-15

    View Next Task:
    Current Task: Task ID: 3, Task Name: Prepare Presentation, Priority: 3, Due Date: 2024-12-17

    Remove Task by ID 2:
    Task with ID 2 removed.

    All Tasks after removal:
    Task ID: 4, Task Name: Schedule Doctor's Appointment, Priority: 4, Due Date: 2024-12-18
    Task ID: 1, Task Name: Coding, Priority: 1, Due Date: 2024-12-15
    Task ID: 3, Task Name: Prepare Presentation, Priority: 3, Due Date: 2024-12-17

    Search by Priority 1:
    Task ID: 1, Task Name: Coding, Priority: 1, Due Date: 2024-12-15
 */