package org.example.tpo_6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "due_time")
    private LocalDateTime dueTime;

    private boolean completed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int priority;

    private String owner;

    private boolean archived;
}
