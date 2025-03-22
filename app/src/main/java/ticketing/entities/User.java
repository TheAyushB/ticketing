package ticketing.entities;

import java.util.List;

public class User {


    private String name;

    private String password;

    private String hashPassword;

    private List<Ticket> bookedTickets;

    private String userId;

}
