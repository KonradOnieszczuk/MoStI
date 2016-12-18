package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.notifier.Notifier;

public interface Comparator {
    void compare(long id, String content);
    void setNotifier(Notifier notifier);
}
