package edu.escuelaing.ieti.tasks.service.impl;

import edu.escuelaing.ieti.tasks.dto.TaskDto;
import edu.escuelaing.ieti.tasks.entities.Task;
import edu.escuelaing.ieti.tasks.enums.Status;
import edu.escuelaing.ieti.tasks.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceHashMap implements TaskService {

    private final Map<String, Task> taskHashMap = new HashMap<>();

    public TaskServiceHashMap(){
        taskHashMap.put("1",new Task(new TaskDto("Revisar bug pruebas", "Corregir bugs en las pruebas del login", Status.TODO, "Julian", "2022/05/28"),"1", new Date()));
    }

    @Override
    public Task create(Task task) {
        return taskHashMap.put(task.getId(), task);
    }

    @Override
    public Task findById(String id) {
        return taskHashMap.get(id);
    }

    @Override
    public List<Task> getAll() {
        return new ArrayList<Task>(taskHashMap.values());
    }

    @Override
    public boolean deleteById(String id) {
        try {
            taskHashMap.remove(id);
            return true;
        } catch (Exception e){
            new Exception("La tarea con id: " + id + "no existe", e);
            return false;
        }
    }

    @Override
    public Task update(Task task, String id) {
        return taskHashMap.put(id, task);
    }

    @Override
    public TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setAssignedTo(task.getAssignedTo());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setCreatedAt(task.getCreatedAt());
        return taskDto;
    }

    @Override
    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setAssignedTo(taskDto.getAssignedTo());
        task.setDueDate(taskDto.getDueDate());
        task.setCreatedAt(taskDto.getCreatedAt());
        return task;
    }
}
