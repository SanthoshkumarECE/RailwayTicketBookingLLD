package TrainBooking;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{   public static boolean userLogged = false;

    public static String currentUserLogged = null;

    public static HashMap<String,UserDetails> UserDetailsCOllection = new HashMap<>();
    public static boolean authUser(String userName)
    {
        if(UserDetailsCOllection.containsKey(userName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void logOutUser(String userName)
    {
        if(UserDetailsCOllection.containsKey(userName))
        {
            userLogged = false;
            currentUserLogged = null;
        }
        else
        {
            System.out.println("User not found");
        }
    }
    public static void main(String[] args)
    {   
        Scanner sc = new Scanner(System.in);
        String userName;

        
        System.out.println("--------TRAIN TICKER RESERVATION SYSTEM--------");


        while(!userLogged)
        {
            System.out.println("Log in / Sign in");
            System.out.println("Enter Username : ");

            userName = sc.nextLine();
            if(authUser(userName))
            {
                System.out.println("Welcome Back " + userName);
                userLogged = true;
                currentUserLogged = userName;
            }
            else
            {
                System.out.println("User not found, Please Sign Up");
                System.out.println("Enter Username : ");
                userName = sc.nextLine();
                UserDetailsCOllection.put(userName,new UserDetails(userName));
                System.out.println("User Created Successfully, Welcome " + userName);
                userLogged = true;
                currentUserLogged = userName;
            }
        
        
        while(userLogged)
        {
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Ticket");
            System.out.println("4. Show Available Seats");
            System.out.println("5. Log Out");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            TicketBooker ticketBooker = new TicketBooker();
            switch(choice)
            {
                case 1:
                    ticketBooker.showAvailableSeats();
                    System.out.println("Enter Passenger Name : ");  
                    String passengerName = sc.next();
                    System.out.println("Enter Age : ");
                    int age = sc.nextInt();
                    System.out.println("Enter Preferred Class (FC/SC/RAC/WL) : "); 
                    String preferredClass = sc.next();

                    if(!preferredClass.equalsIgnoreCase("FC") && !preferredClass.equalsIgnoreCase("SC") && !preferredClass.equalsIgnoreCase("RAC") && !preferredClass.equalsIgnoreCase("WL"))
                    {
                        System.out.println("Invalid Class, Please try again");
                        break;
                    }   

                    ticketBooker.bookTicket(currentUserLogged,passengerName, age, preferredClass);   
                    break;
                case 2:
                    ticketBooker.cancelTicket(currentUserLogged);   
                    break;
                case 3:
                    ticketBooker.viewTicket(currentUserLogged);
                    break;
                case 4:
                    ticketBooker.showAvailableSeats();
                    break;
                case 5:
                    logOutUser(currentUserLogged);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }


            
            }

        }




    
    }
    }