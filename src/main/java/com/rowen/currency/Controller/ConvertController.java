package com.rowen.currency.Controller;

import com.rowen.currency.Model.Converter;
import com.rowen.currency.Repo.HistoryRepo;
import com.rowen.currency.Service.ConverterService;
import com.rowen.currency.Service.XmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class ConvertController {

    @Autowired
    ConverterService converterService;

    @Autowired
    HistoryRepo historyRepo;

    @Autowired
    XmlParserService xmlParser;

    @GetMapping
    public String main(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("currencies", xmlParser.getCurrencies());
        model.addAttribute("converter", new Converter());

        return "convert";
    }

    @PostMapping
    public String convert(@AuthenticationPrincipal UserDetails user, @ModelAttribute @Valid Converter converter, Model model) {
        converter.setUsername(user.getUsername());

        if (converter.getQuantity() == null)
            converter.setQuantity(0L);

        model.addAttribute("currencies", xmlParser.getCurrencies());

        converterService.convert(converter);

        return "convert";
    }

}
