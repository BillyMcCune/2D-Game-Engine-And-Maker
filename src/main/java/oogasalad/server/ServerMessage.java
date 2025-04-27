package oogasalad.server;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * Defines a server message data structure.
 *
 * @author Aksel Bell
 */
public class ServerMessage {
  private String type;
  private String playerCount;
  private String players;
  private String message;
  private static Gson gson = new Gson();

  public ServerMessage(String type, String message) {
    this.type = type;
    this.message = message;
  }

  /**
   * Sends the gson message to the desired socket.
   *
   * @param socket a socket connected to the server.
   */
  public void sendToSocket(ClientSocket socket) {
    socket.send(gson.toJson(this));
  }

  /**
   * @return the type of the message
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the number of people in the current lobby
   * @return player count
   */
  public int getPlayerCount() {
    return Integer.parseInt(playerCount);
  }

  /**
   * Returns the list of people in the current lobby
   * @return player list
   */
  public List<String> getPlayers() {
    String[] playersArr = players.split(",");
    return Arrays.asList(playersArr);
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }
}
