package com.rowen.currency.Service;

import com.rowen.currency.Model.Converter;
import com.rowen.currency.Repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HistoryService {

    @Autowired
    HistoryRepo historyRepo;

    public void saveToHistory(Converter converter) {
        converter.setDateHistory(LocalDate.now());
        historyRepo.save(converter);
    }

}
