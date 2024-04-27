package c322spring2024homework2.c322spring2024homework2.controllers;

import c322spring2024homework2.c322spring2024homework2.model.Builder;
import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import c322spring2024homework2.c322spring2024homework2.model.Type;
import c322spring2024homework2.c322spring2024homework2.model.Wood;
import c322spring2024homework2.c322spring2024homework2.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class InventoryController {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    @Autowired
    InventoryRepository inventoryRepository;
    @GetMapping("/search")
    public List<Guitar> search(@RequestParam (required = false) String serialNumber, @RequestParam (required = false) String price,
                               @RequestParam (required = false) String builder, @RequestParam(required = false) String model,
                               @RequestParam (required = false) String type, @RequestParam (required = false) String backwood,
                               @RequestParam (required = false) String topwood){
        Guitar searchGuitar;
        if(price == null){
            searchGuitar = new Guitar(serialNumber, 0, (builder==null)?Builder.ANY:Builder.valueOf(builder), model, (type==null)?Type.ANY:Type.valueOf(type) , (backwood==null)?Wood.ANY:Wood.valueOf(backwood), (topwood==null)?Wood.ANY:Wood.valueOf(topwood));
        }else {
            searchGuitar = new Guitar(serialNumber, Double.parseDouble(price), (builder==null)?Builder.ANY:Builder.valueOf(builder), model, (type==null)?Type.ANY:Type.valueOf(type) , (backwood==null)?Wood.ANY:Wood.valueOf(backwood), (topwood==null)?Wood.ANY:Wood.valueOf(topwood));
        }


        List<Guitar> result = inventoryRepository.findAll();
        return result.stream()
                .filter(guitar ->
                        (guitar.getBuilder() == Builder.ANY || guitar.getBuilder().equals(searchGuitar.getBuilder())) &&
                                (searchGuitar.getBackwood() == Wood.ANY || guitar.getBackwood().equals(searchGuitar.getBackwood())) &&
                                (searchGuitar.getModel() == null || guitar.getModel().equalsIgnoreCase(searchGuitar.getModel())) &&
                                (searchGuitar.getTopwood() == Wood.ANY || guitar.getTopwood().equals(searchGuitar.getTopwood())) &&
                                (searchGuitar.getType() == Type.ANY || guitar.getType().equals(searchGuitar.getType())) &&
                                (searchGuitar.getSerialNumber() == null || guitar.getSerialNumber().equals(searchGuitar.getSerialNumber())) &&
                                (searchGuitar.getPrice() == 0 || guitar.getPrice() == searchGuitar.getPrice())
                )
                .collect(Collectors.toList());
    }
    @PostMapping("/add")
    public Guitar add(@RequestBody Guitar guitar){
        try{
            System.out.println(guitar.toString());
            return inventoryRepository.save(guitar);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/find/{serialNumber}")
    public Guitar find(@PathVariable String serialNumber){
        try {
            return inventoryRepository.findBySerialNumber(serialNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
