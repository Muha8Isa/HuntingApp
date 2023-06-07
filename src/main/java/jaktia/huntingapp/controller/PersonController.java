package jaktia.huntingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jaktia.huntingapp.dto.PersonDto;
import jaktia.huntingapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
    @RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public ResponseEntity<List<PersonDto>> getAll(){ // ResponseEntity<> converts List<> to a JSON message, where List<> is the message body.
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAll());
    }

    @Operation(summary = "Get a person by its id") // Swagger UI
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(personService.findById(id));
    }

    @Operation(summary = "Add a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added person",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example", implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
    @PostMapping("/add")
    //@PreAuthorize("hasAuthority(T(jaktia.huntingapp.Enum.Role).ROLE_ADMIN.name())") // T is a type operator used to reference a class or type. It allows you to access static members (fields, methods, and enum values) of a class within an expression.
    public ResponseEntity<PersonDto> add(@RequestBody @Valid PersonDto dto){ // @Valid takes the annotations in PersonDto (NotEmpty, size....etc) into consideration.
        PersonDto addedPersonDto = personService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPersonDto); // 201
    }

    @PutMapping("/update")
    //@PreAuthorize("hasAuthority(T(jaktia.huntingapp.Enum.Role).ROLE_ADMIN.name())")
    public ResponseEntity<Void> update(@RequestBody @Valid PersonDto dto){
        personService.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority(T(jaktia.huntingapp.Enum.Role).ROLE_ADMIN.name())")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){ // Delete is a void method; there is no return type.
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
