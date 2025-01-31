package mk.ukim.finki.wp.lab1A.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventBooking {
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;
}
