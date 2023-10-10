package entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user_data", schema = "public", catalog = "postgres")
public class User {
    @Id
    @Column(name = "telegram_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Cities> cities;
}
