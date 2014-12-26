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
package org.kitteh.irc.client.library;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.util.Consumer;

import java.util.Set;

/**
 * An individual IRC connection, see {@link ClientBuilder} to create one.
 */
public interface Client {
    /**
     * Adds channels to this client.
     * <p>
     * Joins the channel if already connected.
     *
     * @param channel channel(s) to add
     */
    void addChannel(String... channel);

    /**
     * Executes a command which will receive a
     * @param command
     * @param args
     * @param consumer
     */
    void executeCommand(Command command, String args, Consumer<? extends CommandResponse> consumer);

    /**
     * Gets the channels in which the client is currently present.
     *
     * @return an immutable set snapshotting the client's current channels
     */
    Set<Channel> getChannels();

    /**
     * Gets the client's event manager.
     *
     * @return the event manager for this client
     */
    EventManager getEventManager();

    /**
     * Gets the nickname the client intends to possess. May not reflect
     * the current nickname if it's taken. The client will automatically
     * attempt to take back this nickname.
     * <p>
     * Use {@link #getNick()} for the current nick.
     *
     * @return the nickname the client tries to maintain
     */
    String getIntendedNick();

    /**
     * Gets the client name. This name is just an internal name for reference
     * and is not visible from IRC.
     *
     * @return the client name
     */
    String getName();

    /**
     * Gets the current nickname the client has.
     *
     * @return the current nick
     */
    String getNick();

    /**
     * Removes a channel from the client, leaving as necessary.
     *
     * @param channel channel to leave
     */
    void removeChannel(String channel);

    /**
     * Sends a CTCP message to a target user or channel. Automagically adds
     * the CTCP delimiter around the message and escapes the characters that
     * need escaping when sending a CTCP message.
     * <p>
     * <i>Note: CTCP replies should not be sent this way. Catch the message
     * with the {@link org.kitteh.irc.client.library.event.user.PrivateCTCPQueryEvent}</i>
     *
     * @param target the destination of the message
     * @param message the message to send
     */
    void sendCTCPMessage(String target, String message);

    /**
     * Sends a message to a target user or channel.
     *
     * @param target the destination of the message
     * @param message the message to send
     */
    void sendMessage(String target, String message);

    /**
     * Sends a raw IRC message.
     *
     * @param message message to send
     */
    void sendRawLine(String message);

    /**
     * Sets the authentication method, user and password.
     *
     * @param type authentication type
     * @param nick nickname
     * @param pass password
     */
    void setAuth(AuthType type, String nick, String pass);

    /**
     * Sets the delay between messages sent to the server.
     * <p>
     * Default is 1200ms.
     *
     * @param delay milliseconds between sent messages
     */
    void setMessageDelay(int delay);

    /**
     * Sets the nick the client wishes to use.
     *
     * @param nick new nickname
     */
    void setNick(String nick);

    /**
     * Shuts down the client.
     *
     * @param reason quit message to send, null for blank message
     */
    void shutdown(String reason);
}