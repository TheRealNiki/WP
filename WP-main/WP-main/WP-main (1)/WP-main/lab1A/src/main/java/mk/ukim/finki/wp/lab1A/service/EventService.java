package mk.ukim.finki.wp.lab1A.service;

import mk.ukim.finki.wp.lab1A.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
}
