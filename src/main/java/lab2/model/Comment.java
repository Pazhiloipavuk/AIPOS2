package lab2.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(of = {"id", "content"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment_model")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private Description description;

    @ManyToOne
    private User author;
}