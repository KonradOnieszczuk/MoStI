package pl.edu.agh.to.mosti.notifier;

import pl.edu.agh.to.mosti.comparator.model.Section;

public interface INotifier {
    void notify(Section section, String currentContent, String previousContent) throws InvalidNotificationType;
}
