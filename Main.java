import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static char[][] seats ;
    static int totalSeats ;
    static String[] movieNames = {"Superman", "Avatar", "Minecraft", "Inside Out", "F1"};
    static int availableSeats = 0, bookedSeats = 0;
    static int selection;
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter number or rows");
        int rows = userInput.nextInt();
        System.out.println("Enter number of columns");
        int cols = userInput.nextInt();
        seats = new char[rows][cols];
        totalSeats = seats.length * seats[seats.length - 1].length;
        //= new char[5][6];
        //fill all seats with 'O'
        emptySeats(seats);
        //the menu
        displayMenu();

        while (selection != -1) {

            if (selection < 1 || selection > 5) {
                System.out.println("Wrong Selection, please try again");
                displayMenu();
                selection = userInput.nextInt();
            }

            if (selection == 1) {
                displaySeats(seats);
            }
            if (selection == 2) {
                System.out.println("Enter your seat number");
                bookSeat(userInput.nextInt());
            }
            if (selection == 3) {
                System.out.println("Enter your booked seat number to cancel");
                cancelBooking(userInput.nextInt());
            }
            if (selection == 4) {
                showAllMovies(movieNames);
            }
            if (selection == 5) {
                System.out.println("Thanks for using our Cinema Booking App");
                break;
            }
            System.out.println("------------------------");
            displayMenu();

        }


    }

    private static void displayMenu() {
        System.out.println("Choose an option");
        System.out.println("1. Display Seats");
        System.out.println("2. Book Seat");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Show All Movies");
        System.out.println("5. Exit");
        selection = userInput.nextInt();
    }

    private static void showNumberOfAvailableAndBookedSeats() {
        System.out.printf("Available Seats: %d", availableSeats);
        System.out.printf("Booked Seats: %d", bookedSeats);
    }

    private static void cancelBooking(int seat) {
        int seatColumn = seat % 10;
        int seatRow = seat - seatColumn;
        seatRow = seatRow / 10;
        if (seatRow < 1 || seatRow > seats.length || seatColumn < 1 || seatColumn > seats[0].length) {
            System.out.println("Invalid Seat");
            return;
        }
        //adjust rows & columns for array indices
        seatColumn--;
        seatRow--;
        if (seats[seatRow][seatColumn] == 'X') {
            seats[seatRow][seatColumn] = 'O';
            System.out.printf("Seat number %d booking was cancelled.", seat);
            System.out.println();
        }
        else System.out.println("This seat was not booked before");
        bookedSeats--;
        System.out.println("Booked seats count:" + bookedSeats);
        availableSeats = totalSeats - bookedSeats;
        System.out.println("Available seats count:" + availableSeats);
    }

    private static void showAllMovies(String[] movieNames) {
        for (String movie : movieNames)
            System.out.println(movie);
    }

    public static void displaySeats(char[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.printf("[%c]", seats[i][j]);
            }
            System.out.println();
        }
    }

    public static void bookSeat(int seat) {
        int seatColumn = seat % 10;
        int seatRow = seat - seatColumn;
        seatRow = seatRow / 10;
        if (seatRow < 1 || seatRow > seats.length || seatColumn < 1 || seatColumn > seats[0].length) {
            System.out.println("Invalid Seat");
            return;
        }
        //adjust rows & columns for array indices
        seatColumn--;
        seatRow--;

        if (seats[seatRow][seatColumn] == 'O' || seats[seatRow][seatColumn] == '\u0000') {
            seats[seatRow][seatColumn] = 'X';
            System.out.printf("Seat number %d booking is confirmed.", seat);
            System.out.println();
        } else {
            System.out.printf("Seat number %d is already booked, please choose another seat.", seat);
            System.out.println();
            return;
        }
        bookedSeats++;
        System.out.println("Booked seats count:" + bookedSeats);
        availableSeats = totalSeats - bookedSeats;
        System.out.println("Available seats count:" + availableSeats);
        if (bookedSeats > 24 ) System.out.println("Almost Full");

    }

    public static void emptySeats(char[][] seats) {
        for (char[] seat : seats) {
            Arrays.fill(seat, 'O');
        }
    }
}

//for (  int i = 0; i < seats.length; i++) {
//        for (int j = 0; j < seats[i].length; j++)
//        seats[i][j] ='O';
//        }

//to print columns with first column as a header
//          for (  int i = 1; i <= seats.length; i++) {
//              System.out.print("[0]");
//          }
//        System.out.println();
//        for (  int i = 1; i <= seats.length; i++) {
//            for (int j = 0; j < seats.length; j++) {
//                System.out.print("[X]");
//            }
//            System.out.println();
//        }

//        for (char[] seat : seats) {
//            System.out.print("["+ Arrays.toString(seat) +"]");
//            for (int s : seat)
//                System.out.printf("[%d]", s + 1);
//                //System.out.print("[c]");
//            System.out.println();
//        }

//String seatTxt = String.valueOf(seat);
//int seatRow = (int) seatTxt.charAt(0) - 1;
// System.out.printf("[%d]", j);
//System.out.println(seats[seatRow][seatColumn]);
//            System.out.printf("[%c]", seats[i][i]); //this caused me an issue, this line not required at all
//in the displaySeats, if i booked before a seat 11, it used to highlight 2 seats

//traditional for loop, replaced with modern one in the emptySeats method
//        for (  int i = 0; i < seats.length; i++) {
//        for (int j = 0; j < seats[i].length; j++)
//        seats[i][j] ='O';
//        }