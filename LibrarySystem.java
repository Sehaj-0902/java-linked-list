public class LibrarySystem {
    static class Book {
        int bookId;
        String title;
        String author;
        String genre;
        boolean isAvailable;
        Book prev;
        Book next;

        public Book(int bookId, String title, String author, String genre) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.isAvailable = true;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Available: " + isAvailable;
        }
    }

    Book head;
    Book tail;
    int bookCount;

    public LibrarySystem() {
        this.head = null;
        this.tail = null;
        this.bookCount = 0;
    }

    // Method to add book at the start
    public void addBookAtStart(int bookId, String title, String author, String genre) {
        Book newBook = new Book(bookId, title, author, genre);
        if (head == null) {
            head = newBook;
            tail = newBook;
        }
        else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        bookCount++;
    }

    // Method to add book at the end
    public void addBookAtEnd(int bookId, String title, String author, String genre) {
        Book newBook = new Book(bookId, title, author, genre);
        if (tail == null) {
            head = newBook;
            tail = newBook;
        }
        else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        bookCount++;
    }

    // Method to add book at the given position
    public void addBookAtPosition(int bookId, String title, String author, String genre, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        Book newBook = new Book(bookId, title, author, genre);
        if (position == 1) {
            addBookAtStart(bookId, title, author, genre);
            return;
        }
        Book current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position exceeds list size.");
            return;
        }
        newBook.next = current.next;
        newBook.prev = current;
        if (current.next != null) {
            current.next.prev = newBook;
        }
        else {
            tail = newBook;
        }
        current.next = newBook;
        bookCount++;
    }

    // Method to search book by title
    public void searchBookByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println("\nBook with title '" + title + "' found.");
                return;
            }
            current = current.next;
        }
        System.out.println("\nBook with title '" + title + "' not found.");
    }

    // Method to search book by author
    public void searchBookByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                System.out.println("\nBook by author '" + author + "' found.");
                return;
            }
            current = current.next;
        }
        System.out.println("\nBook by author '" + author + "' not found.");
    }

    // Method to remove book by Id
    public void removeBookById(int bookId) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                else {
                    tail = current.prev;
                }
                bookCount--;
                System.out.println("\nBook with ID " + bookId + " removed.");
                return;
            }
            current = current.next;
        }
        System.out.println("\nBook with ID " + bookId + " not found.");
    }

    // Method to update book availability status
    public void updateAvailabilityStatus(int bookId, boolean isAvailable) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = isAvailable;
                System.out.println("\nAvailability status of Book ID " + bookId + " updated to " + isAvailable);
                return;
            }
            current = current.next;
        }
        System.out.println("\nBook with ID " + bookId + " not found.");
    }

    // Method to get book count
    public int getBookCount() {
        return bookCount;
    }

    // Method to display books in forward order
    public void displayBooksForward() {
        Book current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    // Method to display books in reverse order
    public void displayBooksReverse() {
        Book current = tail;
        while (current != null) {
            System.out.println(current);
            current = current.prev;
        }
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        library.addBookAtStart(101, "Alice In Wonderland", "Lewis Carroll", "Fantasy");
        library.addBookAtEnd(102, "Pride and Prejudice", "Jane Austen", "Classic");
        library.addBookAtPosition(103, "Charlie and the Chocolate Factory", "Roald Dahl", "Fantasy", 2);
        library.addBookAtPosition(104, "1984", "George Orwell", "Dystopian", 1);

        System.out.println("Books in Forward Order:");
        library.displayBooksForward();

        System.out.println("\nBooks in Reverse Order:");
        library.displayBooksReverse();

        System.out.println("\nTotal Books: " + library.getBookCount());

        library.updateAvailabilityStatus(102, false);

        library.removeBookById(103);

        library.searchBookByTitle("1984");

        library.searchBookByAuthor("Douglas Adams");

        System.out.println("\nBooks in Forward Order after removal:");
        library.displayBooksForward();
    }
}

/*
Output:
    Books in Forward Order:
    Book ID: 104, Title: 1984, Author: George Orwell, Genre: Dystopian, Available: true
    Book ID: 101, Title: Alice In Wonderland, Author: Lewis Carroll, Genre: Fantasy, Available: true
    Book ID: 103, Title: Charlie and the Chocolate Factory, Author: Roald Dahl, Genre: Fantasy, Available: true
    Book ID: 102, Title: Pride and Prejudice, Author: Jane Austen, Genre: Classic, Available: true

    Books in Reverse Order:
    Book ID: 102, Title: Pride and Prejudice, Author: Jane Austen, Genre: Classic, Available: true
    Book ID: 103, Title: Charlie and the Chocolate Factory, Author: Roald Dahl, Genre: Fantasy, Available: true
    Book ID: 101, Title: Alice In Wonderland, Author: Lewis Carroll, Genre: Fantasy, Available: true
    Book ID: 104, Title: 1984, Author: George Orwell, Genre: Dystopian, Available: true

    Total Books: 4

    Availability status of Book ID 102 updated to false

    Book with ID 103 removed.

    Book with title '1984' found.

    Book by author 'Douglas Adams' not found.

    Books in Forward Order after removal:
    Book ID: 104, Title: 1984, Author: George Orwell, Genre: Dystopian, Available: true
    Book ID: 101, Title: Alice In Wonderland, Author: Lewis Carroll, Genre: Fantasy, Available: true
    Book ID: 102, Title: Pride and Prejudice, Author: Jane Austen, Genre: Classic, Available: false
 */