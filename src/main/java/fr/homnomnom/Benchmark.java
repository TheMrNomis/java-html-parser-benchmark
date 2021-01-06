package fr.homnomnom;

import java.io.File;
import java.util.Scanner;

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
                String resultLine = performSingleRun(html, parserType);
                //TODO: add line to result
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
