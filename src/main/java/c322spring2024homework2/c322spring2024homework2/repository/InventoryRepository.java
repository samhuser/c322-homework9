package c322spring2024homework2.c322spring2024homework2.repository;

import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Guitar, String> {
    List<Guitar> findAll();

    Guitar findBySerialNumber(String s);
}
