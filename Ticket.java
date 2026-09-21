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

    public int getTicketId() {
        return ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getAge() {
        return age;
    }

    public String getPreferredClass() {
        return preferredClass;
    }

    public String getClassGiven() {
        return classGiven;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    @Override 
    public String toString() {
        return "Ticket{" +
                "ticketId : " + this.ticketId +
                ", passengerName='" + this.passengerName + '\'' +
                ", age=" + this.age +
                ", preferredClass='" + this.preferredClass + '\'' +
                ", classGiven='" + this.classGiven + '\'' +
                ", seatNumber='" + this.seatNumber + '\'' +
                '}';
    }

}