package ru.nukkit.nedis;

import cn.nukkit.plugin.Plugin;
import cn.nukkit.utils.SimpleConfig;
import cn.nukkit.utils.SimpleConfig.Path;

public class NedisConfig extends SimpleConfig {

    @Path(value = "redis.host")
    public String host = "localhost";

    @Path(value = "redis.port")
    public int port=6379;

    @Path(value = "redis.extra-config.timeout")
    public int timeout = 0;

    @Path(value = "redis.extra-config.password")
    public String password = "";

    @Path(value = "redis.extra-config.database")
    public int database = 0;

    @Path(value = "redis.extra-config.clientname")
    public String clientName = "";

    public NedisConfig(Plugin plugin) {
        super(plugin);
    }
}
