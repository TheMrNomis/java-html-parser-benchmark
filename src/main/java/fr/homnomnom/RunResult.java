package fr.homnomnom;

import java.time.Duration;

public class RunResult {
    private Duration m_duration;
    private String m_filename;
    private String m_parserName;

    public RunResult(Duration duration) {
        m_duration = duration;
        m_filename = "";
        m_parserName = "";
    }

    public void setDuration(Duration duration) {
        m_duration = duration;
    }

    public Duration getDuration() {
        return m_duration;
    }

    public void setFilename(String filename) {
        m_filename = filename;
    }

    public String getFilename() {
        return m_filename;
    }

    public void setParserName(String parserName) {
        m_parserName = parserName;
    }

    public String getParserName() {
        return m_parserName;
    }

    public String csvHeader() {
        var csv = new CSVBuilder();
        csv.string("filename");
        csv.string("parser");
        csv.string("time");
        return csv.toString();
    }

    public String toCSV() {
        var csv = new CSVBuilder();
        csv.string(m_filename);
        csv.string(m_parserName);
        csv.number(m_duration.toMillis());
        return csv.toString();
    }
}
