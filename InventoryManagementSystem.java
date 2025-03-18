public class InventoryManagementSystem {
    static class Item {
        int itemId;
        String itemName;
        int quantity;
        double price;
        Item next;

        public Item(int itemId, String itemName, int quantity, double price) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Item ID: " + itemId + ", Item Name: " + itemName + ", Quantity: " + quantity + ", Price: " + price;
        }
    }

    Item head;

    public InventoryManagementSystem() {
        this.head = null;
    }

    public void addItemAtBeginning(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addItemAtEnd(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    public void addItemAtPosition(int itemId, String itemName, int quantity, double price, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        Item newItem = new Item(itemId, itemName, quantity, price);
        if (position == 1) {
            newItem.next = head;
            head = newItem;
            return;
        }
        Item temp = head;
        int count = 1;
        while (count < position - 1 && temp != null) {
            temp = temp.next;
            count++;
        }
        if (temp == null) {
            System.out.println("Position exceeds list size.");
            return;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeItemById(int itemId) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " removed.");
            return;
        }
        Item current = head;
        Item previous = null;
        while (current != null && current.itemId != itemId) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println("Item with ID " + itemId + " not found.");
            return;
        }
        previous.next = current.next;
        System.out.println("Item with ID " + itemId + " removed.");
    }

    public void updateQuantityById(int itemId, int newQuantity) {
        Item current = head;
        while (current != null) {
            if (current.itemId == itemId) {
                current.quantity = newQuantity;
                System.out.println("Quantity of Item ID " + itemId + " updated to " + newQuantity);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void searchItemById(int itemId) {
        Item current = head;
        while (current != null) {
            if (current.itemId == itemId) {
                System.out.println(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void searchItemByName(String itemName) {
        Item current = head;
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(itemName)) {
                System.out.println(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with Name '" + itemName + "' not found.");
    }

    public double calculateTotalInventoryValue() {
        double totalValue = 0;
        Item current = head;
        while (current != null) {
            totalValue += current.quantity * current.price;
            current = current.next;
        }
        return totalValue;
    }

    public void sortInventoryByName(boolean ascending) {
        head = mergeSort(head, true, ascending);
    }

    public void sortInventoryByPrice(boolean ascending) {
        head = mergeSort(head, false, ascending);
    }

    private Item mergeSort(Item head, boolean sortByName, boolean ascending) {
        if (head == null || head.next == null) {
            return head;
        }
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;
        Item left = mergeSort(head, sortByName, ascending);
        Item right = mergeSort(nextOfMiddle, sortByName, ascending);
        return merge(left, right, sortByName, ascending);
    }

    private Item merge(Item left, Item right, boolean sortByName, boolean ascending) {
        Item result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (sortByName) {
            if (ascending ? left.itemName.compareToIgnoreCase(right.itemName) <= 0 : left.itemName.compareToIgnoreCase(right.itemName) > 0) {
                result = left;
                result.next = merge(left.next, right, sortByName, ascending);
            } else {
                result = right;
                result.next = merge(left, right.next, sortByName, ascending);
            }
        } else {
            if (ascending ? left.price <= right.price : left.price > right.price) {
                result = left;
                result.next = merge(left.next, right, sortByName, ascending);
            } else {
                result = right;
                result.next = merge(left, right.next, sortByName, ascending);
            }
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) {
            return head;
        }
        Item slow = head;
        Item fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayInventory() {
        Item current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();

        inventory.addItemAtBeginning(1, "Laptop", 10, 1200.0);
        inventory.addItemAtEnd(2, "Mouse", 50, 25.0);
        inventory.addItemAtPosition(3, "Keyboard", 30, 80.0, 2);
        inventory.addItemAtPosition(4, "Monitor", 20, 300.0, 1);

        System.out.println("Inventory:");
        inventory.displayInventory();

        System.out.println("\nTotal Inventory Value: " + inventory.calculateTotalInventoryValue());

        System.out.println("\nSearch by Item ID 2:");
        inventory.searchItemById(2);

        System.out.println("\nSearch by Item Name Keyboard:");
        inventory.searchItemByName("Keyboard");

        inventory.updateQuantityById(1, 15);

        inventory.removeItemById(3);

        System.out.println("\nInventory after updates:");
        inventory.displayInventory();

        System.out.println("\nSort by Item Name (Ascending):");
        inventory.sortInventoryByName(true);
        inventory.displayInventory();

        System.out.println("\nSort by Price (Descending):");
        inventory.sortInventoryByPrice(false);
        inventory.displayInventory();
    }
}

/*
Output:
    Inventory:
    Item ID: 4, Item Name: Monitor, Quantity: 20, Price: 300.0
    Item ID: 1, Item Name: Laptop, Quantity: 10, Price: 1200.0
    Item ID: 3, Item Name: Keyboard, Quantity: 30, Price: 80.0
    Item ID: 2, Item Name: Mouse, Quantity: 50, Price: 25.0

    Total Inventory Value: 21650.0

    Search by Item ID 2:
    Item ID: 2, Item Name: Mouse, Quantity: 50, Price: 25.0

    Search by Item Name Keyboard:
    Item ID: 3, Item Name: Keyboard, Quantity: 30, Price: 80.0
    Quantity of Item ID 1 updated to 15
    Item with ID 3 removed.

    Inventory after updates:
    Item ID: 4, Item Name: Monitor, Quantity: 20, Price: 300.0
    Item ID: 1, Item Name: Laptop, Quantity: 15, Price: 1200.0
    Item ID: 2, Item Name: Mouse, Quantity: 50, Price: 25.0

    Sort by Item Name (Ascending):
    Item ID: 1, Item Name: Laptop, Quantity: 15, Price: 1200.0
    Item ID: 4, Item Name: Monitor, Quantity: 20, Price: 300.0
    Item ID: 2, Item Name: Mouse, Quantity: 50, Price: 25.0

    Sort by Price (Descending):
    Item ID: 1, Item Name: Laptop, Quantity: 15, Price: 1200.0
    Item ID: 4, Item Name: Monitor, Quantity: 20, Price: 300.0
    Item ID: 2, Item Name: Mouse, Quantity: 50, Price: 25.0
 */