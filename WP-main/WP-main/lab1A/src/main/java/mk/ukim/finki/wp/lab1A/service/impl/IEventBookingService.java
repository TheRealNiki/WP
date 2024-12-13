package mk.ukim.finki.wp.lab1A.service.impl;

import mk.ukim.finki.wp.lab1A.model.EventBooking;
import mk.ukim.finki.wp.lab1A.repository.EventRepository;
import mk.ukim.finki.wp.lab1A.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IEventBookingService implements EventBookingService {
    private final EventRepository eventRepository;

    public IEventBookingService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking newBooking = new EventBooking(eventName,attendeeName,attendeeAddress, (long) numberOfTickets);
        eventRepository.placeBooking(newBooking);
    }

    @Override
    public Optional<EventBooking> getBookingByUserName(String username) {
        return eventRepository.getBookingByUserName(username);
    }
}
