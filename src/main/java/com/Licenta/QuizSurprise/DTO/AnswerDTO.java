package com.Licenta.QuizSurprise.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDTO {
    private Integer id;
    private String answer;
    private Boolean isCorrect;
}
