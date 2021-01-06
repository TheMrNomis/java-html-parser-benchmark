package fr.homnomnom;

public class CSVBuilder {
    private StringBuilder m_str;

    public CSVBuilder() {
        m_str = new StringBuilder();
    }

    public String toString() {
        return m_str.toString();
    }

    public CSVBuilder string(String str) {
        this.field("\"" + str + "\"");
        return this;
    }

    public CSVBuilder number(int x) {
        this.field(Integer.valueOf(x).toString());
        return this;
    }

    public CSVBuilder number(double x) {
        this.field(Double.valueOf(x).toString());
        return this;
    }

    private void field(String str) {
        if(m_str.length() > 0) {
            m_str.append(", ");
        }

        m_str.append(str);
    }
}
