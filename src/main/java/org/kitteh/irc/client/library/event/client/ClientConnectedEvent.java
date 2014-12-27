/*
 * * Copyright (C) 2013-2014 Matt Baxter http://kitteh.org
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kitteh.irc.client.library.event.client;

import org.kitteh.irc.client.library.ServerInfo;
import org.kitteh.irc.client.library.element.Actor;

/**
 * The {@link org.kitteh.irc.client.library.Client} has successfully
 * connected to the server. At this time the client will begin to send
 * queued messages which were not essential to establishing the connection.
 */
public class ClientConnectedEvent {
    private final Actor server;
    private final ServerInfo serverInfo;

    /**
     * Creates the event.
     *
     * @param server the server to which the client is connected
     */
    public ClientConnectedEvent(Actor server, ServerInfo serverInfo) {
        this.server = server;
        this.serverInfo = serverInfo;
    }

    /**
     * Gets the server name to which the client has connected
     *
     * @return the server the client is connected to
     */
    public Actor getServer() {
        return this.server;
    }

    /**
     * Gets information about the server to which the client is currently
     * connected. As long as the client remains connected the information
     * returned by this object will update according to information received
     * from the server. Note that at the time of this event the server may not
     * yet have sent any information.
     *
     * @return the server information object
     */
    public ServerInfo getServerInfo() {
        return this.serverInfo;
    }
}