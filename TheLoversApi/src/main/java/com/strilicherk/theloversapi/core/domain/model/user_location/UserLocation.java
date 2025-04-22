package com.strilicherk.theloversapi.core.domain.model.user_location;

import com.strilicherk.theloversapi.core.domain.model.city.City;
import com.strilicherk.theloversapi.core.domain.model.country.Country;
import com.strilicherk.theloversapi.core.domain.model.state.State;
import com.strilicherk.theloversapi.core.domain.model.user.User;
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
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(columnDefinition = "geography(Point,4326)", nullable = false)
    private Point location;
//    private float latitude;
//    private float longitude;
}
