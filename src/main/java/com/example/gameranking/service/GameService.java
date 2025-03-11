package com.example.gameranking.service;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.exception.customized.ObjectNotFoundException;
import com.example.gameranking.model.Game;
import com.example.gameranking.model.GameRating;
import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.model.dto.input.GameRatingCreateRequestDTO;
import com.example.gameranking.model.dto.input.GameUpdateRequestDTO;
import com.example.gameranking.model.dto.output.GameResponseDTO;
import com.example.gameranking.model.dto.output.UnratedGamesResponseDTO;
import com.example.gameranking.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.gameranking.utils.GameRatingUtils.calculateAverage;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final ModelMapper mapper;

    @Value("${file.upload-dir}")
    private String imagesDirectory;

    @Value("${file.allowed-types}")
    private String allowedTypes;

    @Value("${image.base.url}")
    private String imageBaseUrl;

    public void create(GameCreateRequestDTO gameCreateRequestDTO) throws IOException {
        verifyIfNameExists(gameCreateRequestDTO.getName());

        validateDates(gameCreateRequestDTO);

        Game game = mapper.map(gameCreateRequestDTO, Game.class);
        uploadImage(game, gameCreateRequestDTO.getImage());
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

    public void saveAll(List<Game> games) {
        repository.saveAll(games);
    }

    @Transactional
    public void rateGame(GameRatingCreateRequestDTO gameRatingCreateRequestDTO, Long gameId) {
        Game game = findById(gameId);
        GameRating gameRating = mapper.map(gameRatingCreateRequestDTO, GameRating.class);

        BigDecimal totalRating = calculateAverage(gameRating);

        game.setGameRating(gameRating);
        game.setTotalRating(totalRating);
        save(game);

        setGamePosition();
    }

    private void setGamePosition() {
        List<Game> games = repository.findAllGamesTotalRating()
                .stream()
                .sorted(Comparator.comparing(Game::getTotalRating,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());

        IntStream.range(0, games.size())
                .forEach(i -> games.get(i).setRanking(i + 1));

        saveAll(games);
    }

    public Page<GameResponseDTO> getAllPaged(String gameName, Integer pageNumber, Integer pageSize, String orderBy) {

        String sortBy = "RANKING";
        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<Game> gamePaged = repository.findAllPaged(paging, gameName);
        return gamePaged.map(g -> mapper.map(g, GameResponseDTO.class));
    }

    public void uploadImage(Game game, MultipartFile file) throws IOException {
        verifyImageExtension(file);

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(imagesDirectory, fileName);

        Files.createDirectories(filePath.getParent());

        Files.write(filePath, file.getBytes());

        String imageUrl = imageBaseUrl + fileName;
        game.setImageUrl(imageUrl);
    }

    private void verifyImageExtension(MultipartFile file) {
        String contentType = file.getContentType();
        List<String> allowedMimeTypes = Arrays.asList(allowedTypes.split(","));

        List<String> allowedExtensions = allowedMimeTypes.stream()
                .map(mimeType -> mimeType.substring(mimeType.indexOf("/") + 1))
                .map(ext -> "." + ext)
                .toList();

        if (!allowedMimeTypes.contains(contentType)) {
            throw new BadRequestException("Invalid file type. Allowed types are: " + String.join(", ",
                    allowedExtensions));
        }
    }

    public Page<UnratedGamesResponseDTO> getUnratedGames(Integer pageNumber, Integer pageSize, String orderBy,
                                                         String sortBy, String gameName) {

        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<Game> unratedGames = repository.getUnratedGames(paging, gameName);
        return unratedGames.map(g -> mapper.map(g, UnratedGamesResponseDTO.class));
    }

    public void updateGame(Long id, GameUpdateRequestDTO gameUpdateRequestDTO) throws IOException {
        Game game = findById(id);
        mapper.map(gameUpdateRequestDTO, game);

        if(Objects.nonNull(gameUpdateRequestDTO.getImage())) {
            uploadImage(game, gameUpdateRequestDTO.getImage());
        }

        repository.save(game);
    }

    public GameResponseDTO getGameById(Long id) {
        Game game = findById(id);
        return mapper.map(game, GameResponseDTO.class);
    }

    public Page<GameResponseDTO> getAllGames(Integer pageNumber, Integer pageSize, String orderBy, String sortBy,
                                             String gameName) {

        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<Game> gamePage = repository.getAllGames(paging, gameName);
        return gamePage.map(g -> mapper.map(g, GameResponseDTO.class));
    }
}