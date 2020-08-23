package com.github.parkhuiwo0.learningreactvie.configuration;

import com.github.parkhuiwo0.learningreactvie.document.Chapter;
import com.github.parkhuiwo0.learningreactvie.repository.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * Created by Huiwoo Park
 */
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(ChapterRepository repository) {
        return args -> {
            Flux.just(
                    new Chapter("Quick Start with Java"),
                    new Chapter("Reactive Web with Spring Boot"),
                    new Chapter("...and more !"))
                    .flatMap(repository::save)
                    .subscribe(System.out::println);
        };
    }
}
