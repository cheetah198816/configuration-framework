package api.impl;

import api.ConfigurationApi;
import service.ConfigurationService;
import service.impl.ConfigurationServiceImpl;

/**
 * Created by chetan on 29.11.2017.
 */
public class ConfigurationApiImpl implements ConfigurationApi {

    private ConfigurationService configurationService = new ConfigurationServiceImpl();

    public <T>  T getPropertyValue(String propertyName, T defaultValue, Class<T> tClass) {
        return configurationService.getProperty(propertyName, defaultValue, tClass);
    }
}
