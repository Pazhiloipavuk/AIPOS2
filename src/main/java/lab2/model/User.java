package lab2.model;

import javassist.runtime.Desc;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@ToString(of = {"id", "username"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_model")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToMany(mappedBy = "author")
    private Collection<Description> descriptions;

    @OneToMany(mappedBy = "author")
    private Collection<Comment> comments;
}
