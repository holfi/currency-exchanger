package com.rowen.currency.Repo;

import com.rowen.currency.Model.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;

public interface HistoryRepo extends PagingAndSortingRepository<Converter, Long> {

    Page<Converter> findAllByUsername(String username, Pageable pageable);

    Page<Converter> findAllByDateHistoryAndFromCurAndToCurAndUsername(LocalDate date, String from, String to, String username, Pageable pageable);

    Page<Converter> findAllByFromCurAndToCurAndUsername(String from, String to, String username, Pageable pageable);

    Page<Converter> findAllByDateHistoryAndUsername(LocalDate date, String username, Pageable pageable);

}
