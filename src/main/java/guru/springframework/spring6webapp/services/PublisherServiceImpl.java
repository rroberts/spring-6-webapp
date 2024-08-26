package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Iterable<Publisher> findAll() {
        return publisherRepository.findAll();
    }
}
