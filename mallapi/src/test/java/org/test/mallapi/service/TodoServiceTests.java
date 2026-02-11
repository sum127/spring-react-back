package org.test.mallapi.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.mallapi.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Disabled
    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .complete(false)
                .dueDate(LocalDate.of(2026, 12, 31))
                .build();

        Long tno = todoService.register(todoDTO);

        log.info(tno);
    }

    @Test
    public void testRead() {
        Long tno = 261L;
        TodoDTO todoDTO = todoService.get(tno);

        log.info("DTO 입니다 : " + todoDTO);

    }
}
