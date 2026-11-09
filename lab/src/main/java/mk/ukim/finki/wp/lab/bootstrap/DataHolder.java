package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //kazhuvame na spring boot deka ovaa klasa treba da se upravuva od Spring
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    public static List<Instructor> instructors = null;

    @PostConstruct // init ke se izvrshi avtomatski otkatko Spring ke ja kreira data holder komponentata
    public void init() {
        workshops = new ArrayList<>();

        instructors = new ArrayList<>();

        instructors.add(new Instructor(1L, "Andrej", "Gruevski", "andrej@gmail.com", "Expertise 1"));
        instructors.add(new Instructor(2L, "Marija", "Spasenoska", "marija@gmail.com", "Expertise 2"));
        instructors.add(new Instructor(3L, "Marko", "Alchevski", "marko@gmail.com", "Expertise 3"));

        workshops.add(new Workshop("Rabotilnica 1", "Kategorija 1", 1L, 1200, 20, instructors.get(0)));
        workshops.add(new Workshop("Rabotilnica 2", "Kategorija 2", 2L, 1500, 15, instructors.get(1)));
        workshops.add(new Workshop("Rabotilnica 3", "Kategorija 3", 3L, 2000, 10, instructors.get(2)));
        workshops.add(new Workshop("Rabotilnica 4", "Kategorija 4", 4L, 1000, 12, instructors.get(0)));
        workshops.add(new Workshop("Rabotilnica 5", "Kategorija 5", 5L, 800, 0, instructors.get(1)));
        workshops.add(new Workshop("Rabotilnica 6", "Kategorija 6", 6L, 1800, 25, instructors.get(2)));
        workshops.add(new Workshop("Rabotilnica 7", "Kategorija 7", 7L, 2200, 8, instructors.get(0)));
        workshops.add(new Workshop("Rabotilnica 8", "Kategorija 8", 8L, 2500, 5, instructors.get(1)));
        workshops.add(new Workshop("Rabotilnica 9", "Kategorija 9", 9L, 3000, 10, instructors.get(2)));
        workshops.add(new Workshop("Rabotilnica 10", "Kategorija 10", 10L, 1700, 18, instructors.get(0)));

        registrations = new ArrayList<>();
    }
}
