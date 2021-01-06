package fr.homnomnom;

public class ParserBenchmarkFactory {
    public static enum Parser {
        Regex,
        Jsoup
    };

    public static AbstractParserBenchmark build(Parser type) {
        switch(type) {
            case Regex:
                return new RegexBenchmark();
            case Jsoup:
                return new JsoupBenchmark();
            default:
                throw new IllegalArgumentException("unknown type");
        }
    }
}
