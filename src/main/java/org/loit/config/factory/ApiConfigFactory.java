package org.loit.config.factory;

import org.aeonbits.owner.ConfigCache;
import org.loit.config.ApiConfig;
import org.loit.config.DBConfig;

public final class ApiConfigFactory {

    private ApiConfigFactory() {
    }

    public static ApiConfig getConfig() {
        return ConfigCache.getOrCreate(ApiConfig.class);
    }
    public static DBConfig getDBConfig() {
        return ConfigCache.getOrCreate(DBConfig.class);
    }
}
