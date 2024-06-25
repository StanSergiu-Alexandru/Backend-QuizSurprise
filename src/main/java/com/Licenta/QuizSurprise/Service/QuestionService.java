package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.DTO.QuestionDTO;
import com.Licenta.QuizSurprise.Entity.Answer;
import com.Licenta.QuizSurprise.Entity.Question;
import com.Licenta.QuizSurprise.Repository.AnswerRepository;
import com.Licenta.QuizSurprise.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Question getRandomQuestionBySubjectType(String subjectType) {
        List<Question> subjectQuestions = questionRepository.findBySubjectType(subjectType);

        if (subjectQuestions.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return subjectQuestions.get(random.nextInt(subjectQuestions.size()));
    }

    public Question addQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setSubjectType(questionDTO.getSubjectType());

        List<Answer> answers = questionDTO.getAnswers().stream().map(answerDTO -> {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setIsCorrect(answerDTO.getIsCorrect());
            answer.setQuestion(question);
            return answer;
        }).collect(Collectors.toList());

        question.setAnswers(answers);
        return questionRepository.save(question);
    }
}
