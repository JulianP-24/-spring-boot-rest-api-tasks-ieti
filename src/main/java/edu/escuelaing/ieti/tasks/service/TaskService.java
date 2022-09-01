package edu.escuelaing.ieti.tasks.service;

import edu.escuelaing.ieti.tasks.dto.TaskDto;
import edu.escuelaing.ieti.tasks.entities.Task;
import java.util.List;

public interface TaskService {
    Task create(Task task );
    Task findById( String id );
    List<Task> getAll();
    boolean deleteById( String id );
    Task update( Task task, String id );
    TaskDto mapToDto(Task task);
    Task mapToEntity(TaskDto taskDto);
}
