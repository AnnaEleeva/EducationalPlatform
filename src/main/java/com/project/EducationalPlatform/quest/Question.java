package com.project.EducationalPlatform.quest;

public class Question {
    private String question;
    private String[] answers;
    private String key;

    public Question(String question, String[] answers, String key) {
        this.question = question;
        this.answers = answers;
        this.key=key;
    }

    /*public String correctAnswer(){
        return this.answers[answers.length-1];
    }*/

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
