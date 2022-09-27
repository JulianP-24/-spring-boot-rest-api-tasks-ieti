package edu.escuelaing.ieti.tasks.service.impl;

import edu.escuelaing.ieti.tasks.dto.TaskDto;
import edu.escuelaing.ieti.tasks.entities.Task;
import edu.escuelaing.ieti.tasks.repository.TaskRepository;
import edu.escuelaing.ieti.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceMongoDB implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceMongoDB(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findById(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e){
            new Exception("La tarea con id: " + id + "no existe", e);
            return false;
        }
    }

    @Override
    public Task update(Task task, String id) {
        Task updateTask = findById(id);
        updateTask.setName(task.getName());
        updateTask.setDescription(task.getDescription());
        updateTask.setStatus(task.getStatus());
        updateTask.setAssignedTo(task.getAssignedTo());
        updateTask.setDueDate(task.getDueDate());
        return create(updateTask);
    }

    @Override
    public TaskDto mapToDto(Task task) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setAssignedTo(task.getAssignedTo());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setDueDate(task.getDueDate());
        return taskDTO;
    }

    @Override
    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setAssignedTo(taskDto.getAssignedTo());
        task.setCreatedAt(taskDto.getCreatedAt());
        task.setDueDate(taskDto.getDueDate());
        return task;
    }
}
