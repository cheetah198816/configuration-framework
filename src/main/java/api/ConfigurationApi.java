package api;

/**
 * Created by chetan on 25.11.2017.
 */
public interface ConfigurationApi {

    /**
     * fetches the property value.
     * @param propertyName name of the property.
     * @param defaultValue default value for the property
     * @param tClass value type of the property
     * @param <T> the value type of the property.
     * @return the value of the property or else default Value.
     */
    <T>  T getPropertyValue(String propertyName, T defaultValue, Class<T> tClass);
}
