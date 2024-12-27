package mk.ukim.finki.wp.lab1A.service.impl;

import mk.ukim.finki.wp.lab1A.model.Event;
import mk.ukim.finki.wp.lab1A.repository.EventRepository;
import mk.ukim.finki.wp.lab1A.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEventService implements EventService {
    private final EventRepository eventRepository;

    public IEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return processString(text);
    }

    public List<Event> processString(String input) {
        try {
            int number = Integer.parseInt(input);
            return useAsInteger(number);
        } catch (NumberFormatException e) {
            return useAsString(input);
        }
    }

    private List<Event> useAsInteger(int number) {
        return eventRepository.searchEventsByRating(number);
    }

    private List<Event> useAsString(String input) {
        return eventRepository.searchEvents(input);
    }

}
