package org.example.tpo_6.dto;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskDto {
    private String title;
    private String description;

    @Future(message = "Invalid date!")
    private LocalDateTime dueTime;
    private Boolean completed;
    private Integer priority;
}
