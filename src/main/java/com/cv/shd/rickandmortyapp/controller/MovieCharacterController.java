package com.cv.shd.rickandmortyapp.controller;

import com.cv.shd.rickandmortyapp.dto.CharacterResponseDto;
import com.cv.shd.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import com.cv.shd.rickandmortyapp.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService,
                                    MovieCharacterMapper movieCharacterMapper) {
        this.movieCharacterService = movieCharacterService;
        this.mapper = movieCharacterMapper;
    }

    @ApiOperation("Get random character")
    @GetMapping("/random")
    public CharacterResponseDto getRandom() {
        return mapper.toResponseDto(movieCharacterService.getRandomCharacter());
    }

    @ApiOperation("Find character by name")
    @GetMapping("/by-name")
    public List<CharacterResponseDto> getByName(
            @ApiParam(value = "Enter name, default `Rick`")
            @RequestParam(defaultValue = "Rick", value = "name") String namePart) {
        return movieCharacterService.getAllByName(namePart)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
