package org.example.tpo_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueTime;
    private Boolean completed;
    private Integer priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
