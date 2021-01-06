package fr.homnomnom;

import java.io.File;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class Benchmark {

    /**
     * @brief Parses a HTML file and logs time
     *
     * @param html: HTML to parse
     * @param parserType: type of parser to use (actual parser will be built)
     *
     * @return time of the run
     */
    private static Duration performSingleRun(String html, ParserBenchmarkFactory.Parser parserType) {
        var parser = ParserBenchmarkFactory.build(parserType);

        var t1 = Instant.now();
        //TODO: launch run
        var t2 = Instant.now();

        return Duration.between(t1, t2);
    }

    /**
     * @brief Performs all the runs on a given HTML file.
     * Each parser is run multiple times.
     *
     * @param html: html code of the page
     * @param nbIndividualRuns: how many repetitions for each parser?
     *
     * @return result of the runs (CSV-formatted)
     */
    private static String performRunsOnFile(String html, int nbIndividualRuns) {
        String result = "";
        for(var parserType : ParserBenchmarkFactory.Parser.values()) {
            for(int i = 0; i < nbIndividualRuns; ++i) {
                Duration time = performSingleRun(html, parserType);

                //TODO: create CSV
            }
        }

        return result;
    }

    private static String loadFile(File file) throws java.io.FileNotFoundException{
        var scanner = new Scanner(file);

        var buffer = new StringBuffer();
        while(scanner.hasNextLine()) {
            buffer.append(scanner.nextLine());
        }

        return buffer.toString();
    }

    /**
     * @brief main function, runs all the tests
     */
    public static void main(String[] args) {
        var dir = new File("./pages");
        if(!dir.exists() || !dir.isDirectory()) {
            System.err.println("bad directory: " + dir);
            System.exit(-1);
        }

        File[] files = dir.listFiles();

        for(File file : files) {
            if(!file.isFile()) continue;
            System.out.println("Benchmark on file \"" + file.getName() + "\"");

            try {
                String html = loadFile(file);
                String result = performRunsOnFile(html, 10);
            } catch(java.io.FileNotFoundException e) {
                System.err.println(e);
                System.exit(-1);
            }
        }
    }
}
