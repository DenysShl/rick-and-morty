package com.cv.shd.rickandmortyapp.dto;

import com.cv.shd.rickandmortyapp.model.Gender;
import com.cv.shd.rickandmortyapp.model.Status;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
