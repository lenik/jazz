package net.bodz.violet.edu;

public class TestQuestionSamples {

    public static TestQuestion build(Course course) {
        TestQuestion a = new TestQuestion();
        a.setLabel("testQuestion-1");
        a.setSubject("A testQuestion named testQuestion-1.");
        a.setCourse(course);
        a.setAnswer("answer-1");
        return a;
    }

}
