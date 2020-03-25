package lab2.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@ToString(of = {"id", "title", "content"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "description_model")
public class Description {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @OneToOne
    private Task task;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "description")
    private Collection<Comment> comments;
}