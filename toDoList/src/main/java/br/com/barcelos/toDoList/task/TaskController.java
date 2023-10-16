package br.com.barcelos.toDoList.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getEndAt()) || currentDate.isAfter(taskModel.getStartAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de início e/ou de término devem ser após a data atual");
        }
        if (!!taskModel.getEndAt().isBefore(taskModel.getStartAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser antes a data de término");
        }

        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var newTask = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/")
    public List<TaskModel> getAllTasksByUserId(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var userTasks = this.taskRepository.findByUserId((UUID) userId);
        return userTasks;
    }
}
