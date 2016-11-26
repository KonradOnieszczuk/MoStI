package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.notifier.Notifier;

public interface Comparator {

    /**
     * Sets a {@link Notifier} instance on that Comparator so that whenever
     * there is a change detection, a notification is being sent to the user.
     *
     * @param notifier a {@link Notifier} instance
     */
    public void setNotifier(Notifier notifier);

    /**
     * Given an id of an {@link pl.edu.agh.to.mosti.model.ObservedPageSection}
     * and a new content of that section, checks whether there was a change in
     * that section's content.
     *
     * @param observedPageSectionId id of a page section that's being observed
     * @param currentContent current content of that section
     *
     * @see pl.edu.agh.to.mosti.model.ObservedPageSection
     */
    public void checkSnapshot(long observedPageSectionId, String currentContent);

}
