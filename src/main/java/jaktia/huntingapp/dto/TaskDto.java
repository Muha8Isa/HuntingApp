package jaktia.huntingapp.dto;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    @Valid
    private PersonDto assignee;
    private boolean done;
}
