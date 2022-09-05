package com.cv.shd.rickandmortyapp.dto.mapper;

import com.cv.shd.rickandmortyapp.dto.CharacterResponseDto;
import com.cv.shd.rickandmortyapp.dto.external.ApiCharacterDto;
import com.cv.shd.rickandmortyapp.model.Gender;
import com.cv.shd.rickandmortyapp.model.MovieCharacter;
import com.cv.shd.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponseDto(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public CharacterResponseDto toResponseDto(MovieCharacter character) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(character.getId());
        characterResponseDto.setExternalId(character.getExternalId());
        characterResponseDto.setName(character.getName());
        characterResponseDto.setStatus(character.getStatus());
        characterResponseDto.setGender(character.getGender());
        return characterResponseDto;
    }
}
