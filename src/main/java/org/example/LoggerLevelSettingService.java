package org.example;

import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态设置日志级别
 */
@Service
@Slf4j
public class LoggerLevelSettingService {

    private static final String ROOT_LOGGER_NAME = "";

    @Resource
    private LoggingSystem loggingSystem;

    public void setRootLoggerLevel(String level) {

        LoggerConfiguration loggerConfiguration = loggingSystem.getLoggerConfiguration(ROOT_LOGGER_NAME);

        if (loggerConfiguration == null) {
            if (log.isErrorEnabled()) {
                log.error("no loggerConfiguration with loggerName " + level);
            }
            return;
        }

        if (!supportLevels().contains(level)) {
            if (log.isErrorEnabled()) {
                log.error("current Level is not support : " + level);
            }
            return;
        }

        if (!loggerConfiguration.getEffectiveLevel().equals(LogLevel.valueOf(level))) {
            if (log.isInfoEnabled()) {
                log.info("setRootLoggerLevel success,old level is  '" + loggerConfiguration.getEffectiveLevel()
                    + "' , new level is '" + level + "'");
            }
            loggingSystem.setLogLevel(ROOT_LOGGER_NAME, LogLevel.valueOf(level));
        }
    }

    private List<String> supportLevels() {
        return loggingSystem.getSupportedLogLevels().stream().map(Enum::name).collect(Collectors.toList());
    }

    public void setLoggerLevel(List<LoggerConfig> configList) {

        Optional.ofNullable(configList).orElse(Collections.emptyList()).forEach(
                config -> {
                    LoggerConfiguration loggerConfiguration = loggingSystem.getLoggerConfiguration(config.getLoggerName());

                    if (loggerConfiguration == null) {
                        if (log.isErrorEnabled()) {
                            log.error("no loggerConfiguration with loggerName " + config.getLoggerName());
                        }
                        return;
                    }

                    if (!supportLevels().contains(config.getLevel())) {
                        if (log.isErrorEnabled()) {
                            log.error("current Level is not support : " + config.getLevel());
                        }
                        return;
                    }

                    if (log.isInfoEnabled()) {
                        log.info("setLoggerLevel success for logger '" + config.getLoggerName() + "' ,old level is  '"
                                + loggerConfiguration.getEffectiveLevel()
                                + "' , new level is '" + config.getLevel() + "'");
                    }
                    loggingSystem.setLogLevel(config.getLoggerName(), LogLevel.valueOf(config.getLevel()));
                }
        );
    }
}