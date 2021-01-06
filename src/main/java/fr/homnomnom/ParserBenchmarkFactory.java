package fr.homnomnom;

public class ParserBenchmarkFactory {
    public static enum Parser {
        Jsoup
    };

    public static AbstractParserBenchmark build(Parser type) {
        switch(type) {
            case Jsoup:
                return new JsoupBenchmark();
            default:
                throw new IllegalArgumentException("unknown type");
        }
    }
}
