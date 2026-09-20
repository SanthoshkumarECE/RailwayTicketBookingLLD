package TrainBooking;

class UserDetails
{   
    protected static int userCounter = 0;
    private int userId;
    private String userName;

    UserDetails(String userName)
    {
        this.userId = userCounter++; ;
        this.userName = userName;
        

    }

    public int getuserId()
    {
        return this.userId;
    }

    public String userName()
    {
        return this.userName;
    }

    
}