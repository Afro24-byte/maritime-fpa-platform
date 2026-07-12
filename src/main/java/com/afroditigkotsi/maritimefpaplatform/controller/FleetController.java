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
}