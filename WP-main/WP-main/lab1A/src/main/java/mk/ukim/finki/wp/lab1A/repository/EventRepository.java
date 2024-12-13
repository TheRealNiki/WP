package mk.ukim.finki.wp.lab1A.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1A.model.Event;
import mk.ukim.finki.wp.lab1A.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
        private final List<Event> events;
        private final List<EventBooking> bookings;

    public EventRepository() {
        this.events = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        events.add(new Event("Music Festival", "A grand music festival with various artists.", 8.5));
        events.add(new Event("Tech Conference", "Latest innovations and trends in technology.", 9.0));
        events.add(new Event("Art Exhibition", "Display of modern and classical artworks.", 7.3));
        events.add(new Event("Food Carnival", "A gathering of the best food vendors.", 8.0));
        events.add(new Event("Sports Championship", "An intense sports competition.", 8.9));
        events.add(new Event("Book Fair", "A fair for book lovers with various publishers.", 7.8));
        events.add(new Event("Film Screening", "Exclusive screening of a new movie.", 6.7));
        events.add(new Event("Charity Gala", "A charity event for raising funds.", 8.2));
        events.add(new Event("Comedy Show", "A night of laughs with popular comedians.", 7.5));
        events.add(new Event("Science Fair", "Exhibits and experiments from science enthusiasts.", 7.9));
    }
    public List<Event> findAll(){
        return events;
    }
    public List<Event> searchEvents(String text){
        return events.stream()
                .filter(e->e.getName().contains(text) || e.getDescription().contains(text))
                .toList();
    }
    public List<Event> searchEventsByRating(Integer ps){
        return events.stream()
                .filter(e->e.getPopularityScore()>=ps)
                .toList();
    }

    public void placeBooking(EventBooking booking){
        bookings.removeIf(b->b.getAttendeeName().equals(booking.getAttendeeName()));
        bookings.add(booking);
    }
    public Optional<EventBooking> getBookingByUserName(String username){
        return bookings.stream().filter(b->b.getAttendeeName().equals(username)).findFirst();
    }

}
