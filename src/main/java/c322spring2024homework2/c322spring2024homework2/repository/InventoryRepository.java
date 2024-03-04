package c322spring2024homework2.c322spring2024homework2.repository;

import c322spring2024homework2.c322spring2024homework2.model.Builder;
import c322spring2024homework2.c322spring2024homework2.model.Guitar;
import c322spring2024homework2.c322spring2024homework2.model.Type;
import c322spring2024homework2.c322spring2024homework2.model.Wood;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class InventoryRepository {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME= "guitars_database.txt";
    public boolean addGuitar(Guitar guitar) throws IOException{
        Path path = Paths.get(DATABASE_NAME);
        String data = guitar.getSerialNumber() +
                "," + guitar.getPrice() +
                "," + guitar.getBuilder() +
                "," + guitar.getModel() +
                "," + guitar.getType() +
                "," + guitar.getBackwood() +
                "," + guitar.getTopwood();
        Files.write(path,(data+NEW_LINE).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        return true;
    }
    public Guitar getGuitar(String serialNumber) throws IOException{
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line: data){
            if(line.contains(serialNumber)){
                String[] words = line.split(",");
                    return new Guitar(words[0],Double.parseDouble(words[1]),(words[2].equals("Unspecified")) ? Builder.valueOf("ANY"):Builder.valueOf(words[2].toUpperCase()), words[3],  (words[4].equals("unspecified")) ? Type.ANY:Type.valueOf(words[4].toUpperCase()), (words[5].equals("unspecified")) ? Wood.ANY : Wood.valueOf(words[5].toUpperCase().replace(' ', '_')), (words[6].equals("unspecified")) ? Wood.ANY:Wood.valueOf(words[6].toUpperCase().replace(' ', '_')));

            }
        }
        return null;

    }
    public List<Guitar> search(Guitar searchGuitar) throws IOException {
        List<Guitar> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line: data){
            String[] words = line.split(",");
            System.out.println(words);
            Guitar guitar = new Guitar(words[0],Double.parseDouble(words[1]),(words[2].equals("Unspecified")) ? Builder.valueOf("ANY"):Builder.valueOf(words[2].toUpperCase()), words[3],  (words[4].equals("unspecified")) ? Type.ANY:Type.valueOf(words[4].toUpperCase()), (words[5].equals("unspecified")) ? Wood.ANY : Wood.valueOf(words[5].toUpperCase().replace(' ', '_')), (words[6].equals("unspecified")) ? Wood.ANY:Wood.valueOf(words[6].toUpperCase().replace(' ', '_')));
            result.add(guitar);
            if (searchGuitar.getSerialNumber() == null){

            }
        }
        return result.stream()
                .filter(guitar ->
                        (searchGuitar.getBuilder() == Builder.ANY || guitar.getBuilder().equals(searchGuitar.getBuilder())) &&
                                (searchGuitar.getBackwood() == Wood.ANY || guitar.getBackwood().equals(searchGuitar.getBackwood())) &&
                                (searchGuitar.getModel() == null || guitar.getModel().equalsIgnoreCase(searchGuitar.getModel())) &&
                                (searchGuitar.getTopwood() == Wood.ANY || guitar.getTopwood().equals(searchGuitar.getTopwood())) &&
                                (searchGuitar.getType() == Type.ANY || guitar.getType().equals(searchGuitar.getType())) &&
                                (searchGuitar.getSerialNumber() == null || guitar.getSerialNumber().equals(searchGuitar.getSerialNumber())) &&
                                (searchGuitar.getPrice() == 0 || guitar.getPrice() == searchGuitar.getPrice())
                )
                .collect(Collectors.toList());

    }


}
