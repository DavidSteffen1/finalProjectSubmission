package com.promineotech.mwa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterWithWeapons;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/characters")

@OpenAPIDefinition(info = @Info(title = "Character List"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.") })

public interface CharacterController {

/**
 * @return
 * @GetMapping for Characters
 */
@Operation(
      summary = "Returns a list of all Characters or a Character by name or id",
      description = "Returns a list of all Characters or a apecific Character",
      responses = {
          @ApiResponse(responseCode = "200", 
                       description = "The selected Character(s) is/are returned", 
                       content = @Content(mediaType = "application/json", 
                       schema = @Schema(implementation = Character.class))),
          @ApiResponse(responseCode = "400", 
                       description = "The request parameters are invalid", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
                       description = "No Characters were found", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
                       description = "An unplanned error ocurred.", 
                       content = @Content(mediaType = "application/json"))
      }

  )

  @GetMapping("/all")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Character> fetchCharacters();

  @GetMapping("/id/{character_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Character> fetchCharacterById(@PathVariable("character_id") int character_id);
  
  @GetMapping("/name/{name}")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Character> fetchCharacterByName(@PathVariable("name") String name);


/**
 * @return 
 * @PostMapping for Characters
 */
@Operation(
		summary = "Create a new Character",
		description = "Returns the created Character",
		responses = {
				@ApiResponse(responseCode = "201", 
						description = "A newly created Character is returned", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Character.class))),
				@ApiResponse(responseCode = "400", 
				description = "The Character is invalid", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "404", 
				description = "No Characters were found", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "500", 
				description = "An unplanned error ocurred.", 
				content = @Content(mediaType = "application/json"))
		}

		)

@PostMapping("/new/")
@ResponseStatus(code = HttpStatus.CREATED)
  public Character createNewCharacter(@RequestBody String name);


/**
 * @return 
 * @PutMapping for characters
 */
@Operation(
		summary = "Updates an existing Characters name",
		description = "Returns the Character with updates applied",
		responses = {
				@ApiResponse(responseCode = "202", 
						description = "A Character has been updated", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Character.class))),
				@ApiResponse(responseCode = "400", 
				description = "The Character is invalid", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "404", 
				description = "No Characters were found", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "500", 
				description = "An unplanned error ocurred.", 
				content = @Content(mediaType = "application/json"))
		}
		
		)

@PutMapping("/update/{name}/{newName}")
@ResponseStatus(code = HttpStatus.ACCEPTED)
  public String updateCharacterName(@RequestBody @PathVariable("name") String name, @PathVariable("newName") String newName);

/**
 * @return 
 * @DeleteMapping for characters
 */
@Operation(
		summary = "Delete a Character",
		description = "Deletes the selected Character",
		responses = {
				@ApiResponse(responseCode = "204", 
						description = "A selected Character is deleted", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Character.class))),
				@ApiResponse(responseCode = "400", 
				description = "The Character is invalid", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "404", 
				description = "No Characters were found", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "500", 
				description = "An unplanned error ocurred.", 
				content = @Content(mediaType = "application/json"))
		},
		parameters = {
				@Parameter(name = "name", 
						allowEmptyValue = false, 
						required = true, 
						description = "The Character name"),

		}
		
		)

@DeleteMapping("/delete/{name}")
@ResponseStatus(code = HttpStatus.NO_CONTENT)
  public String deleteCharacter(@PathVariable("name") String name);


/**
 * @return
 * @GetMapping for many-to-many relationship between Character and Weapon
 */
@Operation(
	      summary = "Returns a list of all Characters and their equipped Weapons",
	      description = "Returns a list of all Characters and their equipped Weapons",
	      responses = {
	          @ApiResponse(responseCode = "200", 
	                       description = "A list of Characters and their equipped Weapons is returned", 
	                       content = @Content(mediaType = "application/json", 
	                       schema = @Schema(implementation = Character.class))),
	          @ApiResponse(responseCode = "400", 
	                       description = "The request parameters are invalid", 
	                       content = @Content(mediaType = "application/json")),
	          @ApiResponse(responseCode = "404", 
	                       description = "No Characters were found", 
	                       content = @Content(mediaType = "application/json")),
	          @ApiResponse(responseCode = "500", 
	                       description = "An unplanned error ocurred.", 
	                       content = @Content(mediaType = "application/json"))
	      }
	  )

	  @GetMapping("/weapons/all")
	  @ResponseStatus(code = HttpStatus.OK)
	  public List<CharacterWithWeapons> fetchCharactersWithWeapons();
}
