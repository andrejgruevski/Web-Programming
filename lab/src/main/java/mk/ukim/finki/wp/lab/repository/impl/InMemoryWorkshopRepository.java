package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.lab.model.Workshop;

import java.util.List;
import java.util.Objects;
import java.util.Optional;;

@Repository // pristap do podatoci
public class InMemoryWorkshopRepository implements WorkshopRepository {

    @Override
    public List<Workshop> findAll(){
        return DataHolder.workshops;
    }

    @Override
    public Optional<Workshop> findByTitle(String title){
        return DataHolder.workshops.stream().filter(w -> w.getTitle().equals(title)).findFirst();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return DataHolder.workshops.stream().filter(w -> text == null || text.isBlank()
                || w.getTitle().toLowerCase().contains(text.toLowerCase())
                || w.getCategory().toLowerCase().contains(text.toLowerCase()))
                .filter(w -> maxPrice == null || w.getPrice() <= maxPrice)
                .toList();
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return DataHolder.workshops.stream()
                .filter(workshop -> Objects.equals(workshop.getId(), id))
                .findFirst();
    }

    @Override
    public Workshop save(Workshop workshop) {

        if (workshop.getId() == null) {
            Long nextId = DataHolder.workshops.stream()
                    .map(Workshop::getId)
                    .filter(Objects::nonNull)
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0L) + 1;

            workshop.setId(nextId);
        }

        for (int i = 0; i < DataHolder.workshops.size(); i++) {
            if (Objects.equals(DataHolder.workshops.get(i).getId(),
                    workshop.getId())) {

                DataHolder.workshops.set(i, workshop);
                return workshop;
            }
        }

        DataHolder.workshops.add(workshop);
        return workshop;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.workshops.removeIf(
                workshop -> Objects.equals(workshop.getId(), id)
        );
    }
}
