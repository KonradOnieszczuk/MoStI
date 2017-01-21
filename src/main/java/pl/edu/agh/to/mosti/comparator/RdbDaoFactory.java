package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class RdbDaoFactory extends DaoFactory {

    @Autowired private RdbSectionDao rdbSectionDao;
    @Autowired private RdbSectionSnapshotDao rdbSectionSnapshotDao;
    @Autowired private RdbNotificationDao rdbNotificationDao;

    @Bean @Primary
    @Override
    protected SectionDao getSectionDao() {
        return rdbSectionDao;
    }

    @Bean @Primary
    @Override
    protected SectionSnapshotDao getSectionSnapshotDao() {
        return rdbSectionSnapshotDao;
    }

    @Bean @Primary
    @Override
    protected NotificationDao getNotificationDao() {
        return rdbNotificationDao;
    }
}