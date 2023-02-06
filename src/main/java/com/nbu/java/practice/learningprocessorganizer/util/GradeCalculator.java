package com.nbu.java.practice.learningprocessorganizer.util;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Answer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Question;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Result;
import com.nbu.java.practice.learningprocessorganizer.exceptions.InvalidAttemptException;

import java.util.List;

public final class GradeCalculator {

    private static final int MAX_POINTS = 200;
    private static final int MAX_PERCENTAGE = 100;

    private GradeCalculator() {
    }

    public static double calculateGrade(Attempt attempt) {
        if (attempt == null) {
            throw new InvalidAttemptException();
        }
        final var exam = attempt.getExam();
        if (exam == null) {
            throw new InvalidAttemptException();
        }
        final var questions = exam.getQuestions();
        if (questions == null || questions.isEmpty()) {
            throw new InvalidAttemptException();
        }
        int correctlyAnsweredQuestions = getCountOfCorrectlyAnsweredQuestions(questions, attempt.getResults());
        final var percentage = calculatePercentage(correctlyAnsweredQuestions, attempt.getExam().getQuestions().size());
        return calculateGrade(percentage);
    }

    private static double calculateGrade(double percentage) {
        if (percentage < 50) {
            return 2.0;
        }
        if (percentage < 55) {
            return 2.50;
        }
        if (percentage < 60) {
            return 3.0;
        }
        if (percentage < 65) {
            return 3.5;
        }
        if (percentage < 70) {
            return 4.0;
        }
        if (percentage < 75) {
            return 4.5;
        }
        if (percentage < 80) {
            return 5;
        }
        if (percentage < 85) {
            return 5.50;
        }
        return 6.0;
    }

    private static double calculatePercentage(int correctlyAnsweredQuestions, int allQuestions) {
        final double pointsPerQuestion = MAX_POINTS / allQuestions;
        final double collectedPoints = correctlyAnsweredQuestions * pointsPerQuestion;
        return collectedPoints / MAX_POINTS * MAX_PERCENTAGE;
    }

    private static int getCountOfCorrectlyAnsweredQuestions(List<Question> questions, List<Result> results) {
        int correctlyAnsweredQuestions = 0;
        for (Question question : questions) {
            final var questionId = question.getId();
            final var correctAnswerOpt = question.getAnswers().stream()
                    .filter(answer -> answer.getIsCorrect() != null && answer.getIsCorrect())
                    .map(Answer::getValue)
                    .findAny();
            if (correctAnswerOpt.isEmpty()) {
                throw new InvalidAttemptException();
            }
            final var correctAnswer = correctAnswerOpt.get();
            if (correctAnswer.equalsIgnoreCase(getGivenAnswerToQuestion(questionId, results))) {
                correctlyAnsweredQuestions++;
            }
        }
        return correctlyAnsweredQuestions;
    }

    private static String getGivenAnswerToQuestion(long questionId, List<Result> results) {
        final var resultOpt = results.stream()
                .filter(result -> result.getQuestion().getId() == questionId)
                .findAny();
        if (resultOpt.isEmpty()) {
            return null;
        }
        return resultOpt.get().getGivenAnswer();
    }
}
