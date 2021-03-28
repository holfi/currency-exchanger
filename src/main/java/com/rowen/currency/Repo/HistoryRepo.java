package com.rowen.currency.Repo;

import com.rowen.currency.Model.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;

public interface HistoryRepo extends PagingAndSortingRepository<Converter, Long> {

//    List<Converter> findAllByUsername(String username);
//
//    List<Converter> findAllByDateHistoryAndFromCurAndToCurAndUsername(LocalDate date, String from, String to, String username);
//
//    List<Converter> findAllByFromCurAndToCurAndUsername(String from, String to, String username);
//
//    List<Converter> findAllByDateHistoryAndUsername(LocalDate date, String username);

    Page<Converter> findAllByUsername(String username, Pageable pageable);

    Page<Converter> findAllByDateHistoryAndFromCurAndToCurAndUsername(LocalDate date, String from, String to, String username, Pageable pageable);

    Page<Converter> findAllByFromCurAndToCurAndUsername(String from, String to, String username, Pageable pageable);

    Page<Converter> findAllByDateHistoryAndUsername(LocalDate date, String username, Pageable pageable);

}
