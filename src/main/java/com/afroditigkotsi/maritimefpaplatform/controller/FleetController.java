package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.service.FleetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.afroditigkotsi.maritimefpaplatform.entity.Fleet;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/fleets")
public class FleetController {

    private final FleetService fleetService;

    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    @GetMapping
    public String listFleets(Model model) {

        model.addAttribute("fleets", fleetService.findAll());

        return "fleets";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("fleet", new Fleet());

        return "fleet-form";
    }

    @PostMapping
    public RedirectView saveFleet(@ModelAttribute Fleet fleet) {

        fleetService.save(fleet);

        return new RedirectView("/fleets");
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Fleet fleet = fleetService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid fleet ID: " + id
                ));

        model.addAttribute("fleet", fleet);

        return "fleet-form";
    }

    @GetMapping("/{id}/delete")
    public RedirectView deleteFleet(@PathVariable Long id) {

        fleetService.deleteById(id);

        return new RedirectView("/fleets");
    }
}