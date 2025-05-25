package org.example.tpo_6.service;

import lombok.RequiredArgsConstructor;
import org.example.tpo_6.dto.CreateTaskDto;
import org.example.tpo_6.dto.TaskDto;
import org.example.tpo_6.dto.UpdateTaskDto;
import org.example.tpo_6.model.Task;
import org.example.tpo_6.repository.TaskRepository;
import org.example.tpo_6.util.SortFieldRegistry;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SortFieldRegistry sortFieldRegistry;

    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::toDto);
    }

    public List<TaskDto> getTasks() {
        return taskRepository.findAll()
                             .stream()
                             .map(this::toDto)
                             .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksSorted(String sortedBy, boolean desc) {
        if(!sortFieldRegistry.isAllowed(sortedBy))
            throw new IllegalArgumentException("Sorted by " + sortedBy + " is not allowed");

        Sort sort = desc ? Sort.by(sortedBy).descending() : Sort.by(sortedBy).ascending();
        return taskRepository.findAll(sort)
                             .stream()
                             .map(this::toDto)
                             .collect(Collectors.toList());
    }

    public TaskDto addTask(CreateTaskDto dto) {
        Task task = Task.builder()
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .dueTime(dto.getDueTime())
                        .priority(dto.getPriority() != null ? dto.getPriority() : 1)
                        .completed(false)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
        return toDto(taskRepository.save(task));
    }

    public TaskDto updateTask(Long id, UpdateTaskDto taskDto) {
        return taskRepository.findById(id).map(task -> {
            if(taskDto.getTitle() != null)
                task.setTitle(taskDto.getTitle());
            if(taskDto.getDescription() != null)
                task.setDescription(taskDto.getDescription());
            if(taskDto.getDueTime() != null)
                task.setDueTime(taskDto.getDueTime());
            if(taskDto.getPriority() != null)
                task.setPriority(taskDto.getPriority());
            if(taskDto.getCompleted() != null)
                task.setCompleted(taskDto.getCompleted());
            task.setUpdatedAt(LocalDateTime.now());
            return toDto(taskRepository.save(task));
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toDto(Task task) {
        return TaskDto.builder()
                      .id(task.getId())
                      .title(task.getTitle())
                      .description(task.getDescription())
                      .dueTime(task.getDueTime())
                      .completed(task.isCompleted())
                      .priority(task.getPriority())
                      .createdAt(task.getCreatedAt())
                      .updatedAt(task.getUpdatedAt())
                      .build();
    }

}
