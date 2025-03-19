package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Genre;

public interface IGenreService {

    List<Genre> getGenres();

    Optional<Genre> getGenreById(long id);

    void updateGenre(Genre genre);

    void addGenre(String name);

    void deleteGenre(long id);

    void saveGenre(Genre genre);
}