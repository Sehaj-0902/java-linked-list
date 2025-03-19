class UndoRedoEditor {
    private static class TextNode {
        String data;
        TextNode prev;
        TextNode next;

        TextNode(String data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private TextNode head;
    private TextNode tail;
    private TextNode current;
    private int historyLimit;
    private int size;

    public UndoRedoEditor(int historyLimit) {
        this.historyLimit = historyLimit;
        this.head = new TextNode(""); // Initial state
        this.tail = this.head;
        this.current = this.head;
        this.size = 1;
    }

    // Method to add state
    public void addState(String text) {
        if (current != tail) {
            TextNode temp = current.next;
            while (temp != null) {
                TextNode nextTemp = temp.next;
                temp = null;
                temp = nextTemp;
            }
            tail = current;
        }

        String newData = current.data + text;
        TextNode newNode = new TextNode(newData);
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
        tail = newNode;
        size++;

        if (size > historyLimit + 1) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    // Method to undo the action
    public void undo() {
        if (current.prev != null) {
            current = current.prev;
        }
    }

    // Method to redo the action
    public void redo() {
        if (current.next != null) {
            current = current.next;
        }
    }

    // Method to get current text
    public String getCurrentText() {
        return current.data;
    }

    // Method to display history
    public void displayHistory() {
        TextNode temp = head;
        int index = 0;
        System.out.println("\nHistory:");
        while (temp != null) {
            System.out.println(index + ": " + temp.data);
            temp = temp.next;
            index++;
        }
        System.out.println("Current State: " + current.data);
    }

    public static void main(String[] args) {
        UndoRedoEditor editor = new UndoRedoEditor(10);

        editor.addState("Doubly ");
        editor.addState("Linked ");
        editor.addState("List");

        System.out.println("Current Text: " + editor.getCurrentText());

        editor.undo();
        System.out.println("Current Text (undo): " + editor.getCurrentText());

        editor.undo();
        System.out.println("Current Text (undo): " + editor.getCurrentText());

        editor.redo();
        System.out.println("Current Text (redo): " + editor.getCurrentText());

        editor.displayHistory();
    }
}

/*
Output:
    Current Text: Doubly Linked List
    Current Text (undo): Doubly Linked
    Current Text (undo): Doubly
    Current Text (redo): Doubly Linked

    History:
    0:
    1: Doubly
    2: Doubly Linked
    3: Doubly Linked List
    Current State: Doubly Linked
 */