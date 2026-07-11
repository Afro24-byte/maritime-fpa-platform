package com.afroditigkotsi.maritimefpaplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalystController {

    @GetMapping("/analyst/dashboard")
    public String analystDashboard() {
        return "analyst-dashboard";
    }

}