package com.example.gameranking.service;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.exception.customized.ObjectNotFoundException;
import com.example.gameranking.model.Game;
import com.example.gameranking.model.GameRating;
import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.model.dto.input.GameRatingCreateRequestDTO;
import com.example.gameranking.model.dto.output.GamePagedResponseDTO;
import com.example.gameranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.example.gameranking.utils.GameRatingUtils.calculateAverage;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final ModelMapper mapper;

    public void create(GameCreateRequestDTO gameCreateRequestDTO) {
        //TODO: Criar regra em que a data não pode ser maior que o dia de hoje
        verifyIfNameExists(gameCreateRequestDTO.getName());

        validateDates(gameCreateRequestDTO);

        Game game = mapper.map(gameCreateRequestDTO, Game.class);
        save(game);
    }

    private static void validateDates(GameCreateRequestDTO gameCreateRequestDTO) {
        if(Objects.nonNull(gameCreateRequestDTO.getAllAchievementsDate()) &&
                gameCreateRequestDTO.getAllAchievementsDate().isAfter(LocalDate.now())){
            throw new BadRequestException("The All Achievements Date cannot be in the future.");
        }

        if(Objects.nonNull(gameCreateRequestDTO.getFinishDate()) &&
                gameCreateRequestDTO.getFinishDate().isAfter(LocalDate.now())){
            throw new BadRequestException("The Finish Date cannot be in the future.");
        }

        if(Objects.nonNull(gameCreateRequestDTO.getOneHundredPercentDate()) &&
                gameCreateRequestDTO.getOneHundredPercentDate().isAfter(LocalDate.now())){
            throw new BadRequestException("The One Hundred Percent Date cannot be in the future.");
        }
    }

    private void verifyIfNameExists(String gameName) {
        Game game = repository.findByName(gameName);
        if(Objects.nonNull(game)){
            throw new BadRequestException("There is already a Game with this name: " + gameName);
        }
    }

    public Game findById(Long gameId) {
        return repository.findById(gameId).orElseThrow(() -> new ObjectNotFoundException("Cannot find a Game with " +
                "id: " + gameId));
    }

    public void save(Game game) {
        repository.save(game);
    }

    public void rateGame(GameRatingCreateRequestDTO gameRatingCreateRequestDTO, Long gameId) {
        Game game = findById(gameId);
        GameRating gameRating = mapper.map(gameRatingCreateRequestDTO, GameRating.class);

        BigDecimal totalRating = calculateAverage(gameRating);

        game.setGameRating(gameRating);
        game.setTotalRating(totalRating);
        save(game);
    }

    public Page<GamePagedResponseDTO> getAllPaged(String gameName, Integer pageNumber, Integer pageSize, String orderBy) {

        String sortBy = "TOTAL_RATING";
        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<Game> gamePaged = repository.findAllPaged(paging, gameName);
        return gamePaged.map(g -> mapper.map(g, GamePagedResponseDTO.class));
    }
}
