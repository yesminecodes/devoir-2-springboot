package com.yesmine.games.service;

import com.yesmine.games.model.Game;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GameService {

    Game saveGame(Game g);
    Game updateGame(Game g);
    void deleteGame(Game g);
    void deleteGameById(Long id);
    Game getGame(Long id);
    List<Game> getAllGames();

    Page<Game> getAllGamesParPage(int page, int size);


}
