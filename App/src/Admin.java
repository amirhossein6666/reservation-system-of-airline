import java.util.Scanner;

public class Admin {
    public static void admin_menu(){
        print_admin_menu();
        Scanner AdminMenu = new Scanner(System.in);
        int admin_choise = AdminMenu.nextInt();
        // AdminMenu.close();
        switch (admin_choise){
            case 1:{
                add_ticket();
                admin_menu();
                break;
            }
            case 2:{
                update_ticket();
                admin_menu();
                break;
            }
            case 3:{
                remove_ticket();
                admin_menu();
                break;
            }
            case 4:{
                flight_schedules();
                admin_menu();
                break;
            }
            case 0:{
                App.enter();
                break;
            }
            default:{
                System.out.println("wrong input try again");
                admin_menu();
            }
        }
    }
    public static void print_admin_menu(){
        App.clearScreen();
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t Admin menue option");
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .\n");
        System.out.println("<1>  add ticket");
        System.out.println("<2>  update ticket");
        System.out.println("<3>  remove ticket");
        System.out.println("<4>  flight_schedules");
        System.out.println("<0>  sign out");


    }
    public static void add_ticket(){
        App.clearScreen();
        int flag = 1;
        String FlightId = "";
        Scanner AddTicket = new Scanner(System.in);
        while(true){
            System.out.println("what is the ticket's flight id");
            String id_test = AddTicket.nextLine();
            for (int i = 0; i < Search.tickets.size(); i++) {
                if (id_test.equals(Search.tickets.get(i).get_flight_id())){
                    flag = 0;
                    break;
                }
            }
            if (flag == 1){
                FlightId = id_test;
                break;
            }
            else{
                System.out.println("the ticket already exists");
                App.PressAnyKey();
            }
        }
        System.out.println("what is the ticket's origin");
        String Origin = AddTicket.nextLine(); 
        System.out.println("waht is the ticket's destination");
        String Destination = AddTicket.nextLine();
        System.out.println("what is the ticket's Date");
        String DATE = AddTicket.next();
        System.out.println("what is the ticket's time");
        String Time = AddTicket.next();
        System.out.println("what is the ticket's price");
        int Price = AddTicket.nextInt();
        System.out.println("what is the ticket's seats");
        int Seats = AddTicket.nextInt();
        Tickect tick = new Tickect(FlightId, Origin, Destination, DATE, Time, Price, Seats);
        Search.tickets.add(App.num_of_tickets, tick);
        // AddTicket.close();
        App.clearScreen();
        System.out.println(" Done: ticket added");
        App.PressAnyKey();
    }
    public static void update_ticket(){
        Scanner update_input = new Scanner(System.in);
        App.clearScreen();
        System.out.println("what is your desired ticket's flight_id that you want to update");
        int tick_num = Tickect.find_ticket(update_input.next());
        print_update_menu();
        int update_section = update_input.nextInt();
        switch (update_section){
            case 1:{
                System.out.println("what is your desired new flight_id");
                Search.tickets.get(tick_num-1).set_flight_id(update_input.next());
                App.clearScreen();
                System.out.println("Done : the tickets is updated");
                App.PressAnyKey();
                admin_menu();
                break;
            }
            case 2:{
                System.out.println("what is your desired new date");
                Search.tickets.get(tick_num-1).set_Date(update_input.next());
                App.clearScreen();
                System.out.println("Done : the tickets is updated");
                App.PressAnyKey();
                admin_menu();
                break;
            }
            case 3:{
                System.out.println("waht is your desired new time");
                Search.tickets.get(tick_num-1).set_time(update_input.next());
                App.clearScreen();
                System.out.println("Done : the tickets is updated");
                App.PressAnyKey();
                admin_menu();
                break;
            } 
            case 4:{
                System.out.println("what is your desired new seat");
                Search.tickets.get(tick_num-1).set_seats(update_input.nextInt());
                App.clearScreen();
                System.out.println("Done : the tickets is updated");
                App.PressAnyKey();
                admin_menu();
            }
            default:{
                App.clearScreen();
                System.out.println("wrong input try again");
                App.PressAnyKey();
                update_ticket();
                break;
            }
        }
        // update_input.close();
    }
    public static void remove_ticket(){
        App.clearScreen();
        Scanner RemoveTicket = new Scanner(System.in); 
        System.out.println("what is your desired ticket's flight_id");
        int tick_num = Tickect.find_ticket(RemoveTicket.next());
        // RemoveTicket.close();
        Search.tickets.remove(tick_num-1);   
        System.out.println("the ticket is removed");
        App.PressAnyKey();
        admin_menu();
    }
    public static void flight_schedules(){
        App.clearScreen();
        for (int i = 0; i < Search.tickets.size(); i++) {
            System.out.print(i + " ->>");
            Tickect.print_ticket(i);
        }
        App.PressAnyKey();
        admin_menu();
    }
    public static void print_update_menu(){
        App.clearScreen();
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t\t ticket update menu");
        System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . .\n. . . . . . . . . . . . . . . . . . . . . . . . . . .");
        System.out.println("you can't change the origin and destination and price of a ticket Because it is a problem for those who have booked the ticket");
        System.out.println("what is your desired section for update");
        System.out.println("<1> flight id");
        System.out.println("<2> Date");
        System.out.println("<3> time");
        System.out.println("<4> seats");
    }
}
