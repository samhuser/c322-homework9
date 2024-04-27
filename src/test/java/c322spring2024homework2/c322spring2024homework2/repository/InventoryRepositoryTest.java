package c322spring2024homework2.c322spring2024homework2.repository;

import c322spring2024homework2.c322spring2024homework2.model.Builder;
import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import c322spring2024homework2.c322spring2024homework2.model.Type;
import c322spring2024homework2.c322spring2024homework2.model.Wood;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    @Test
    void addGuitar() {
        InventoryFileRepository inventoryRepository = new InventoryFileRepository();
        // Create a test guitar
        Guitar testGuitar = new Guitar(
                "123456",
                999.99,
                Builder.FENDER,
                "Stratocaster",
                Type.ELECTRIC,
                Wood.MAPLE,
                Wood.INDIAN_ROSEWOOD
        );

        boolean result;
        try {
            result = inventoryRepository.addGuitar(testGuitar);
        }catch (IOException e){
            result = false;
        }
        assertTrue(result);
        // Create another Guitar object for testing
        Guitar anotherTestGuitar = new Guitar(
                "789012",
                1499.99,
                Builder.GIBSON,
                "Les Paul",
                Type.ELECTRIC,
                Wood.MAHOGANY,
                Wood.MAPLE);
        boolean resultForAnotherGuitar = false;
        try {
            resultForAnotherGuitar = inventoryRepository.addGuitar(anotherTestGuitar);
        }catch (IOException e){
            resultForAnotherGuitar = false;
        }

        assertTrue(resultForAnotherGuitar);
    }

    @Test
    void getGuitar() {
        InventoryFileRepository inventoryRepository = new InventoryFileRepository();
        Guitar guitar = new Guitar(
                "789012",
                1499.99,
                Builder.GIBSON,
                "Les Paul",
                Type.ELECTRIC,
                Wood.MAHOGANY,
                Wood.MAPLE);
        Guitar result;
        try {
            result = inventoryRepository.getGuitar("789012");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(guitar, result);
    }
}