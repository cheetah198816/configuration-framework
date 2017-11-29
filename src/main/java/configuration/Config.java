package configuration;

/**
 * Created by chetan on 26.11.2017.
 */
public interface Config {
    /**
     * finds a property for a given key.
     * @param key the key for the property.
     * @return value of the property or else null.
     */
    String findProperty(String key);
}
