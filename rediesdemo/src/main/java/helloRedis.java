import redis.clients.jedis.Jedis;

public class helloRedis {
    public static void main(String[] args){
        Jedis jedis = new Jedis("192.168.149.129");
        jedis.auth("123");
        System.out.println("server is running"+jedis.ping());
        System.out.println("connectino to server succesfully");
        jedis.set("title","helloworld");
        System.out.println(jedis.get("title"));

    }
}
