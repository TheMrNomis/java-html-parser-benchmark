package fr.homnomnom;

import org.jsoup.Jsoup;

public class JsoupBenchmark extends AbstractParserBenchmark {

    public void parse(String html) {
        var doc = Jsoup.parse(html);
    }
}
