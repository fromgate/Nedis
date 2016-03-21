package ru.nukkit.nedis;

import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class NedisPlugin extends PluginBase {

    private static NedisPlugin plugin;
    private JedisPool jedisPool;
    private NedisConfig cfg;
    private boolean enabled;

    protected static NedisPlugin getPlugin(){
        return plugin;
    }

    protected static boolean isJedisConnected() {
        return plugin.enabled;
    }

    public void onEnable(){
        plugin = this;
        cfg = new NedisConfig(this);
        cfg.load();
        cfg.save();

        this.getLogger().info("Trying to connect to Redis server: "+cfg.host+":"+cfg.port);
        try {
            jedisPool = createJedisPool(cfg.host, cfg.port, cfg.timeout,cfg.password,cfg.database,cfg.clientName);
            if (jedisPool != null) enabled = true;
            Jedis ping = jedisPool.getResource();
            this.getLogger().info("Pinging server. Responce: "+ping.ping());
            ping.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected JedisPool createJedisPool(String host, int port){
        return createJedisPool(host,port,0,null,0,null);
    }

    protected JedisPool createJedisPool(String host, int port, int timeout, String password, int database, String clientName){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(3);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMaxIdle(99);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(50L);
        return new JedisPool(poolConfig,
                host,port,
                timeout,
                password==null||password.isEmpty()? (String) null : password,
                database,
                clientName==null||clientName.isEmpty() ? (String) null : clientName);
    }

    protected JedisPool getJedisPool(){
        return this.jedisPool;
    }
}