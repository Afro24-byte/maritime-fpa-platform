package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.service.KpiAnalyticsService;
import com.afroditigkotsi.maritimefpaplatform.service.KpiDashboardResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KpiDashboardController {

    private final KpiAnalyticsService kpiAnalyticsService;

    public KpiDashboardController(KpiAnalyticsService kpiAnalyticsService) {
        this.kpiAnalyticsService = kpiAnalyticsService;
    }

    @GetMapping("/kpi/dashboard")
    public String dashboard(
            @RequestParam(defaultValue = "2025") Integer year,
            Model model) {

        KpiDashboardResult dashboard =
                kpiAnalyticsService.buildDashboard(year);

        model.addAttribute("dashboard", dashboard);

        return "kpi-dashboard";
    }
}