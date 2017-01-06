package pl.edu.agh.to.mosti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/delete-section/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSection(@PathVariable("id") long id) {
        sectionService.deleteSection(sectionService.getSectionById(id));
    }
}
