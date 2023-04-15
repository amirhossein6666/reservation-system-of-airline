import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
    private int pass_num;
    private String username;
    private String password;
    private long charge;
    private int num_of_booked_tickets;
    public ArrayList<Tickect> booked_tickets = new ArrayList<Tickect>();

    public Passenger(int pass_num , String username, String password , int charge , int num_of_booked_tickets , ArrayList<Tickect> booked_tickets){
        this.pass_num = pass_num;
        this.username = username;
        this.password = password;
        this.charge = charge;
        this.num_of_booked_tickets = num_of_booked_tickets;
        this.booked_tickets = booked_tickets;
    }
    public int get_pass_num(){
        return pass_num;
    }
    public String get_username(){
        return username;
    }
    public String get_password(){
        return password;
    }
    public long get_charge(){
        return charge;
    }
    public int get_num_of_booked_tickets(){
        return num_of_booked_tickets;
    }
    public ArrayList<Tickect> get_booked_tickets(){
        return booked_tickets;
    }
    public void set_password(String Password){
        this.password = Password;
    }
    public void set_charge(long charge){
        this.charge = charge;
    }
    public void set_num_of_booked_tickets(int Num_of_booked_tickets){
        this.num_of_booked_tickets = Num_of_booked_tickets;
    }
    public void set_booked_tickets(ArrayList<Tickect> Booked_tickets){
        booked_tickets = Booked_tickets;
    }
    public static int check_user(String user_in , String pass_in){
        int flag = 0 , pass_number = 0;
        for (int i = 0; i < App.num_of_pass; i++) {
            if (user_in.equals(App.passengers.get(i).get_username()) && pass_in.equals(App.passengers.get(i).get_password())){     
                flag = 1;
                pass_number = i+1;
            }
        }    
        if (flag == 0){
            return 0;
        }
        else {
            return pass_number;
        }
    }
    public static void change_pass(int pass_num){
        App.clearScreen();
        System.out.println("please enter your new password");
        String new_pasword;
        Scanner n_pass = new Scanner(System.in);   // n_pass mean new password
        new_pasword = n_pass.next();
        // n_pass.close();
        App.passengers.get(pass_num-1).set_password(new_pasword);
        System.out.println("Done your new password is " + new_pasword);
        App.PressAnyKey();
    }
    public static void search_menu(int pass_num){
        Search.print_search_menu();
        Scanner s_input = new Scanner(System.in);
        int search_input = s_input.nextInt();
        // s_input.close();
        switch (search_input){
            case 1:{
                Search.S_flightID(pass_num);
                App.passenger_menue(pass_num);
                break;
            }
            case 2:{
                Search.S_origin(pass_num);
                App.passenger_menue(pass_num);

                break;
            }
            case 3:{
                Search.S_destination(pass_num);
                App.passenger_menue(pass_num);

                break;
            }
            case 4:{
                Search.S_price_range(pass_num);
                App.passenger_menue(pass_num);
                Search.S_price_range(pass_num);
                break;
            }
            case 0:{
                App.passenger_menue(pass_num);
            }
            default:{
                System.out.println("wrong input try again");
                search_menu(pass_num);
                break;
            }

        }
    }
    public static void booking(int pass_num){
        App.clearScreen();
        int flag = 0;
        System.out.println("enter your desired ticket's flight id");
        Scanner FI_booking = new Scanner(System.in);
        String f_booking = FI_booking.nextLine();
        // FI_booking.close();
        for (int i = 0; i < Search.tickets.size(); i++) {
            if (f_booking.equals(Search.tickets.get(i).get_flight_id()) ){
                if (App.passengers.get(pass_num-1).get_charge() >= Search.tickets.get(i).get_price()){
                    if (Search.tickets.get(i).get_seats() > 0){
                        Search.tickets.get(i).set_seats(Search.tickets.get(i).get_seats() - 1);
                        App.passengers.get(pass_num-1).set_charge(App.passengers.get(pass_num-1).get_charge() - Search.tickets.get(i).get_price());
                        Tickect tick = new Tickect(f_booking, Search.tickets.get(i).get_origin(), Search.tickets.get(i).get_destination(), Search.tickets.get(i).get_Date(), Search.tickets.get(i).get_time(),Search.tickets.get(i).get_price(), Search.tickets.get(i).get_seats());
                        ArrayList<Tickect> test_BT = new ArrayList<>();
                        for (int j = 0; j < App.passengers.get(pass_num-1).get_num_of_booked_tickets(); j++) {
                            test_BT.add(j, App.passengers.get(pass_num-1).get_booked_tickets().get(j));
                        }
                        test_BT.add(App.passengers.get(pass_num-1).get_num_of_booked_tickets() , tick);
                        App.passengers.get(pass_num-1).set_booked_tickets(test_BT);
                        App.passengers.get(pass_num-1).set_num_of_booked_tickets(App.passengers.get(pass_num-1).get_num_of_booked_tickets() + 1);
                        flag = 1;
                        System.out.println("Done");
                        App.PressAnyKey();
                        break;
                    }
                    else{
                        System.out.println("there are no seats available");
                        App.PressAnyKey();
                        flag = 1;
                    }
                }
                else{
                    System.out.println("there are no enough charge please charge your account first");
                    App.PressAnyKey();
                    flag = 1;
                }
            }
        }
        if (flag == 0){
            System.out.println("the ticket dosn't exists");
            App.PressAnyKey();
        }
        App.passenger_menue(pass_num);
    }
    public static void ticket_cancel(int pass_num){
        App.clearScreen();
        int flag = 0;
        System.out.println("enter your desired ticket's flight id");
        Scanner cancel_i = new Scanner(System.in);
        String tick_canc = cancel_i.nextLine();
        // cancel_i.close();
        if (App.passengers.get(pass_num-1).get_num_of_booked_tickets() == 0){
            System.out.println("there are no booked tickets for cancellation");
            App.PressAnyKey();
        }
        else{
            for (int i = 0; i < App.passengers.get(pass_num-1).get_num_of_booked_tickets(); i++) {
                if (tick_canc.equals(App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_flight_id())){
                    int j = Tickect.find_ticket(App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_flight_id());
                    Search.tickets.get(j).set_seats(Search.tickets.get(j).get_seats() + 1);
                    App.passengers.get(pass_num-1).set_charge(App.passengers.get(pass_num-1).get_charge() + Search.tickets.get(j).get_price());
                    App.passengers.get(pass_num-1).get_booked_tickets().remove(i);
                    App.passengers.get(pass_num-1).set_num_of_booked_tickets(App.passengers.get(pass_num-1).get_num_of_booked_tickets() - 1);
                    System.out.println("the ticket is cancelled");
                    flag = 1;
                    App.PressAnyKey();
                    break;
                }
            }        
        }
        if (flag == 0){
            System.out.println("your desired ticket dosn't book");
            App.PressAnyKey();
        }
        App.passenger_menue(pass_num);
    }
    public static void show_booked_tickets(int pass_num){
        App.clearScreen();
        if (App.passengers.get(pass_num-1).get_num_of_booked_tickets() == 0){
            System.out.println("there are no booked tickets");
            App.PressAnyKey();
        }
        else{
            for (int i = 0; i < App.passengers.get(pass_num-1).get_num_of_booked_tickets(); i++) {
                System.out.print(i+1 + " ->  flight id: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_flight_id());
                System.out.print("     origin: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_origin());
                System.out.print("     destination: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_destination());
                System.out.print("     Date: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_Date());
                System.out.print("     time: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_time());
                System.out.print("     price: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_price());
                System.out.print("     seats: " + App.passengers.get(pass_num-1).get_booked_tickets().get(i).get_seats() + " \n");
            }
            App.PressAnyKey();
        }
        App.passenger_menue(pass_num);
    }
    public static void add_charge(int pass_num){
        App.clearScreen();
        System.out.println("how much do you want to increase your charge");
        Scanner add_charge = new Scanner(System.in);
        long added_charge = add_charge.nextLong();
        // add_charge.close();
        App.passengers.get(pass_num-1).set_charge(App.passengers.get(pass_num-1).get_charge() + added_charge);
        System.out.println("Done. your charge increased  " + added_charge);
        App.PressAnyKey();
    }
}