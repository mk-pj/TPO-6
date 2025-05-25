package org.example.tpo_6.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskDto {

    @NotBlank(message = "Title cannot be empty!")
    private String title;

    private String description;

   @NotNull(message = "Due time is required!")
   @Future(message = "Invalid date!")
   private LocalDateTime dueTime;

   private Integer priority;
}
