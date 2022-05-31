package itpatagonia.com.sprinboottest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "actormovie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actorid")
    private Actor actorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid")
    private Movie movieid;

}