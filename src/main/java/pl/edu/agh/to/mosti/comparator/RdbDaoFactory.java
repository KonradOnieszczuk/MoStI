package pl.edu.agh.to.mosti.comparator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RdbDaoFactory extends DaoFactory {

    @Bean
    @Override
    protected SectionDao getSectionDao() {
        return new RdbSectionDao();
    }

    @Bean
    @Override
    protected SectionSnapshotDao getSectionSnapshotDao() {
        return new RdbSectionSnapshotDao();
    }
}
