package edu.escuelaing.ieti.tasks.repository;

import edu.escuelaing.ieti.tasks.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
