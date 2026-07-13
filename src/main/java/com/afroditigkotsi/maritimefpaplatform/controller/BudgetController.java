package com.afroditigkotsi.maritimefpaplatform.controller;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.BudgetStatus;
import com.afroditigkotsi.maritimefpaplatform.service.BudgetService;
import com.afroditigkotsi.maritimefpaplatform.service.VesselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BudgetController {

    private final BudgetService budgetService;
    private final VesselService vesselService;

    public BudgetController(BudgetService budgetService,
                            VesselService vesselService) {
        this.budgetService = budgetService;
        this.vesselService = vesselService;
    }

    @GetMapping("/budgets")
    public String listBudgets(Model model) {

        model.addAttribute("budgets", budgetService.findAll());

        return "budgets";
    }

    @GetMapping("/budgets/new")
    public String showCreateForm(Model model) {

        model.addAttribute("budget", new Budget());

        model.addAttribute("vessels", vesselService.findAll());

        model.addAttribute("statuses", BudgetStatus.values());

        return "budget-form";
    }


    @PostMapping("/budgets/save")
    public String saveBudget(@ModelAttribute Budget budget,
                             @RequestParam Long vesselId,
                             RedirectAttributes redirectAttributes) {

        Vessel vessel = vesselService.findById(vesselId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vessel id"));

        budget.setVessel(vessel);

        boolean budgetExists;

        if (budget.getId() == null) {

            budgetExists = budgetService.existsByVesselAndBudgetYear(
                    budget.getVessel(),
                    budget.getBudgetYear());

        } else {

            budgetExists = budgetService.existsByVesselAndBudgetYearAndIdNot(
                    budget.getVessel(),
                    budget.getBudgetYear(),
                    budget.getId());
        }

        if (budgetExists) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "A budget already exists for this vessel and year.");

            return budget.getId() == null
                    ? "redirect:/budgets/new"
                    : "redirect:/budgets/edit/" + budget.getId();
        }

        budgetService.save(budget);

        redirectAttributes.addFlashAttribute(
                "success",
                "Budget created successfully.");

        return "redirect:/budgets";
    }



    @GetMapping("/budgets/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Budget budget = budgetService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget id"));

        model.addAttribute("budget", budget);
        model.addAttribute("vessels", vesselService.findAll());
        model.addAttribute("statuses", BudgetStatus.values());

        return "budget-form";
    }

    @GetMapping("/budgets/delete/{id}")
    public String deleteBudget(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {

        budgetService.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Budget deleted successfully.");

        return "redirect:/budgets";
    }
}
