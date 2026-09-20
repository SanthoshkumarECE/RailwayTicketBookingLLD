package TrainBooking;

class Ticket {

    private static int ticketCounter = 0;

    private int ticketId;
    private String passengerName;
    private int age;
    private String preferredClass;
    private String classGiven;
    private String seatNumber;

    public Ticket() {
    }

    public Ticket(String passengerName, int age, String preferredClass,
                 String classGiven, String seatNumber) {
        this.ticketId = ticketCounter++;
        this.passengerName = passengerName;
        this.age = age;
        this.preferredClass = preferredClass;
        this.classGiven = classGiven;
        this.seatNumber = seatNumber;
    }

}