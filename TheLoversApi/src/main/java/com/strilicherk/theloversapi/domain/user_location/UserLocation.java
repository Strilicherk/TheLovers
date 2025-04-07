package com.strilicherk.theloversapi.domain.user_location;

import com.strilicherk.theloversapi.domain.city.City;
import com.strilicherk.theloversapi.domain.country.Country;
import com.strilicherk.theloversapi.domain.state.State;
import com.strilicherk.theloversapi.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import java.util.UUID;

@Table(name = "user_locations")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLocation {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(columnDefinition = "geography(Point,4326)", nullable = false)
    private Point location;
}
