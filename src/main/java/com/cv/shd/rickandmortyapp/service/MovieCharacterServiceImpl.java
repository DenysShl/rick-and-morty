package com.cv.shd.rickandmortyapp.service;

import com.cv.shd.rickandmortyapp.dto.external.ApiCharacterDto;
import com.cv.shd.rickandmortyapp.dto.external.ApiResponseDto;
import com.cv.shd.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import com.cv.shd.rickandmortyapp.model.MovieCharacter;
import com.cv.shd.rickandmortyapp.repository.MovieCharacterRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository repository;
    private final MovieCharacterMapper mapper;

    public MovieCharacterServiceImpl(HttpClient httpClient,
                                     MovieCharacterRepository movieCharacterRepository,
                                     MovieCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.repository = movieCharacterRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    @Scheduled(cron = "0 8 * * *")
    @Override
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters method was invoked at {}", LocalDateTime.now());
        ApiResponseDto apiResponseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ApiResponseDto.class);

        saveDtosToDB(apiResponseDto);

        while (apiResponseDto.getInfo().getNext() != null) {
            httpClient.get(apiResponseDto.getInfo().getNext(), ApiResponseDto.class);
            saveDtosToDB(apiResponseDto);
        }
    }

    @Override
    public List<MovieCharacter> getAllByName(String name) {
        return repository.findAllByNameContains(name);
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = repository.count();
        long randomId = (long) (Math.random() * count);
        return repository.getById(randomId);
    }

    private void saveDtosToDB(ApiResponseDto apiResponseDto) {
        Map<Long, ApiCharacterDto> externalDto = Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));

        Set<Long> externalIds = externalDto.keySet();

        List<MovieCharacter> existingCharacters = repository.findAllByExternalIdIn(externalIds);

        Map<Long, MovieCharacter> existingCharactersIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));

        Set<Long> existingIds = existingCharactersIds.keySet();

        externalIds.removeAll(existingIds);

        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(i -> mapper.parseApiCharacterResponseDto(externalDto.get(i)))
                .collect(Collectors.toList());

        repository.saveAll(charactersToSave);
    }
}
