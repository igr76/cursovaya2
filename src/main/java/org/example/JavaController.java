package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class JavaController {
    private final  QuestionService questionService;

    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/exam/java/add")
    public Question add(@RequestParam String QuestionText, @RequestParam String QuestionAnswer) {
        return questionService.add(QuestionText,QuestionAnswer);
    }
    @GetMapping("/exam/java/remove")
    public Question remove(@RequestParam String QuestionText, @RequestParam String QuestionAnswer) {
        return questionService.remove(new Question(QuestionText,QuestionAnswer));
    }
    @GetMapping("/exam/java")
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
