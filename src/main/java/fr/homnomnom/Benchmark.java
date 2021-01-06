package fr.homnomnom;

public class Benchmark {

    /**
     * @brief Parses a HTML file and logs time
     *
     * @param html: HTML to parse
     * @param parserType: type of parser to use (actual parser will be built)
     *
     * @return result of the run (CSV-formatted)
     */
    private static String performSingleRun(String html, ParserBenchmarkFactory.Parser parserType) {
        var parser = ParserBenchmarkFactory.build(parserType);

        //TODO: parse html
        //TODO: log time
        //TODO: create CSV result

        //FIXME
        return "";
    }

    /**
     * @brief Performs all the runs on a given URL.
     * Each parser is run multiple times.
     *
     * @param url: url to test
     * @param nbIndividualRuns: how many repetitions for each parser?
     *
     * @return result of the runs (CSV-formatted)
     */
    private static String performRunsOnURL(String url, int nbIndividualRuns) {
        //FIXME: get html from URL
        String html = "";

        String result = "";
        for(var parserType : ParserBenchmarkFactory.Parser.values()) {
            for(int i = 0; i < nbIndividualRuns; ++i) {
                String resultLine = performSingleRun(html, parserType);
                //TODO: add line to result
            }
        }

        return result;
    }

    /**
     * @brief main function, runs all the tests
     */
    public static void main(String[] args) {
        //TODO
    }
}
