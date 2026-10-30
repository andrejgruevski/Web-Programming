package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //kazhuvame na spring boot deka ovaa klasa treba da se upravuva od Spring
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;

    @PostConstruct // init ke se izvrshi avtomatski otkatko Spring ke ja kreira data holder komponentata
    public void init() {
        workshops = new ArrayList<>();

        workshops.add(new Workshop("Rabotilnica 1", "Kategorija 1", 1200, 20));
        workshops.add(new Workshop("Rabotilnica 2", "Kategorija 2", 1500, 15));
        workshops.add(new Workshop("Rabotilnica 3", "Kategorija 3", 2000, 10));
        workshops.add(new Workshop("Rabotilnica 4", "Kategorija 4", 1000, 12));
        workshops.add(new Workshop("Rabotilnica 5", "Kategorija 5", 800, 0));
        workshops.add(new Workshop("Rabotilnica 6", "Kategorija 6", 1800, 25));
        workshops.add(new Workshop("Rabotilnica 7", "Kategorija 7", 2200, 8));
        workshops.add(new Workshop("Rabotilnica 8", "Kategorija 8", 2500, 5));
        workshops.add(new Workshop("Rabotilnica 9", "Kategorija 9", 3000, 10));
        workshops.add(new Workshop("Rabotilnica 10", "Kategorija 10", 1700, 18));

        registrations = new ArrayList<>();
    }
}
