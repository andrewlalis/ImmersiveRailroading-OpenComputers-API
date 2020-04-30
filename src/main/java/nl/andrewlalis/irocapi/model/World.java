package nl.andrewlalis.irocapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

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
    @JsonIgnore
    private String token;

    /**
     * The set of rolling stock that belong to this world.
     */
    @OneToMany(mappedBy = "world")
    private Set<RollingStock> rollingStocks;

    public World(String name, String token) {
        this.name = name;
        this.token = token;
        this.rollingStocks = new HashSet<>();
    }
}
