package com.jukusoft.mmo.gameserver.core.stream;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

public class BufferStream implements ReadStream<Buffer>, WriteStream<Buffer> {

    private final RecordParser recordParser;
    private final WriteStream<Buffer> writeStream;
    private int size = -1;
    private Handler<Throwable> exceptionHandler;

    //source: https://github.com/vert-x3/vertx-examples/blob/master/core-examples/src/main/java/io/vertx/example/core/net/stream/BufferStream.java

    public BufferStream(ReadStream<Buffer> rs, WriteStream<Buffer> ws) {
        if (rs == null) {
            throw new NullPointerException("read stream cannot be null.");
        }

        if (ws == null) {
            throw new NullPointerException("write stream cannot be null.");
        }

        //first, we require 4 bytes (1 integer)
        recordParser = RecordParser.newFixed(4, rs);
        writeStream = ws;

        //set exception handler
        recordParser.exceptionHandler(throwable -> {
            if (exceptionHandler != null) {
                exceptionHandler.handle(throwable);
            }
        });

        writeStream.exceptionHandler(throwable -> {
            if (exceptionHandler != null) {
                exceptionHandler.handle(throwable);
            }
        });
    }

    @Override
    public BufferStream exceptionHandler(Handler<Throwable> handler) {
        exceptionHandler = handler;
        return this;
    }

    @Override
    public BufferStream write(Buffer content) {
        if (content == null) {
            throw new NullPointerException("content cannot be null.");
        } else {
            Buffer protocol = Buffer.buffer(content.length() + 4);
            protocol.appendInt(0);
            protocol.appendBuffer(content);
            protocol.setInt(0, protocol.length() - 4);
            writeStream.write(protocol);
        }

        return this;
    }

    @Override
    public void end() {
        writeStream.end();
    }

    @Override
    public BufferStream setWriteQueueMaxSize(int maxSize) {
        writeStream.setWriteQueueMaxSize(maxSize);
        return this;
    }

    @Override
    public boolean writeQueueFull() {
        return writeStream.writeQueueFull();
    }

    @Override
    public BufferStream drainHandler(Handler<Void> handler) {
        writeStream.drainHandler(handler);
        return this;
    }

    @Override
    public BufferStream handler(Handler<Buffer> handler) {
        if (handler == null) {
            recordParser.handler(null);
            recordParser.exceptionHandler(null);
            recordParser.endHandler(null);
            return this;
        }
        recordParser.handler(buffer -> {
            try {
                // Message size mode
                if (size == -1) {
                    size = buffer.getInt(0);
                    recordParser.fixedSizeMode(size);
                    // Message body mode
                } else {
                    size = -1;
                    recordParser.fixedSizeMode(4);

                    // Batch message data (Buffer)
                    final Buffer payload = buffer.getBuffer(0, buffer.length());

                    handler.handle(payload);
                }
            } catch (Exception throwable) {
                if (exceptionHandler != null) {
                    exceptionHandler.handle(throwable);
                }
            }
        });
        return this;
    }

    @Override
    public BufferStream pause() {
        recordParser.pause();
        return this;
    }

    @Override
    public BufferStream resume() {
        recordParser.resume();
        return this;
    }

    @Override
    public BufferStream endHandler(Handler<Void> endHandler) {
        recordParser.endHandler(endHandler);
        return this;
    }

}
