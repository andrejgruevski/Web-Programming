package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workshop {
    private String title;
    private String category;
    private Long id;
    double price;
    int capacity;

    private Instructor instructor;
}

