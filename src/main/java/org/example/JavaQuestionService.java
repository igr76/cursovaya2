package org.example;

import org.example.exeption.QuestionAlreadyExistExeption;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();
    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistExeption();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundExeption();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {

        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return  null;
        }
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }

    private void checkQuestion(String question) {

    }
}
