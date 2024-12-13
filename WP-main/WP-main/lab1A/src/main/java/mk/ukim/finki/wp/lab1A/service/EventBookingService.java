package mk.ukim.finki.wp.lab1A.service;

import mk.ukim.finki.wp.lab1A.model.EventBooking;

import java.util.Optional;

public interface EventBookingService{
    void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    Optional<EventBooking> getBookingByUserName(String username);
}
