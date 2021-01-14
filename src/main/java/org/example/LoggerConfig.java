package org.example;

public class LoggerConfig {

    /**
     * the name of the logger
     */
    private String loggerName;

    /**
     * the log level
     *
     * @see org.springframework.boot.logging.LogLevel
     */
    private String level;

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}