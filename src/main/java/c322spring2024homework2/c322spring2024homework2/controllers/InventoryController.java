package c322spring2024homework2.c322spring2024homework2.controllers;

import c322spring2024homework2.c322spring2024homework2.model.Builder;
import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import c322spring2024homework2.c322spring2024homework2.model.Type;
import c322spring2024homework2.c322spring2024homework2.model.Wood;
import c322spring2024homework2.c322spring2024homework2.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin

public class InventoryController {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    InventoryRepository inventoryRepository = new InventoryRepository();
    @GetMapping("/search")
    public List<Guitar> search(@RequestParam (required = false) String serialNumber, @RequestParam (required = false) String price,
                               @RequestParam (required = false) String builder, @RequestParam(required = false) String model,
                               @RequestParam (required = false) String type, @RequestParam (required = false) String backwood,
                               @RequestParam (required = false) String topwood){
        Guitar guitar;
        if(price == null){
             guitar = new Guitar(serialNumber, 0, (builder==null)?Builder.ANY:Builder.valueOf(builder), model, (type==null)?Type.ANY:Type.valueOf(type) , (backwood==null)?Wood.ANY:Wood.valueOf(backwood), (topwood==null)?Wood.ANY:Wood.valueOf(topwood));
        }else {
            guitar = new Guitar(serialNumber, Double.parseDouble(price), (builder==null)?Builder.ANY:Builder.valueOf(builder), model, (type==null)?Type.ANY:Type.valueOf(type) , (backwood==null)?Wood.ANY:Wood.valueOf(backwood), (topwood==null)?Wood.ANY:Wood.valueOf(topwood));
        }
        try {
            return inventoryRepository.search(guitar);
        } catch (IOException e) {
            return null;
        }
    }
    @PostMapping("/add")
    public boolean add(@RequestBody Guitar guitar){
        try{
            return inventoryRepository.addGuitar(guitar);

        }catch(IOException e){
            return false;
        }
    }
    @GetMapping("/find/{serialNumber}")
    public Guitar find(@PathVariable String serialNumber){
        try {
            return inventoryRepository.getGuitar(serialNumber);
        } catch (IOException e) {
            return null;
        }
    }

}
