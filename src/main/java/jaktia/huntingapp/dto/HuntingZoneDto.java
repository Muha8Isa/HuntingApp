package jaktia.huntingapp.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HuntingZoneDto {
    private Integer id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Description is required")
    private String description;
    @NotEmpty(message = "Location is required")
    private String location;
    @NotEmpty(message = "Terrain is required")
    private String terrain;
}
