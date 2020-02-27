package ua.epam.petProjectSpring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.epam.petProjectSpring.model.Developer;
import ua.epam.petProjectSpring.repository.DeveloperRepository;

import java.util.List;

@Service
@Component
public class DeveloperServiceImpl implements DeveloperService {
    private static final Logger logger = LoggerFactory.getLogger(DeveloperService.class);
    private DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(@Qualifier("jdbcDeveloperRepositoryImpl") DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer create(Developer developer) {
        logger.debug("Developer service->Create");
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> read() {
        logger.debug("Developer service->Read");
        return developerRepository.getAll();
    }

    @Override
    public Developer readById(Long id) {
        logger.debug("Developer service->Read by id");
        return developerRepository.getById(id);
    }

    @Override
    public void update(Long id, Developer developer) {
        logger.debug("Developer service->Edit by id");
        developerRepository.update(id, developer);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Developer service->Delete");
        developerRepository.deleteById(id);
    }
}
