package org.example.tpo_6.util;

import org.example.tpo_6.model.Task;
import org.example.tpo_6.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(TaskRepository taskRepository) {
        return args -> {
            if (taskRepository.count() == 0) {
                taskRepository.save(Task.builder()
                        .title("Write report")
                        .description("Complete the monthly report")
                        .dueTime(LocalDateTime.now().plusDays(3))
                        .completed(false)
                        .createdAt(LocalDateTime.now())
                        .priority(3)
                        .owner("admin")
                        .archived(false)
                        .build());

                taskRepository.save(Task.builder()
                        .title("Fix login bug")
                        .description("Resolve issue with login system")
                        .dueTime(LocalDateTime.now().plusDays(1))
                        .completed(false)
                        .createdAt(LocalDateTime.now())
                        .priority(5)
                        .owner("admin")
                        .archived(false)
                        .build());

                taskRepository.save(Task.builder()
                        .title("Plan sprint")
                        .description("Organize sprint backlog")
                        .dueTime(LocalDateTime.now().plusDays(5))
                        .completed(false)
                        .createdAt(LocalDateTime.now())
                        .priority(2)
                        .owner("admin")
                        .archived(false)
                        .build());

                taskRepository.save(Task.builder()
                        .title("Team meeting")
                        .description("Discuss project status")
                        .dueTime(LocalDateTime.now().plusDays(2))
                        .completed(false)
                        .createdAt(LocalDateTime.now())
                        .priority(4)
                        .owner("admin")
                        .archived(false)
                        .build());
            }
        };
    }
}
