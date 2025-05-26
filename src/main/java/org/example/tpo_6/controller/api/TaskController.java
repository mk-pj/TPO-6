package org.example.tpo_6.controller.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tpo_6.dto.CreateTaskDto;
import org.example.tpo_6.dto.TaskDto;
import org.example.tpo_6.dto.UpdateTaskDto;
import org.example.tpo_6.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/sorted")
    public List<TaskDto> getTasksSorted(
            @RequestParam(required = true, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "false") boolean desc
    ) {
        return taskService.getTasksSorted(sortBy, desc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = "id")
    public ResponseEntity<TaskDto> getTaskByIdParam(@RequestParam Long id) {
        return taskService.getTaskById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskDto dto) {
        TaskDto created = taskService.addTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto dto) {
        TaskDto updated = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
