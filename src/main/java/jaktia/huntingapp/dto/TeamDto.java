package jaktia.huntingapp.dto;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private int id;
    @NotEmpty(message = "Team name is required")
    private String name;
    @NotEmpty(message = "Description name is required")
    private String description;
    private Set<@Valid PersonDto> members = new HashSet<>();
    @Valid
    private PersonDto teamLeader;
    @Valid
    private HuntingZoneDto zone;
}
