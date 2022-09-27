package edu.escuelaing.ieti.tasks.controller;

import edu.escuelaing.ieti.tasks.dto.TaskDto;
import edu.escuelaing.ieti.tasks.entities.Task;
import edu.escuelaing.ieti.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/v1/tasks" )
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> listTasksDto = new ArrayList<>();
        List<Task> listTasks = taskService.getAll();
        for (Task tasks : listTasks) {
            listTasksDto.add(taskService.mapToDto(tasks));
        }
        return new ResponseEntity<List<TaskDto>>(listTasksDto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable String id) {
        Task task = taskService.findById(id);
        return new ResponseEntity<TaskDto>(taskService.mapToDto(task), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create (@RequestBody TaskDto taskDto) {
        List<Task> tasks = taskService.getAll();
        String id = "1";
        if (tasks.size() > 0){
            id = String.valueOf((Integer.parseInt(tasks.get(tasks.size()-1).getId()) + 1));
         }
        Task newTask = taskService.create(new Task(taskDto, id, new Date()));
        return new ResponseEntity<TaskDto>(taskService.mapToDto(newTask), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto taskDto, @PathVariable String id){
        Task updateTask = new Task(taskDto, id, taskService.findById(id).getCreatedAt());
        return new ResponseEntity<TaskDto>(taskService.mapToDto(taskService.update(updateTask, id)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
