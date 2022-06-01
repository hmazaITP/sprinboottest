package itpatagonia.com.sprinboottest.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "actor")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "surname", length = 25)
    private String surname;

    @Column(name = "name", length = 35)
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

}