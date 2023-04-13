import java.util.ArrayList;
import java.util.Scanner;

public class Search {
    static ArrayList<Tickect> tickets = new ArrayList<>();
    public static void print_search_menu(){
        App.clearScreen();
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t    what is your base of search");
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("<1> flight Id");
        System.out.println("<2> origin");
        System.out.println("<3> destination");
        System.out.println("<4> price range");
        System.out.println("<0> return");

    }
    public static void S_flightID(int pass_num){
        App.clearScreen();
        int flag = 0;
        System.out.println("what is your desired flight id");
        Scanner If_id = new Scanner(System.in);
        String I_FlightId = If_id.nextLine();
        // If_id.close();
        for (int i = 0; i < tickets.size(); i++) {
            if (I_FlightId.equals(tickets.get(i).get_flight_id())){
                flag = 1;
                Tickect.print_ticket(i);
            }     
        }
        if (flag == 0){
            System.out.println("this ticket dosn't exist");
        }
        App.PressAnyKey();
        Passenger.search_menu(pass_num);
    }
    public static void S_origin(int pass_num){
        App.clearScreen();
        int flag = 0;
        System.out.println("what is your desired origin");
        Scanner I_origin = new Scanner(System.in);
        String origin_i = I_origin.nextLine();
        // I_origin.close();
        for (int i = 0; i < tickets.size(); i++) {
            if (origin_i.equals(tickets.get(i).get_origin())){
                flag = 1;
                Tickect.print_ticket(i);
            }
        }
        if (flag == 0){
            System.out.println("this ticket dosn't exist");
        }
        App.PressAnyKey();
        Passenger.search_menu(pass_num);
    }
    public static void S_destination(int pass_num){
        App.clearScreen();
        int flag = 0;
        System.out.println("what is your desired destination");
        Scanner i_destination = new Scanner(System.in);
        String destination_i = i_destination.nextLine();
        // i_destination.close();
        for (int i = 0; i < tickets.size(); i++) {
            if (destination_i.equals(tickets.get(i).get_destination())){
                flag = 1;
                Tickect.print_ticket(i);
            }
        }
        if (flag == 0){
            System.out.println("this ticket dosn't exist");
        }
        App.PressAnyKey();
        Passenger.search_menu(pass_num);
    }
    public static void S_price_range(int pass_num){
        App.clearScreen();
        int min_price, max_price , flag = 0;
        System.out.println("what is your desired minimum price");
        Scanner price_input = new Scanner(System.in);
        min_price = price_input.nextInt();
        System.out.println("what is your desired maximum price");
        max_price = price_input.nextInt();
        // price_input.close();
        for (int i = 0; i < tickets.size(); i++) {
            if (min_price <= tickets.get(i).get_price() && max_price >= tickets.get(i).get_price()){
                flag = 1;
                Tickect.print_ticket(i);
            }
        }
        if (flag == 0){
            System.out.println("There are no tickets");
        }
        App.PressAnyKey();
        Passenger.search_menu(pass_num);
    }

}
