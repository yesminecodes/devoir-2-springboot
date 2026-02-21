package com.yesmine.games.controllers;

import com.yesmine.games.model.Game;
import com.yesmine.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @RequestMapping("/ListeGames")
    public String listeGames(ModelMap modelMap,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="5") int size) {
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createGame";
    }

    @RequestMapping("/saveGame")
    public String saveGame(@ModelAttribute("Game") Game game,
                           @RequestParam("date") String date,
                           ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        game.setDateCreation(dateformat.parse(date));
        Game saved = gameService.saveGame(game);
        modelMap.addAttribute("msg", "Game enregistré avec Id " + saved.getIdGame());
        return "createGame";
    }

    @RequestMapping("/supprimerGame")
    public String supprimerGame(@RequestParam("id") Long id,
                                @RequestParam(name="page", defaultValue="0") int page,
                                @RequestParam(name="size", defaultValue="2") int size,
                                ModelMap modelMap) {
        gameService.deleteGameById(id);
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }

    @RequestMapping("/modifierGame")
    public String editerGame(@RequestParam("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("Game", gameService.getGame(id));
        return "editerGame";
    }

    @RequestMapping("/updateGame")
    public String updateGame(@ModelAttribute("Game") Game game,
                             @RequestParam("date") String date,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="2") int size,
                             ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        game.setDateCreation(dateformat.parse(date));
        gameService.updateGame(game);
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }
}


