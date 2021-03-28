package com.rowen.currency.Service;

import com.rowen.currency.Model.Currency;
import com.rowen.currency.Model.XmlValCurs;
import com.rowen.currency.Repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class XmlParserService {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${url}")
    String url;

    @Autowired
    CurrencyRepo currencyRepo;

    public List<Currency> getCurrencies() {
        XmlValCurs xmlValCurs = restTemplate.getForObject(url, XmlValCurs.class);

        if (currencyRepo.findFirstByOrderByDateDesc() == null
                || !xmlValCurs.getDate().equals(currencyRepo.findFirstByOrderByDateDesc().getDate()))
            update();

        return currencyRepo.findAllByDate(xmlValCurs.getDate());
    }

    public void update() {
        XmlValCurs xmlValCurs = restTemplate.getForObject(url, XmlValCurs.class);

        List<Currency> currencies = xmlValCurs.getCurrencies();
        currencies.add(createRub());

        for (Currency c : currencies) {
            c.setDate(xmlValCurs.getDate());
            currencyRepo.save(c);
        }
    }

    public Currency createRub() {
        Currency rub = new Currency();
        rub.setVal_id("R01111");
        rub.setName("Российских рублей");
        rub.setNumCode("001");
        rub.setCharCode("RUB");
        rub.setValue(1.0);

        return rub;
    }

}
