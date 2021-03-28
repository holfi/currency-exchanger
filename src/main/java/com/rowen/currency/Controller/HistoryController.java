package com.rowen.currency.Controller;

import com.rowen.currency.Model.Converter;
import com.rowen.currency.Repo.HistoryRepo;
import com.rowen.currency.Service.XmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryRepo historyRepo;

    @Autowired
    XmlParserService xmlParser;

    @GetMapping
    public String history(@AuthenticationPrincipal UserDetails user,
                          Model model,
                          @PageableDefault(sort = {"dateHistory"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("history", historyRepo.findAllByUsername(user.getUsername(), pageable));
        model.addAttribute("currencies", xmlParser.getCurrencies());

        return "history";
    }

    @GetMapping("/filter")
    public String filter(@AuthenticationPrincipal UserDetails user,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateHistory,
                         @RequestParam(required = false) String fromCur,
                         @RequestParam(required = false) String toCur,
                         Model model,
                         @PageableDefault(sort = {"dateHistory"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Converter> history;

        if (dateHistory == null && fromCur.equals("") && toCur.equals(""))
            history = historyRepo.findAllByUsername(user.getUsername(), pageable);
        else if (dateHistory != null && !fromCur.equals("") && !toCur.equals(""))
            history = historyRepo.findAllByDateHistoryAndFromCurAndToCurAndUsername(dateHistory, fromCur, toCur, user.getUsername(), pageable);
        else if (dateHistory == null && !fromCur.equals("") && !toCur.equals(""))
            history = historyRepo.findAllByFromCurAndToCurAndUsername(fromCur, toCur, user.getUsername(), pageable);
        else if (fromCur.equals("") || toCur.equals("") && dateHistory != null)
            history = historyRepo.findAllByDateHistoryAndUsername(dateHistory, user.getUsername(), pageable);
        else
            history = historyRepo.findAllByUsername(user.getUsername(), pageable);

        model.addAttribute("dateHistory", dateHistory);
        model.addAttribute("fromCur", fromCur);
        model.addAttribute("toCur", toCur);
        model.addAttribute("currencies", xmlParser.getCurrencies());
        model.addAttribute("history", history);
        model.addAttribute("totalPages", history.getTotalPages());

        return "history";
    }

}
