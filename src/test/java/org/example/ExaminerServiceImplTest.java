package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private static  final List<Question> questions3 = List.of(
            new Question("a1","a11"),
            new Question("a2","a22"),
            new Question("a3","a23"),
            new Question("a4","a24"),
            new Question("a5","a25")
    );

   @ParameterizedTest
   @MethodSource("getQuestionNegative")
    public void getQuestionNegative(int incorrectAmount) {
       assertThatExceptionOfType(IncorrectAmountOfQuestion.class)
               .isThrownBy(() -> examinerService.getQuestions(incorrectAmount));

   }

    @Test
    public void getQuestionPositive() {
       when(questionService.getRandomQuestion()).thenReturn(
               questions3.get(1),
               questions3.get(2),
               questions3.get(5),
               questions3.get(1)
       );
       assertThat(examinerService.getQuestions(4)).containsExactly(
               questions3.get(1),
               questions3.get(2),
               questions3.get(5)
       );
    }

   public  static Stream<Arguments> getQuestionNegativeParams() {
       return  Stream.of(
               Arguments.of(-1),
               Arguments.of(questions3.size()-1),
               Arguments.of(questions3.size() + 50)

       );
   }
}
