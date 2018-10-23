package com.fadi.simplecrud2.repository;

import com.fadi.simplecrud2.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
