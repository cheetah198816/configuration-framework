package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import configuration.CaseInsensitiveProperties;
import configuration.Config;
import configuration.EnvVariablesConfig;
import configuration.PropertiesConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by chetan on 27.11.2017.
 */
public class CacheConfigurationLoader {

    private static LoadingCache<String, List<Config>> configCache;

    private static final Integer TIME_TO_RELOAD = 4000;

    public void init() {
        configCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(TIME_TO_RELOAD, TimeUnit.MILLISECONDS)
                .build(
                        new CacheLoader<String, List<Config>>() {
                            public List<Config> load(String key) {
                                return createConfigListCache();
                            }
                            public ListenableFuture<List<Config>> reload(final String key, List<Config> configs) {
                                        ListenableFutureTask<List<Config>> task = ListenableFutureTask.create(new Callable<List<Config>>() {
                                        public List<Config> call() {
                                            return createConfigListCache();
                                        }
                                    });
                                    Executors.newSingleThreadExecutor().execute(task);
                                    return task;
                                }
                            });

    }

    private List<Config> createConfigListCache() {
        final List<Config> configList = new ArrayList<>();
        configList.add(loadEnvironmentVariables(new EnvVariablesConfig()));
        configList.add(loadProperties(new PropertiesConfig()));

        return configList;
    }

    public static List<Config> getConfigCache(String key) throws ExecutionException {
        return configCache.get(key);
    }

    private Config loadEnvironmentVariables(EnvVariablesConfig envVariablesConfig) {
        final Map<String, String> envVariablesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        envVariablesMap.putAll(System.getenv());
        envVariablesConfig.setEnvVariablesMap(envVariablesMap);

        return envVariablesConfig;
    }

    private Config loadProperties(PropertiesConfig propertiesConfig) {
        final CaseInsensitiveProperties prop = new CaseInsensitiveProperties();
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream("configuration.properties");
        try {
            prop.load(is);
            propertiesConfig.setCaseInsensitiveProperties(prop);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return propertiesConfig;
    }
}
