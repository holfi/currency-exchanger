package com.rowen.currency.Repo;

import com.rowen.currency.Model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {

    Currency findFirstByOrderByDateDesc();

    Currency findFirstByCharCodeOrderByDateDesc(String charCode);

    List<Currency> findAllByDate(LocalDate date);


}
