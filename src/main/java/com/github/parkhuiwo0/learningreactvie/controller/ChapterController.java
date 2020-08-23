package com.github.parkhuiwo0.learningreactvie.controller;

import com.github.parkhuiwo0.learningreactvie.document.Chapter;
import com.github.parkhuiwo0.learningreactvie.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created by Huiwoo Park
 * User: common
 * Date: 2020/08/23
 * Time: 3:39 오후
 */
@RestController
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterRepository repository;

    @GetMapping("/chapters")
    public Flux<Chapter> listing() {
        return repository.findAll();
    }
}
