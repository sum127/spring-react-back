package org.test.mallapi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.test.mallapi.domain.Todo;
import org.test.mallapi.dto.TodoDTO;
import org.test.mallapi.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional // 여러개의 작업 호출을 위한 어노테이션
@RequiredArgsConstructor // 의존성 주입을 위한 어노테이션
@Log4j2
public class TodoServiceImpl implements TodoService{        

        //TodoRepository
        private final TodoRepository todoRepository;
        private final ModelMapper modelmapper;
        

        @Override
        public Long register(TodoDTO todoDTO) {
            log.info(".............", todoDTO);
            Todo todo = modelmapper.map(todoDTO, Todo.class);
            Todo savedTodo = todoRepository.save(todo);
            return savedTodo.getTno();
        }


        @Override
        public TodoDTO get(Long tno) {
            Optional<Todo> result = todoRepository.findById(tno);
            Todo todo = result.orElseThrow(() -> new RuntimeException("Todo not found: " + tno));
            TodoDTO dto = modelmapper.map(todo, TodoDTO.class);

            return dto;
        }

}
