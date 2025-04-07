package ticketing.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticketing.entities.Ticket;
import ticketing.entities.User;
import ticketing.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {} );

    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }


    public void fetchBooking() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Ticket ID to be cancelled: ");
        ticketId = sc.next();

        if(ticketId == null || ticketId.isEmpty()) {
            System.out.println("Invalid Ticket ID.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId; // because Strings are immutable
        boolean removed = user.getBookedTickets().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getBookedTickets().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));

        if(removed) {
            System.out.println("Ticket with ID: " + ticketId + " has been cancelled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID: " + ticketId);
            return Boolean.FALSE;
        }
    }
}


