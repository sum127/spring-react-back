package org.test.mallapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.test.mallapi.domain.QTodo;
import org.test.mallapi.domain.Todo;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Disabled
    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Todo todo = Todo.builder()
                    .title("Title..." + i)
                    .dueDate(LocalDate.of(2024, 12, 31))
                    .writer("user00")
                    .build();

            todoRepository.save(todo);
        }
    }

    @Disabled
    @Test
    public void testRead() {
        Long tno = 33L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    @Disabled
    @Test
    public void testModify() {
        Long tno = 33L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        todo.changeTitle("Modified Title");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2024, 12, 31));

        todoRepository.save(todo);
    }

    @Disabled
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        todoRepository.findAll(pageable).forEach((Todo todo) -> {
            log.info(todo);
        });

        Page<Todo> result = todoRepository.findAll(pageable);

         log.info("----- 총페이지 수 -----");
        log.info(result.getTotalPages());
        System.out.println("println 확인");

    }
    
    // title로 검색하는 페이징 처리
    @Disabled
    @Test
    public void testSearch1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findByTitleContaining("1", pageable);
        result.stream().forEach((Todo todo) -> {
            log.info(todo);
        });


    }


    //QTodo를 이용해서 title로 '11'이라는 글자가 있는 데이터 검색
    @Disabled
    @Test
    public void testSearch2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        
        
        //JPQLQueryFactory를 이용해서 검색 
        QTodo qTodo = QTodo.todo;
        
        java.util.List<Todo> list = queryFactory.selectFrom(qTodo).where(qTodo.title.contains("11")).fetch();

        log.info(list);
    }

}


