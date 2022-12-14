package main.java.org.art.samples.core.complitable_future.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class StackOverflowClientImpl implements StackOverflowClient {

    @Override
    public String mostRecentQuestionAbout(String tag) {
        return fetchTitleOnline(tag);
    }

    @Override
    public Document mostRecentQuestionsAbout(String tag) {
        try {
            return Jsoup.connect("http://stackoverflow.com/questions/tagged/" + tag).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String fetchTitleOnline(String tag) {
        return mostRecentQuestionsAbout(tag).select("a.question-hyperlink").get(0).text();
    }
}