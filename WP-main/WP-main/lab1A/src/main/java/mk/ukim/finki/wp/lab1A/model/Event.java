package mk.ukim.finki.wp.lab1A.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Event {
    private String name;
    private String description;
    private double popularityScore;
}
