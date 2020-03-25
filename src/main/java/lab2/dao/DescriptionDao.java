package lab2.dao;

import lab2.model.Description;
import org.springframework.data.repository.CrudRepository;

public interface DescriptionDao extends CrudRepository<Description, Long> {
}
