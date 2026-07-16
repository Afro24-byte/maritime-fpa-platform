package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.service.VarianceResult;
import com.afroditigkotsi.maritimefpaplatform.service.VarianceService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/variance")
public class VarianceController {

    private final VarianceService varianceService;
    private final VesselService vesselService;

    public VarianceController(
            VarianceService varianceService,
            VesselService vesselService) {

        this.varianceService = varianceService;
        this.vesselService = vesselService;
    }

    @GetMapping
    public String showVarianceForm(Model model) {

        model.addAttribute("vessels", vesselService.findAll());

        return "variance";
    }

    @PostMapping
    public String calculateVariance(
            @RequestParam Long vesselId,
            @RequestParam Integer year,
            Model model) {

        Vessel vessel = vesselService.findById(vesselId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Vessel ID"));

        try {

            VarianceResult result =
                    varianceService.calculateVariance(vessel, year);

            model.addAttribute("result", result);

        } catch (IllegalArgumentException ex) {

            model.addAttribute("error", ex.getMessage());

        }

        model.addAttribute("vessels", vesselService.findAll());

        return "variance";
    }

}