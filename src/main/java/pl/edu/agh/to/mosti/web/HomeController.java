package pl.edu.agh.to.mosti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.notifier.INotifier;
import pl.edu.agh.to.mosti.notifier.Notifier;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    private final SectionService sectionService;

    private Section createEmptySection() {
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification());

        Section section = new Section();
        section.setNotifications(notifications);
        return section;
    }

    @Autowired
    public HomeController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("section", createEmptySection());
        model.addAttribute("sections", sectionService.getAllSections());
        return "home";
    }

    @RequestMapping("/section-mng")
    public String addSection(@ModelAttribute Section section) {
        sectionService.saveOrUpdateSection(section);
        return "redirect:/";
    }

    @RequestMapping(value = "/section-mng", params = {"addNotification"})
    public String addNotification(@ModelAttribute Section section, ModelMap model) {
        if (section.getNotifications() == null) {
            section.setNotifications(new LinkedList<>());
        }
        section.getNotifications().add(new Notification());
        model.addAttribute("sections", sectionService.getAllSections());
        return "home";
    }

    @RequestMapping(value = "/section-mng", params = {"removeNotification"})
    public String removeNotification(@ModelAttribute Section section,
                                     HttpServletRequest request, ModelMap model) {
        int idx = Integer.valueOf(request.getParameter("removeNotification"));
        section.getNotifications().remove(idx);
        model.addAttribute("sections", sectionService.getAllSections());
        return "home";
    }

    @RequestMapping(value = "/delete-section/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSection(@PathVariable("id") long id) {
        sectionService.deleteSection(sectionService.getSectionById(id));
    }

    // METHOD FOR DEMO PURPOSES

    @Autowired
    Comparator comparator;

    @RequestMapping(value = "/trigger-change")
    @ResponseStatus(HttpStatus.OK)
    public void triggerChange(@RequestParam(name = "id") Long id, @RequestParam(name = "newContent") String newContent) {

        comparator.compare(id, newContent);
    }
}
