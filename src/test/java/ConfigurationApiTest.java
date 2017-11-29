import api.ConfigurationApi;
import api.impl.ConfigurationApiImpl;
import cache.CacheConfigurationLoader;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chetan on 25.11.2017.
 */
public class ConfigurationApiTest {

    private ConfigurationApi configurationApi = new ConfigurationApiImpl();

    @Before
    public void init() {
        final CacheConfigurationLoader cacheConfigurationLoader = new CacheConfigurationLoader();
        cacheConfigurationLoader.init();
    }

    @Test
    public void test_property_fetch_boolean() {
        final Boolean propertyValue = configurationApi.getPropertyValue("testBoolean", false, Boolean.class);
        assertThat(propertyValue).isEqualTo(false);
    }

    @Test
    public void test_property_fetch_integer() {
        final Integer propertyValue = configurationApi.getPropertyValue("testInteger", 0, Integer.class);
        assertThat(propertyValue).isEqualTo(23);
    }

    @Test
    public void test_property_fetch_double() {
        final Double propertyValue = configurationApi.getPropertyValue("testDouble", 0.0, Double.class);
        assertThat(propertyValue).isEqualTo(1.24);
    }

    @Test
    public void test_property_fetch_string() {
        String propertyValue = configurationApi.getPropertyValue("testString", "Hi", String.class);
        assertThat(propertyValue).isEqualTo("Hello");
    }

    @Test
    public void test_property_fetch_float() {
        Float propertyValue = configurationApi.getPropertyValue("testFloat", 0.0f, Float.class);
        assertThat(propertyValue).isEqualTo(1.23f);
    }

}
