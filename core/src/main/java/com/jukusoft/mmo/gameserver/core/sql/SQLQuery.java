package com.jukusoft.mmo.gameserver.core.sql;

import io.vertx.core.Handler;

import java.sql.Connection;

public interface SQLQuery<T> {

    public void execute (Connection connection, Handler<T> afterExecutedHandler);

}
