package com.Licenta.QuizSurprise.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionDTO {
    private Integer id;
    private String question;
    private String subjectType;
    private List<AnswerDTO> answers;
}
