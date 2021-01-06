package fr.homnomnom;

import java.io.File;
import java.util.*;
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
    private static RunResult performSingleRun(String html, ParserBenchmarkFactory.Parser parserType) {
        var parser = ParserBenchmarkFactory.build(parserType);

        var t1 = Instant.now();
        parser.parse(html);
        var t2 = Instant.now();

        return new RunResult(Duration.between(t1, t2));
    }

    /**
     * @brief Logs (on console) the result of a given run
     *
     * @param id: number of the run
     * @param nb: total number of runs
     * @param time: result time on run
     */
    private static void logRun(int id, int nb, Duration time) {
        String id_str = Integer.valueOf(id).toString();
        String nb_str = Integer.valueOf(nb).toString();

        var str = new StringBuffer();
        str.append(" - ");
        for(int i = id_str.length(); i < nb_str.length(); ++i)
            str.append(" ");
        str.append(id_str);
        str.append("/");
        str.append(nb_str);
        str.append(": ");
        str.append(time.toMillis());
        str.append("ms");

        System.out.println(str.toString());
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
    private static List<RunResult> performRunsOnFile(String html, int nbIndividualRuns) {
        var result = new LinkedList<RunResult>();
        for(var parserType : ParserBenchmarkFactory.Parser.values()) {
            System.out.println("-> " + parserType.toString());
            for(int i = 0; i < nbIndividualRuns; ++i) {
                RunResult run = performSingleRun(html, parserType);
                logRun(i+1, nbIndividualRuns, run.getDuration());
                result.add(run);
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

        var results = new LinkedList<RunResult>();
        for(File file : files) {
            if(!file.isFile()) continue;
            System.out.println("Benchmark on file \"" + file.getName() + "\"");

            try {
                String html = loadFile(file);
                results.addAll(performRunsOnFile(html, 10));
            } catch(java.io.FileNotFoundException e) {
                System.err.println(e);
                System.exit(-1);
            }
        }

        //TODO: write CSV
    }
}
