package fr.homnomnom;

import java.util.List;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class AbstractParserBenchmark {
    public abstract List<Post> parse(String html);

    public final int UNKNOWN_PAGES_COUNT = -1;

    public static Date fromHTMLDate(String year, String month, String day, String hours, String minutes, String seconds) {
        return new GregorianCalendar(
                Integer.parseInt(year),
                Integer.parseInt(month) - 1,
                Integer.parseInt(day),
                Integer.parseInt(hours),
                Integer.parseInt(minutes),
                Integer.parseInt(seconds)
        ).getTime();
    }

    public static Date fromHTMLDate(String year, String month, String day, String hours, String minutes) {
        return fromHTMLDate(year, month, day, hours, minutes, "0");
    }
}
