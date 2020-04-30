package nl.andrewlalis.irocapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Represents a single Minecraft world.
 */
@Entity
@NoArgsConstructor
@Getter
public class World extends BaseEntity {
    public static final int TOKEN_LENGTH = 9;

    /**
     * The world's name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The token that's used to identify this world when requests are sent.
     */
    @Column(nullable = false, unique = true)
    private String token;

    public World(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
