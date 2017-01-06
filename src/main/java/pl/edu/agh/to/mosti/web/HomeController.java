package pl.edu.agh.to.mosti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

@Controller
public class HomeController {

    private final SectionService sectionService;

    @Autowired
    public HomeController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("section", new Section());
        model.addAttribute("sections", sectionService.getAllSections());
        return "home";
    }

    @PostMapping("/add-section")
    public String addSection(@ModelAttribute Section section, Model model) {
        sectionService.saveOrUpdateSection(section);
        return "redirect:/";
    }
}
