package configuration;

import java.util.Map;

/**
 * Created by chetan on 25.11.2017.
 */
public class EnvVariablesConfig implements Config {

    private Map<String, String> envVariablesMap;

    @Override
    public String findProperty(String key) {
        return envVariablesMap.get(key);
    }

    public void setEnvVariablesMap(Map<String, String> envVariablesMap) {
        this.envVariablesMap = envVariablesMap;
    }
}
