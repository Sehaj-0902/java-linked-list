import java.util.ArrayList;

public class SchedulingAlgorithm {
    static class ProcessNode {
        int processId;
        int burstTime;
        int priority;
        ProcessNode next;

        ProcessNode(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.next = null;
        }
    }

    private ProcessNode head ;
    private int timeQuantum;

    public SchedulingAlgorithm(int timeQuantum) {
        this.head = null;
        this.timeQuantum = timeQuantum;
    }

    // Method to add a process at the end
    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newProcess = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        }
        else {
            ProcessNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    // Method to remove a process by Process ID
    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode temp = head, prev = null;
        do {
            if (temp.processId == processId) {
                if (prev == null) {
                    if (head.next == head) {
                        head = null;
                    }
                    else {
                        prev = head;
                        while (prev.next != head) {
                            prev = prev.next;
                        }
                        head = head.next;
                        prev.next = head;
                    }
                }
                else {
                    prev.next = temp.next;
                }
                return;
            }
            System.out.println("Process with Process ID " + processId + " removed.");
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    // Method to execute processes
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode current = head;
        do {
            System.out.println("Executing Process ID: " + current.processId);
            if (current.burstTime > timeQuantum) {
                current.burstTime -= timeQuantum;
                current = current.next;
            }
            else {
                removeProcess(current.processId);
                current = current.next;
            }
        } while (head != null);
        System.out.println("All processes executed.");
    }

    // Method to display all processes
    public void viewProcesses() {
        if (head == null) {
            System.out.println("No processes available to display.");
            return;
        }
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        SchedulingAlgorithm scheduler = new SchedulingAlgorithm(2);

        scheduler.addProcess(1, 4, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 6, 3);

        scheduler.viewProcesses();
        System.out.println();

        scheduler.removeProcess(2);
        System.out.println();

        scheduler.executeProcesses();
    }
}

/*
Output:
    Process ID: 1, Burst Time: 4, Priority: 1
    Process ID: 2, Burst Time: 5, Priority: 2
    Process ID: 3, Burst Time: 6, Priority: 3

    Process with Process ID 2 removed.

    Executing Process ID: 1
    Executing Process ID: 3
    Executing Process ID: 1
    Executing Process ID: 3
    Executing Process ID: 3
    All processes executed.
 */