package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.notifier.INotifier;

public interface Comparator {
    void compare(long id, String content) throws NoSuchSectionException;
    void setNotifier(INotifier notifier);
}
