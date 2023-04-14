import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class App {
    static ArrayList<Passenger> passengers = new ArrayList<>();
    static int num_of_pass = 0; // the number of passengers
    static int num_of_tickets = 0;  // the number of tickets
    static int pass_num = 0; // the number of a passenger
    public static void main(String[] args) throws Exception {
        enter();
    }
    public static void enter(){
        print_enter_menue();
        Scanner enter_input = new Scanner(System.in);
        int enter_menue_input = enter_input.nextInt();
        if (enter_menue_input == 1)
            sign_in();
        else if (enter_menue_input == 2)
        sign_up();
        else {
            System.out.println("wrong input");
            enter();
        }
        // enter_input.close();
    }
    public static void print_enter_menue(){
        clearScreen();
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("\twelcoem to airline reservation system");
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println ("\t\t   menu options\n");
        System.out.println("<1>  sign in");
        System.out.println("<2>  sign up");
    }
    public static void sign_in(){
        clearScreen();
        String user_in;
        String pass_in;
        Scanner in_input = new Scanner(System.in);   
        System.out.println("enter your username");
        user_in = in_input.next();
        System.out.println("enter your password");
        pass_in = in_input.next();
        // in_input.close()
        if (user_in.equals("admin") && pass_in.equals("admin")){
            Admin.admin_menu();
        }
        else{
            if (Passenger.check_user (user_in , pass_in) == 0){
                System.out.println("your username and your password dosn't match");
                App.PressAnyKey();
                sign_in();
            }
            else{
                pass_num = Passenger.check_user(user_in, pass_in);
                passenger_menue(pass_num);
            }
        }
    }
    
    public static void sign_up(){
        clearScreen();
        int flag = 1;
        String username = "";
        String password;
        Scanner SignUp  = new Scanner(System.in);
        System.out.println("enter your username");
        String use_test = SignUp.next();
        if(num_of_pass != 0){
            for (int i = 0; i < num_of_pass; i++) {
                if(use_test.equals(passengers.get(i).get_username())){
                    System.out.println("the username exists try again");
                    App.PressAnyKey();
                    flag = 0;
                    break;
                }
            }
        }
        if (flag == 1){
            username = use_test;
        }
        else{
            sign_up();
        }
        System.out.println("enter your password");
        password = SignUp.next();
        Passenger pass_test = new Passenger(num_of_pass + 1, username, password, 0  , 0 , null);
        passengers.add(num_of_pass, pass_test); 
        num_of_pass = num_of_pass +1;
        enter();
    }
    public static void passenger_menue(int pass_num){
        print_passenger_menue(pass_num);
        Scanner pmi = new Scanner(System.in);   // pmi mean passenger menue input
        int passenger_menue_input = pmi.nextInt();
        // pmi.close();
        switch (passenger_menue_input){
            case 1: {
                Passenger.change_pass(pass_num);
                passenger_menue(pass_num);
                break;
            }
            case 2:{
                Passenger.search_menu(pass_num);
                passenger_menue(pass_num);
                break;
            }
            case 3:{
                Passenger.booking(pass_num);    
                passenger_menue(pass_num);
                break;
            }
            case 4:{
                Passenger.ticket_cancel(pass_num);
                passenger_menue(pass_num);
                break;
            }
            case 5:{
                Passenger.show_booked_tickets(pass_num);
                passenger_menue(pass_num);
                break;
            }
            case 6:{
                Passenger.add_charge(pass_num);
                passenger_menue(pass_num);
                break;
            }
            case 0:{
                enter();
                break;
            }
            default:{
                System.out.println("wrong input try again");
                App.PressAnyKey();
                passenger_menue(pass_num);
                break;
            }

        }

    }
    public static void print_passenger_menue(int pass_num){
        clearScreen();
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t\tpassenger menue option");
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .\n");
        System.out.println("your information");
        System.out.println("username: " + App.passengers.get(pass_num-1).get_username() + "     password: " + App.passengers.get(pass_num-1).get_password() + "     charge: " + App.passengers.get(pass_num-1).get_charge());
        System.out.println("<1>  change password");
        System.out.println("<2>  search flight tickets");
        System.out.println("<3>  booking tickets");
        System.out.println("<4>  ticket cancellation");
        System.out.println("<5>  booked tickets");
        System.out.println("<6>  add charge");
        System.out.println("<0>  sign out");
    }
    public static void clearScreen() {
        // clear screen after any chose
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void PressAnyKey() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press any key to continue...");
        try {
            input.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      }
}
