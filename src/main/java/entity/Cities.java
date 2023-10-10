package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "favourite_cities", schema = "public", catalog = "postgres")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Basic
    @Column(name = "cities")
    private String cities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}