package org.example.tpo_6.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Component
public class SortFieldRegistry {

    private final Set<String> allowedFields = Set.of(
            "id", "title", "dueTime", "priority", "createdAt", "updatedAt"
    );

    public boolean isAllowed(String field) {
        return allowedFields.contains(field);
    }
}
