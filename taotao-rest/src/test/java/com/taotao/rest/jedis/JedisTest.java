package com.taotao.rest.jedis;

import java.awt.image.FilteredImageSource;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis("192.168.180.131", 6379);
		jedis.set("key1", "jedistest1");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
	}

	@Test
	public void testJedisPool() {
		JedisPool jedisPool = new JedisPool("192.168.180.131", 6379);
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.180.131", 7001));
		nodes.add(new HostAndPort("192.168.180.131", 7002));
		nodes.add(new HostAndPort("192.168.180.131", 7003));
		nodes.add(new HostAndPort("192.168.180.131", 7004));
		nodes.add(new HostAndPort("192.168.180.131", 7005));
		nodes.add(new HostAndPort("192.168.180.131", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("key1", "100000");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}

	@Test
	public void testJedisClient() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationJedis.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		jedis.close();
		pool.close();
	}

	@Test
	public void testJedisCluster2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationJedis.xml");
		JedisCluster cluster = (JedisCluster) context.getBean("jedisCluster");
		System.out.println(cluster.get("a"));
		cluster.close();
	}

}
