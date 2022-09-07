package com.cv.shd.rickandmortyapp.dto.mapper;

import com.cv.shd.rickandmortyapp.dto.CharacterResponseDto;
import com.cv.shd.rickandmortyapp.dto.external.ApiCharacterDto;
import com.cv.shd.rickandmortyapp.model.Gender;
import com.cv.shd.rickandmortyapp.model.MovieCharacter;
import com.cv.shd.rickandmortyapp.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieCharacterMapperTest {
    private final MovieCharacterMapper mapper = new MovieCharacterMapper();
    private static MovieCharacter movieCharacter;
    private static ApiCharacterDto dto;
    private static CharacterResponseDto characterResponseDto;

    @BeforeAll
    static void beforeAll() {
        movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick");
        movieCharacter.setGender(Gender.MALE);
        movieCharacter.setStatus(Status.ALIVE);

        dto = new ApiCharacterDto();
        dto.setId(1L);
        dto.setName("Rick");
        dto.setGender(String.valueOf(Gender.MALE));
        dto.setStatus(String.valueOf(Status.ALIVE));

        characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(1L);
        characterResponseDto.setExternalId(1L);
        characterResponseDto.setName("Rick");
        characterResponseDto.setStatus(Status.ALIVE);
        characterResponseDto.setGender(Gender.MALE);
    }

    @Test
    void parseApiCharacterResponseDto_Ok() {
        MovieCharacter actual = mapper.parseApiCharacterResponseDto(dto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(movieCharacter.getExternalId(), actual.getExternalId());
        Assertions.assertEquals(movieCharacter.getName(), actual.getName());
        Assertions.assertEquals(movieCharacter.getGender(), actual.getGender());
        Assertions.assertEquals(movieCharacter.getStatus(), actual.getStatus());
    }

    @Test
    void toResponseDto_Ok() {
        CharacterResponseDto actual = mapper.toResponseDto(movieCharacter);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(characterResponseDto, actual);
        Assertions.assertEquals(characterResponseDto.getName(), actual.getName());
    }
}
