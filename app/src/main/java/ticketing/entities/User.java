package ticketing.entities;

import java.util.List;

public class User {


    private String name;

    private String password;

    private String hashedPassword;

    private List<Ticket> bookedTickets;

    private String userId;

    public User(String name, String password, String hashedPassword, List<Ticket> bookedTickets, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.bookedTickets = bookedTickets;
        this.userId = userId;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void printTickets() {
        for (int i = 0; i < bookedTickets.size(); i++) {
            System.out.println(bookedTickets.get(i).getTicketInfo());
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
