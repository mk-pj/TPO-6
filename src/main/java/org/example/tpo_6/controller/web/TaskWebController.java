package org.example.tpo_6.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tpo_6.dto.CreateTaskDto;
import org.example.tpo_6.dto.TaskDto;
import org.example.tpo_6.dto.UpdateTaskDto;
import org.example.tpo_6.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskWebController {

    private final TaskService taskService;

    @GetMapping
    public String listTasks(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "false") boolean desc,
            Model model
    ) {
        model.addAttribute("tasks", taskService.getTasksSorted(sort, desc));
        return "list";
    }

    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new CreateTaskDto());
        return "form";
    }

    @PostMapping
    public String createTask(
            @ModelAttribute("task") @Valid CreateTaskDto dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "form";
        }
        taskService.addTask(dto);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        TaskDto task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        UpdateTaskDto updateTaskDto = UpdateTaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .dueTime(task.getDueTime())
                .priority(task.getPriority())
                .completed(task.getCompleted())
                .build();

        model.addAttribute("task", updateTaskDto);
        model.addAttribute("taskId", id);
        return "form";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(
            @PathVariable Long id,
            @ModelAttribute("task") @Valid UpdateTaskDto dto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("taskId", id);
            return "form";
        }

        taskService.updateTask(id, dto);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}
