package configuration;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by chetan on 26.11.2017.
 */
public class CaseInsensitiveProperties extends Properties {

    public String getPropertyIgnoreCase(String key) {
        final String value = getProperty(key);
        if (value != null)
            return value;
        // Not matching with the actual key then
        final Set<Map.Entry<Object, Object>> s = entrySet();
        final Iterator<Map.Entry<Object, Object>> it = s.iterator();
        while (it.hasNext()) {
            final Map.Entry<Object, Object> entry = it.next();
            if (key.equalsIgnoreCase((String) entry.getKey())) {
                return (String) entry.getValue();
            }
        }
        return null;
    }
}
