package org.example;

import org.example.exeption.QuestionAlreadyExistExeption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void slearUp() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addPositiveTest() {
        assertThat(questionService.getAll()).isEmpty();
        Question question = new Question("test","test");
        add(question  );

        Question question1 = new Question("test2","test2");
        questionService.add(question1);
        assertThat(questionService.getAll())
                .hasSize(2)
                .containsOnly(question,question1);
    }
    @Test
    public void addNegativeTest() {
        assertThat(questionService.getAll()).isEmpty();
        Question question = new Question("test","test2");
       add(question);
        assertThatExceptionOfType(QuestionAlreadyExistExeption.class)
                .isThrownBy(() -> questionService.add(question));

    }

    private void add(Question question) {
        int sizeBefore = questionService.getAll().size();
        questionService.add(question.getQuestion(),question.getAnswer());
        assertThat(questionService.getAll())
                .hasSize(sizeBefore +1)
                .containsOnly(question);
    }
    @Test
    public void removePositiveTest() {
        assertThat(questionService.getAll()).isEmpty();
        Question question = new Question("test","test");
        add(question  );

        questionService.remove(question);
        assertThat(questionService.getAll())
                .isEmpty();
    }

    @Test
    public void getRandomQuestionPositiveTest() {
        for (int i = 0; i < 5; i++) {
            add(new Question("a" +i,"b" + i));
        }
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }
    @Test
    private void  getRandomQuestionNegativeTest() {
        assertThat(questionService.getAll()).isEmpty();;
        assertThat(questionService.getRandomQuestion()).isNull();
    }
}
