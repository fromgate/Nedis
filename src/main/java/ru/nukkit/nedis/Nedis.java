package ru.nukkit.nedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class Nedis {

    /**
     * @return state of default Redis server connection
     */
    public static boolean isEnabled(){
        return NedisPlugin.isJedisConnected();
    }

    /**
     * Get resource (jedis) from default JedisPool
     * @return Jedis resource. Null if default server connection failed
     */
    public static Jedis getResource(){
        return isEnabled() ? getJedisPool().getResource() : null;
    }

    /**
     * Get default JedisPool
     * @return default Jedis pool. Null if default server connection failed
     */
    public static JedisPool getJedisPool(){
        if (!isEnabled()) return null;
        return NedisPlugin.getPlugin().getJedisPool();
    }


    /**
     * Create new JedisPool
     * @param host - server IP or url
     * @param port - server port
     * @return - created JedisPool
     */
    public static JedisPool createJedisPool(String host, int port){
        return NedisPlugin.getPlugin().createJedisPool(host,port);
    }

    /**
     * Create new JedisPool
     * @param host - server IP or url
     * @param port - server port
     * @param timeout - connection time out, use 0 for default
     * @param password - password, use "" or null to connect without password
     * @param database - database index, use 0 for default
     * @param clientName - client name, use "" or null for default
     * @return
     */
    public static JedisPool createJedisPool(String host, int port, int timeout, String password, int database, String clientName){
        return NedisPlugin.getPlugin().createJedisPool(host,port,timeout,password,database,clientName);
    }

}
