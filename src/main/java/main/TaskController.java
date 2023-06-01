package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping(value = "/tasks/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> add(@RequestBody Task task) {
        Task newTask = new Task(LocalDateTime.now(), false, task.getTitle(), task.getDescription());
        taskRepository.save(newTask);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> patch(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task updatedTask = new Task();
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (task.getTitle() != null && !task.getTitle().equals(optionalTask.get().getTitle())) {
            updatedTask.setTitle(task.getTitle());
        } else {
            updatedTask.setTitle(optionalTask.get().getTitle());
        }
        if (task.getDescription() != null && !task.getDescription().equals(optionalTask.get().getDescription())) {
            updatedTask.setDescription(task.getDescription());
        } else {
            updatedTask.setDescription(optionalTask.get().getDescription());
        }
        if (task.isDone()) {
            updatedTask.setDone(task.isDone());
        } else {
            updatedTask.setDone(false);
        }
        updatedTask.setId(optionalTask.get().getId());
        updatedTask.setCreationTime(optionalTask.get().getCreationTime());
        taskRepository.save(updatedTask);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
