package c322spring2024homework2.c322spring2024homework2.repository;

import c322spring2024homework2.c322spring2024homework2.model.Builder;
import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import c322spring2024homework2.c322spring2024homework2.model.Type;
import c322spring2024homework2.c322spring2024homework2.model.Wood;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    @Test
    void addGuitar() {
        InventoryRepository inventoryRepository = new InventoryRepository();
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
        InventoryRepository inventoryRepository = new InventoryRepository();
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

    @Test
    void search() {
        // Act: Perform a search
        InventoryRepository inventoryRepository = new InventoryRepository();
        Guitar guitar =  new Guitar(null, 0.0, Builder.ANY, null, Type.ELECTRIC, Wood.ANY, Wood.ANY);

        List<Guitar> result = null;
        try {
            result = inventoryRepository.search(guitar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert: Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());

    }
}