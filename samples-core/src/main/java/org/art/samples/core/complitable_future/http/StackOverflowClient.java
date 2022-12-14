package main.java.org.art.samples.core.complitable_future.http;

import org.jsoup.nodes.Document;

public interface StackOverflowClient {
    String mostRecentQuestionAbout(String tag);
    Document mostRecentQuestionsAbout(String tag);
}
