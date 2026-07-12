package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.service.FleetService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vessels")
public class VesselController {

    private final VesselService vesselService;
    private final FleetService fleetService;

    public VesselController(VesselService vesselService,
                            FleetService fleetService) {
        this.vesselService = vesselService;
        this.fleetService = fleetService;
    }

    @GetMapping
    public String listVessels(Model model) {

        model.addAttribute("vessels", vesselService.findAll());

        return "vessels";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("vessel", new Vessel());
        model.addAttribute("fleets", fleetService.findAll());

        return "vessel-form";
    }

    @PostMapping
    public String saveVessel(@ModelAttribute Vessel vessel) {

        vesselService.save(vessel);

        return "redirect:/vessels";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Vessel vessel = vesselService.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid vessel ID: " + id));

        model.addAttribute("vessel", vessel);
        model.addAttribute("fleets", fleetService.findAll());

        return "vessel-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteVessel(@PathVariable Long id) {

        vesselService.deleteById(id);

        return "redirect:/vessels";
    }
}
