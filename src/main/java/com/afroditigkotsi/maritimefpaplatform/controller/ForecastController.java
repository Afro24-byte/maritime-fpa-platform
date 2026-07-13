package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Forecast;
import com.afroditigkotsi.maritimefpaplatform.enums.ForecastStatus;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import com.afroditigkotsi.maritimefpaplatform.service.BudgetService;
import com.afroditigkotsi.maritimefpaplatform.service.ForecastService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forecasts")
public class ForecastController {

    private final ForecastService forecastService;
    private final VesselService vesselService;
    private final BudgetService budgetService;

    public ForecastController(
            ForecastService forecastService,
            VesselService vesselService,
            BudgetService budgetService) {

        this.forecastService = forecastService;
        this.vesselService = vesselService;
        this.budgetService = budgetService;
    }

    @GetMapping
    public String listForecasts(Model model) {

        model.addAttribute(
                "forecasts",
                forecastService.findAll()
        );

        return "forecasts";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("forecast", new Forecast());
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("budgets", budgetService.findAll());
        model.addAttribute("scenarioTypes", ScenarioType.values());
        model.addAttribute("statuses", ForecastStatus.values());

        return "forecast-form";
    }


}
