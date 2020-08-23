package com.github.parkhuiwo0.learningreactvie.repository;

import com.github.parkhuiwo0.learningreactvie.document.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by Huiwoo Park
 *
 */
public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
}
