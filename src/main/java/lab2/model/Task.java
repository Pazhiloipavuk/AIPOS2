package lab2.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_model")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "task")
    private Description description;
}
