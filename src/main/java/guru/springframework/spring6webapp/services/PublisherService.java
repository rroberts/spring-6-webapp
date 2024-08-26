package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Publisher;

public interface PublisherService {
    Iterable<Publisher> findAll();
}
