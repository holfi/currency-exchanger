package com.rowen.currency.Service;

import com.rowen.currency.Model.Converter;
import com.rowen.currency.Repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    @Autowired
    HistoryService historyService;

    @Autowired
    CurrencyRepo currencyRepo;

    public Double convert(Converter converter) {
        System.out.println(converter);

        Double fromCur = currencyRepo.findFirstByCharCodeOrderByDateDesc(converter.getFromCur()).getValue();
        Double toCur = currencyRepo.findFirstByCharCodeOrderByDateDesc(converter.getToCur()).getValue();

        Double result = fromCur * converter.getQuantity() / toCur;

        converter.setValue(result);

        historyService.saveToHistory(converter);

        return result;
    }

}
