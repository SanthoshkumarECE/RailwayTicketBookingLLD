package TrainBooking;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class TicketBooker {

    public static int FC = 2;
    public static int SC = 2;
    public static int RAC = 2;
    public static int WL = 2;

    private static Queue<Integer> FCSeats = createSeatQueue(FC);
    private static Queue<Integer> SCSeats = createSeatQueue(SC);
    private static Queue<Integer> RACSeats = createSeatQueue(RAC);
    private static Queue<Integer> WLSeats = createSeatQueue(WL);

    private static HashMap<String, ArrayDeque<Ticket>> bookedTickets = new HashMap<>();

    private static Queue<Integer> createSeatQueue(int count) {
        Queue<Integer> seats = new ArrayDeque<>();
        for (int seat = 1; seat <= count; seat++) {
            seats.offer(seat);
        }
        return seats;
    }

    public void bookTicket(String userName, String passengerName, int age, String preferredClass) {
        String classGiven = null;
        String seatNumber = null;

        if (preferredClass.equalsIgnoreCase("FC") && !FCSeats.isEmpty()) {
            classGiven = "FC";
            seatNumber = String.valueOf(FCSeats.poll());
        } else if (preferredClass.equalsIgnoreCase("SC") && !SCSeats.isEmpty()) {
            classGiven = "SC";
            seatNumber = String.valueOf(SCSeats.poll());
        } else if (!RACSeats.isEmpty()) {
            classGiven = "RAC";
            seatNumber = String.valueOf(RACSeats.poll());
        } else if (!WLSeats.isEmpty()) {
            classGiven = "WL";
            seatNumber = String.valueOf(WLSeats.poll());
        } else {
            System.out.println("No seats available in any class.");
            return;
        }

        Ticket ticket = new Ticket(passengerName, age, preferredClass, classGiven, seatNumber + classGiven);
        if (!bookedTickets.containsKey(userName)) {
            bookedTickets.put(userName, new ArrayDeque<>());
        }
        bookedTickets.get(userName).offer(ticket);
        System.out.println("Ticket booked successfully for " + passengerName + ". Class: " + classGiven + ", Seat Number: " + seatNumber);
    }

    public void showAvailableSeats() {
        System.out.println("Available Seats:");
        System.out.println("First Class (FC): " + FCSeats.size());
        System.out.println("Second Class (SC): " + SCSeats.size());
        System.out.println("Reservation Against Cancellation (RAC): " + RACSeats.size());
        System.out.println("Waiting List (WL): " + WLSeats.size());
    }

    public void viewTicket(String userName) {
        ArrayDeque<Ticket> tickets = bookedTickets.get(userName);
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                System.out.println("--------------------");
                System.out.println("TICKET ID : " + ticket.getTicketId());
                System.out.println("Passenger Name : " + ticket.getPassengerName());
                System.out.println("Age : " + ticket.getAge());
                System.out.println("Preferred Class : " + ticket.getPreferredClass());
                System.out.println("Class Given : " + ticket.getClassGiven());
                System.out.println("Seat Number : " + ticket.getSeatNumber());
                System.out.println("--------------------");
            }
        } else {
            System.out.println("No ticket found for " + userName);
        }
    }

    public void cancelTicket(String userName,int ticketId) {
        ArrayDeque<Ticket> tickets = bookedTickets.get(userName);
        if (tickets != null) {
            Ticket ticketToCancel = null;
            for (Ticket ticket : tickets) {
                if (ticket.getTicketId() == ticketId) {
                    ticketToCancel = ticket;
                    break;
                }
            }
            if (ticketToCancel != null) {
                tickets.remove(ticketToCancel);
                String classGiven = ticketToCancel.getClassGiven();
                if (classGiven.equalsIgnoreCase("FC")) {
                    FCSeats.offer(Integer.parseInt(ticketToCancel.getSeatNumber()));
                } else if (classGiven.equalsIgnoreCase("SC")) {
                    SCSeats.offer(Integer.parseInt(ticketToCancel.getSeatNumber()));
                } else if (classGiven.equalsIgnoreCase("RAC")) {
                    RACSeats.offer(Integer.parseInt(ticketToCancel.getSeatNumber()));
                } else if (classGiven.equalsIgnoreCase("WL")) {
                    WLSeats.offer(Integer.parseInt(ticketToCancel.getSeatNumber()));
                }
                System.out.println("Ticket with ID " + ticketId + " canceled successfully.");
            } else {
                System.out.println("No ticket found with ID " + ticketId + " for user " + userName);
            }
        } else {
            System.out.println("No tickets found for user " + userName);
        }
        if(ticketToCancel.getClassGiven().equalsIgnoreCase("FC") && !RACSeats.isEmpty())
        {
            Ticket racTicket = RACSeats.poll();
            String newSeatNumber = String.valueOf(FCSeats.poll());
            racTicket = new Ticket(racTicket.getPassengerName(), racTicket.getAge(), racTicket.getPreferredClass(), "FC", newSeatNumber + "FC");
            tickets.offer(racTicket);
            System.out.println("RAC ticket upgraded to FC for passenger: " + racTicket.getPassengerName() + ". New Seat Number: " + newSeatNumber);
        }
        
    }
    {

    }


}
