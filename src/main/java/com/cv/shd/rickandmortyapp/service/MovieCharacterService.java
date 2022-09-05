package com.cv.shd.rickandmortyapp.service;

import com.cv.shd.rickandmortyapp.model.MovieCharacter;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomCharacter();

    List<MovieCharacter> getAllByName(String name);
}
