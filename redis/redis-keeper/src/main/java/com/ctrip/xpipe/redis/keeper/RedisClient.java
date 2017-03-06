package com.ctrip.xpipe.redis.keeper;

import java.io.Closeable;
import java.util.Set;

import com.ctrip.xpipe.api.lifecycle.Releasable;
import com.ctrip.xpipe.api.observer.Observable;
import com.ctrip.xpipe.redis.core.protocal.CAPA;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * @author wenchao.meng
 *
 * 2016年4月22日 上午11:25:07
 */
public interface RedisClient extends Observable, Infoable, Closeable, RedisRole, Releasable{
	
	public static enum CLIENT_ROLE{
		NORMAL,
		SLAVE
	}
	
	RedisSlave becomeSlave();
	
	RedisKeeperServer getRedisKeeperServer();

	void setSlaveListeningPort(int port);

	int getSlaveListeningPort();

	void capa(CAPA capa);

	boolean capaOf(CAPA capa);

	Set<CAPA> getCapas();
	
	String []readCommands(ByteBuf byteBuf);

	String info();
	
	Channel channel();

	void sendMessage(ByteBuf byteBuf);
	
	void sendMessage(byte[] bytes);
	
	void addChannelCloseReleaseResources(Releasable releasable);

	void processCommandSequentially(Runnable runnable);

}