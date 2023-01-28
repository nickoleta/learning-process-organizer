package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Answer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Exam;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Question;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.AnswersRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ExamsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.QuestionRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.QuestionDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.ExamsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamsServiceImpl implements ExamsService {

    private final ExamsRepository examsRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(long activityId, ExamDTO examDTO) {
        examsRepository.save(modelMapper.map(examDTO, Exam.class));
    }

    @Override
    public void addQuestionToExam(long examId, QuestionDTO questionDTO) {
        final var exam = examsRepository.findById(examId);
        if(exam.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.EXAM_DOES_NOT_EXIST, examId);
        }
        final var question = new Question();
        question.setQuestionType(questionDTO.getQuestionType());
        question.setQuestion(questionDTO.getQuestion());
        question.setExam(exam.get());
        final var questionEntity = questionRepository.save(question);

        final var answers = questionDTO.getAnswers().stream()
                .map(answer -> {
                    final var answerEntity = new Answer();
                    answerEntity.setValue(answer.getValue());
                    answerEntity.setIsCorrect(answer.isCorrect());
                    answerEntity.setQuestion(questionEntity);
                    return answerEntity;
                })
                .collect(Collectors.toList());
        answersRepository.saveAll(answers);
    }

    @Override
    public void publishExam(long examId) {
        final var examOpt = examsRepository.findById(examId);
        if(examOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.EXAM_DOES_NOT_EXIST, examId);
        }
        final var exam = examOpt.get();
        exam.setPublished(true);
        examsRepository.save(exam);
    }
}
