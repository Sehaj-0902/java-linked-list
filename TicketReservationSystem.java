public class TicketReservationSystem {
    static class Ticket {
        int ticketId;
        String customerName;
        String movieName;
        String seatNumber;
        Ticket next;

        public Ticket(int ticketId, String customerName, String movieName, String seatNumber) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.next = null;
        }
    }

    private Ticket head;

    public TicketReservationSystem() {
        head = null;
    }

    // Method to add ticket
    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber);
        if (head == null) {
            head = newTicket;
            newTicket.next = head;
        }
        else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    // Method to remove ticket
    public void removeTicket(int ticketId) {
        if (head == null) return;

        Ticket temp = head;
        Ticket prev = null;
        do {
            if (temp.ticketId == ticketId) {
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
                System.out.println("The ticket with Ticket Id " + ticketId + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    // Method to display tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets to display.");
            return;
        }

        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber);
            temp = temp.next;
        } while (temp != head);
    }

    // Method to count tickets
    public int countTickets() {
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem ticketSystem = new TicketReservationSystem();

        System.out.println("Booked Tickets:");
        ticketSystem.addTicket(101, "Sehaj", "Kung Fu Panda", "D-2");
        ticketSystem.addTicket(102, "Olivia", "The Lion King", "D-4");

        ticketSystem.displayTickets();
        System.out.println();

        System.out.println("Number of tickets: " + ticketSystem.countTickets());
        System.out.println();

        ticketSystem.removeTicket(101);
        System.out.println();

        System.out.println("Remaining Tickets:");
        ticketSystem.displayTickets();
    }
}

/*
Output:
    Booked Tickets:
    Ticket ID: 101, Customer: Sehaj, Movie: Kung Fu Panda, Seat: D-2
    Ticket ID: 102, Customer: Olivia, Movie: The Lion King, Seat: D-4

    Number of tickets: 2

    The ticket with Ticket Id 101 removed.

    Remaining Tickets:
    Ticket ID: 102, Customer: Olivia, Movie: The Lion King, Seat: D-4
 */