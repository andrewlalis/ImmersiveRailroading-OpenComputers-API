package nl.andrewlalis.irocapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.andrewlalis.irocapi.model.World;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class WorldResponse {
    private String name;
    private String token;
    private String createdAt;
    private String updatedAt;

    public WorldResponse(World world) {
        this(
                world.getName(),
                world.getToken(),
                world.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME),
                world.getUpdatedAt().format(DateTimeFormatter.ISO_DATE_TIME)
        );
    }
}
