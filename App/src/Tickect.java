
public class Tickect {
    private String flight_id;
    private String origin;
    private String destination;
    private String Date;
    private String time;
    private int price;
    private int seats;
    
    public Tickect(String flight_id , String origin , String destination , String Date , String time , int price , int seats){
        this.flight_id = flight_id;
        this.origin = origin;
        this.destination = destination;
        this.Date = Date;
        this.time = time;
        this.price = price;
        this.seats = seats;
    }
    public String get_flight_id(){
        return flight_id;
    }
    public String get_origin(){
        return origin;
    }
    public String get_destination(){
        return destination;
    }
    public String get_Date(){
        return Date;
    }
    public String get_time(){
        return time;
    }
    public int get_price(){
        return price;
    }
    public int get_seats(){
        return seats;
    }
    public void set_flight_id(String flight_id_1){
        flight_id = flight_id_1;
    }
    public void set_origin(String origin_1){
        origin = origin_1;
    }
    public void set_destination(String destination_1){
        destination = destination_1;
    }
    public void set_Date(String Date_1){
        Date = Date_1;
    }
    public void set_time(String time_1){
        time = time_1;
    }
    public void set_price(int price_1){
        price = price_1;
    }
    public void set_seats(int seats_1){
        seats = seats_1;
    }
    public static void print_ticket(int tick_num){
        System.out.print("   flight id: " + Search.tickets.get(tick_num).get_flight_id() + "     origin: " + Search.tickets.get(tick_num).get_origin());
        System.out.print("     destination: " + Search.tickets.get(tick_num).get_destination() + "     Date: " + Search.tickets.get(tick_num).get_Date());;
        System.out.print("     time: " + Search.tickets.get(tick_num).get_time() + "     price: " + Search.tickets.get(tick_num).get_price() + "     seats: " + Search.tickets.get(tick_num).get_seats() + "\n");
    }
    public static int find_ticket(String flight_id){
        int i;
        for (i = 0; i < Search.tickets.size(); i++) {
            if(flight_id.equals(Search.tickets.get(i).get_flight_id())){
                break;
            }
        }
        return i;
    }
}
