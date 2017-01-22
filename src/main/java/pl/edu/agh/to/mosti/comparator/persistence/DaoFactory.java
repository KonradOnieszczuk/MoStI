package pl.edu.agh.to.mosti.comparator.persistence;

abstract class DaoFactory {
    protected abstract SectionDao getSectionDao();
    protected abstract SectionSnapshotDao getSectionSnapshotDao();
    protected abstract NotificationDao getNotificationDao();
}