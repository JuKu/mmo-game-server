package com.jukusoft.mmo.gameserver.core.network;

@FunctionalInterface
public interface MessageReceiver<T> {

    public void receive(T buffer);

}
