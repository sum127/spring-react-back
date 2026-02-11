package org.test.mallapi.service;

import org.test.mallapi.dto.TodoDTO;

// 결합도를 줄이기 위해서 lmpl(구현하는 곳) 따로 생성
public interface TodoService {
    Long register(TodoDTO todoDTO);

    TodoDTO get(Long tno);
}
