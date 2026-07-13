package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Forecast;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.ForecastStatus;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import com.afroditigkotsi.maritimefpaplatform.service.BudgetService;
import com.afroditigkotsi.maritimefpaplatform.service.ForecastService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        model.addAttribute("forecasts", forecastService.findAll());

        return "forecasts";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("forecast", new Forecast());
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("budgets", budgetService.findAll());
        model.addAttribute("scenarioTypes", ScenarioType.values());
        model.addAttribute("statuses", ForecastStatus.values());

        model.addAttribute("formAction", "/forecasts/save");

        return "forecast-form";
    }

    @PostMapping("/save")
    public String saveForecast(
            @ModelAttribute Forecast forecast,
            @RequestParam Long vesselId,
            @RequestParam Long budgetId,
            RedirectAttributes redirectAttributes) {

        Vessel vessel = vesselService.findById(vesselId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Vessel ID"));

        Budget budget = budgetService.findById(budgetId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Budget ID"));

        forecast.setVessel(vessel);
        forecast.setBudget(budget);

        forecastService.save(forecast);

        redirectAttributes.addFlashAttribute(
                "success",
                "Forecast created successfully."
        );

        return "redirect:/forecasts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Forecast forecast = forecastService.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Forecast ID"));

        model.addAttribute("forecast", forecast);
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("budgets", budgetService.findAll());
        model.addAttribute("scenarioTypes", ScenarioType.values());
        model.addAttribute("statuses", ForecastStatus.values());

        model.addAttribute("formAction", "/forecasts/" + id + "/update");

        return "forecast-form";
    }

    @PostMapping("/{id}/update")
    public String updateForecast(
            @PathVariable Long id,
            @ModelAttribute Forecast forecast,
            @RequestParam Long vesselId,
            @RequestParam Long budgetId,
            RedirectAttributes redirectAttributes) {

        Vessel vessel = vesselService.findById(vesselId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Vessel ID"));

        Budget budget = budgetService.findById(budgetId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Budget ID"));

        forecast.setId(id);
        forecast.setVessel(vessel);
        forecast.setBudget(budget);

        if (forecastService.existsByBudgetAndScenarioTypeAndIdNot(
                budget,
                forecast.getScenarioType(),
                id)) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "A Forecast already exists for this Budget and Scenario."
            );

            return "redirect:/forecasts/" + id + "/edit";
        }

        forecastService.save(forecast);

        redirectAttributes.addFlashAttribute(
                "success",
                "Forecast updated successfully."
        );

        return "redirect:/forecasts";
    }

    @GetMapping("/{id}/delete")
    public String deleteForecast(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        forecastService.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Forecast deleted successfully."
        );

        return "redirect:/forecasts";
    }

}
