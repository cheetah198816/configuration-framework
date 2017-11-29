package configuration;

/**
 * Created by chetan on 25.11.2017.
 */
public class PropertiesConfig implements Config {

    private CaseInsensitiveProperties caseInsensitiveProperties;

    @Override
    public String findProperty(String key) {
        return caseInsensitiveProperties.getPropertyIgnoreCase(key);
    }

    public void setCaseInsensitiveProperties(CaseInsensitiveProperties caseInsensitiveProperties) {
        this.caseInsensitiveProperties = caseInsensitiveProperties;
    }

}
