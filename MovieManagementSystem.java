public class MovieManagementSystem {
    static class MovieNode {
        String title;
        String director;
        int year;
        double rating;
        MovieNode prevNode;
        MovieNode nextNode;

        public MovieNode(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
            this.prevNode = null;
            this.nextNode = null;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Director: " + director + ", Year: " + year + ", Rating: " + rating;
        }
    }

    MovieNode head;
    MovieNode tail;

    public MovieManagementSystem() {
        this.head = null;
        this.tail = null;
    }

    // Method to add movie at the start
    public void addMovieAtStart(String title, String director, int year, double rating) {
        MovieNode newMovie = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = newMovie;
            tail = newMovie;
        }
        else {
            newMovie.nextNode = head;
            head.prevNode = newMovie;
            head = newMovie;
        }
    }

    // Method to add movie at the end
    public void addMovieAtEnd(String title, String director, int year, double rating) {
        MovieNode newMovie = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = newMovie;
            tail = newMovie;
        }
        else {
            tail.nextNode = newMovie;
            newMovie.prevNode = tail;
            tail = newMovie;
        }
    }

    // Method to add movie at the given position
    public void addMovieAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 0) {
            System.out.println("Position not valid.");
            return;
        }
        if (position == 1) {
            addMovieAtStart(title, director, year, rating);
            return;
        }
        MovieNode newMovie = new MovieNode(title, director, year, rating);
        MovieNode current = head;
        int count = 1;

        while (current != null && count < position - 1) {
            current = current.nextNode;
            count++;
        }
        if (current == null) {
            System.out.println("Position exceeds the list size.");
            return;
        }
        newMovie.nextNode = current.nextNode;
        newMovie.prevNode = current;
        if (current.nextNode != null) {
            current.nextNode.prevNode = newMovie;
        }
        else {
            tail = newMovie;
        }
        current.nextNode = newMovie;
    }

    // Method to remove movie by title
    public void removeMovieByTitle(String title) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current.prevNode != null) {
                    current.prevNode.nextNode = current.nextNode;
                }
                else {
                    head = current.nextNode;
                }
                if (current.nextNode != null) {
                    current.nextNode.prevNode = current.prevNode;
                }
                else {
                    tail = current.prevNode;
                }
                System.out.println("Movie '" + title + "' removed.");
                return;
            }
            current = current.nextNode;
        }
        System.out.println("Movie with title '" + title + "' not found.");
    }

    // Method to search movie by director
    public void searchMovieByDirector(String director) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                System.out.println(current);
                found = true;
            }
            current = current.nextNode;
        }
        if (!found) {
            System.out.println("No movies found for director '" + director + "'.");
        }
    }

    // Method to search movie by rating
    public void searchMovieByRating(double rating) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println(current);
                found = true;
            }
            current = current.nextNode;
        }
        if (!found) {
            System.out.println("No movies found with rating " + rating + ".");
        }
    }

    // Method to display movies in forward order
    public void displayMoviesForward() {
        MovieNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.nextNode;
        }
    }

    // Method to display movies in reverse order
    public void displayMoviesReverse() {
        MovieNode current = tail;
        while (current != null) {
            System.out.println(current);
            current = current.prevNode;
        }
    }

    // Method to update movie rating
    public void updateMovieRating(String title, double newRating) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                System.out.println("Movie '" + title + "' rating updated to " + newRating);
                return;
            }
            current = current.nextNode;
        }
        System.out.println("Movie with title '" + title + "' not found.");
    }

    // Method to display movies
    public void displayMovies() {
        MovieNode current = head;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.year + ", Rating: " + current.rating);
            current = current.nextNode;
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem movies = new MovieManagementSystem();

        movies.addMovieAtStart("WALL-E", "Andrew Stanton", 2008, 8.4);
        movies.addMovieAtEnd("The Lion King", "Jon Favreau", 2019, 6.8);
        movies.addMovieAtPosition("Kung Fu Panda", "John Stevenson and Mark Osborne", 2008, 7.6, 3);

        // Display movies
        System.out.println("Movies List:");
        movies.displayMovies();

        // Display forward
        System.out.println("\nMovies in forward order:");
        movies.displayMoviesForward();

        // Display reverse
        System.out.println("\nMovies in reverse order:");
        movies.displayMoviesReverse();

        // Update rating
        System.out.println("\nUpdating movie rating by title");
        movies.updateMovieRating("The Lion King", 7.8);

        // Remove movie
        System.out.println("\nRemoving movie by title");
        movies.removeMovieByTitle("Kung Fu Panda");

        // Search by Director
        System.out.println("\nSearching by director");
        movies.searchMovieByDirector("Andrew Stanton");

        // Search by rating
        System.out.println("\nSearching by rating");
        movies.searchMovieByRating(8.4);

        // Display Movies after operations
        System.out.println("\nMovies List after operations:");
        movies.displayMovies();
    }
}

/*
Output:
    Movies List:
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4
    Title: The Lion King, Director: Jon Favreau, Year: 2019, Rating: 6.8
    Title: Kung Fu Panda, Director: John Stevenson and Mark Osborne, Year: 2008, Rating: 7.6

    Movies in forward order:
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4
    Title: The Lion King, Director: Jon Favreau, Year: 2019, Rating: 6.8
    Title: Kung Fu Panda, Director: John Stevenson and Mark Osborne, Year: 2008, Rating: 7.6

    Movies in reverse order:
    Title: Kung Fu Panda, Director: John Stevenson and Mark Osborne, Year: 2008, Rating: 7.6
    Title: The Lion King, Director: Jon Favreau, Year: 2019, Rating: 6.8
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4

    Updating movie rating by title
    Movie 'The Lion King' rating updated to 7.8

    Removing movie by title
    Movie 'Kung Fu Panda' removed.

    Searching by director
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4

    Searching by rating
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4

    Movies List after operations:
    Title: WALL-E, Director: Andrew Stanton, Year: 2008, Rating: 8.4
    Title: The Lion King, Director: Jon Favreau, Year: 2019, Rating: 7.8
 */