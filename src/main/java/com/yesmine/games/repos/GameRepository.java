package com.yesmine.games.repos;

import com.yesmine.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Long> {
}
