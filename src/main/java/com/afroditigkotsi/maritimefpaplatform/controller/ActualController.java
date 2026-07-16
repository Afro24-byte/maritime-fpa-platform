package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.ActualStatus;
import com.afroditigkotsi.maritimefpaplatform.service.ActualService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/actuals")
public class ActualController {

    private final ActualService actualService;
    private final VesselService vesselService;

    public ActualController(
            ActualService actualService,
            VesselService vesselService) {

        this.actualService = actualService;
        this.vesselService = vesselService;
    }

    @GetMapping
    public String listActuals(Model model) {

        model.addAttribute("actuals", actualService.findAll());

        return "actuals";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("actual", new Actual());
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("statuses", ActualStatus.values());

        return "actual-form";
    }

    @PostMapping("/save")
    public String saveActual(
            @ModelAttribute Actual actual,
            @RequestParam Long vesselId,
            RedirectAttributes redirectAttributes) {

        Vessel vessel = vesselService.findById(vesselId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Vessel ID"));

        actual.setVessel(vessel);

        boolean actualExists;

        if (actual.getId() == null) {

            actualExists = actualService.existsByVesselAndActualYear(
                    actual.getVessel(),
                    actual.getActualYear());

        } else {

            actualExists = actualService.existsByVesselAndActualYearAndIdNot(
                    actual.getVessel(),
                    actual.getActualYear(),
                    actual.getId());
        }

        if (actualExists) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "An Actual already exists for this vessel and year.");

            return actual.getId() == null
                    ? "redirect:/actuals/new"
                    : "redirect:/actuals/edit/" + actual.getId();
        }

        actualService.save(actual);

        redirectAttributes.addFlashAttribute(
                "success",
                "Actual created successfully.");

        return "redirect:/actuals";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Actual actual = actualService.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Actual ID"));

        model.addAttribute("actual", actual);
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("statuses", ActualStatus.values());

        return "actual-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteActual(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        actualService.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Actual deleted successfully.");

        return "redirect:/actuals";
    }

}