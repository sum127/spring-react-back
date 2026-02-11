package org.test.mallapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.test.mallapi.domain.Todo;

// <>안에는 클래스 엔티티와 pk타입
public interface TodoRepository extends JpaRepository<Todo, Long>{
    // title검색 하는 페이징
    Page<Todo> findByTitleContaining(String title, Pageable pageable);
}
