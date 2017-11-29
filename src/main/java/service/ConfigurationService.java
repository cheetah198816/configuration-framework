package service;

/**
 * Created by chetan on 25.11.2017.
 */
public interface ConfigurationService {

    <T> T getProperty(String key, T defaultValue, Class<T> tClass);
}
