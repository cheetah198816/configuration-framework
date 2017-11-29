package service.impl;

import cache.CacheConfigurationLoader;
import configuration.Config;
import service.ConfigurationService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static constants.StringConstants.*;
import static constants.StringConstants.STRING;

/**
 * Created by chetan on 29.11.2017.
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    public <T> T getProperty(String key, T defaultValue, Class<T> tClass) {
        switch (tClass.getName()) {
            case BOOLEAN:
                return (T) getPropertyAsBoolean(key, (Boolean) defaultValue);
            case INTEGER:
                return (T) getPropertyAsInteger(key, (Integer) defaultValue);
            case FLOAT:
                return (T) getPropertyAsFloat(key, (Float) defaultValue);
            case DOUBLE:
                return (T) getPropertyAsDouble(key, (Double) defaultValue);
            case STRING:
                return (T) getPropertyAsString(key, (String) defaultValue);
        }
        return defaultValue;
    }

    private String getPropertyAsString(String key, String defaultValue) {
        final String value = getPropertyValue(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    private Double getPropertyAsDouble(String key, Double defaultValue) {
        final String value = getPropertyValue(key);
        if (value != null) {
            return Double.parseDouble(value);
        }
        return defaultValue;
    }

    private Float getPropertyAsFloat(String key, Float defaultValue) {
        final String value = getPropertyValue(key);
        if (value != null) {
            return Float.parseFloat(value);
        }
        return defaultValue;
    }

    private Integer getPropertyAsInteger(String key, Integer defaultValue) {
        final String value = getPropertyValue(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return defaultValue;
    }

    private Boolean getPropertyAsBoolean(String key, Boolean defaultValue) {
        final String value = getPropertyValue(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    private String getPropertyValue(String key) {
        try {
            final List<Config> configList = CacheConfigurationLoader.getConfigCache("configList");
            for (Config config : configList) {
                final String value = config.findProperty(key);
                if (value != null) {
                    return value;
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
