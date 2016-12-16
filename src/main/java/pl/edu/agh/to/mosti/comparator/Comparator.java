package pl.edu.agh.to.mosti.comparator;

import com.sun.javaws.exceptions.InvalidArgumentException;
import pl.edu.agh.to.mosti.notifier.Notifier;

public interface Comparator {
    public void compare(long id, String content) throws IllegalArgumentException;
    public void setNotifier(Notifier notifier);
}
