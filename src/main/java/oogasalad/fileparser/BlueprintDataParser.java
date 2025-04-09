package oogasalad.fileparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import oogasalad.exceptions.BlueprintParseException;
import oogasalad.fileparser.records.BlueprintData;
import oogasalad.fileparser.records.EventData;
import oogasalad.fileparser.records.HitBoxData;
import oogasalad.fileparser.records.SpriteData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The BlueprintDataParser class is responsible for parsing XML blueprint data into a map of
 * {@link BlueprintData} records. It processes various XML elements such as game nodes, object
 * groups, individual game objects, events, and property nodes (delegated to {@link PropertyParser})
 * for both double and string properties.
 * <p>
 * Example usage:
 * <pre>
 *   Element root = ...; // obtain the XML root element
 *   List&lt;EventData&gt; events = ...; // get event data list
 *   BlueprintDataParser parser = new BlueprintDataParser();
 *   Map&lt;Integer, BlueprintData&gt; blueprintData = parser.getBlueprintData(root, events);
 * </pre>
 * </p>
 *
 * @author Billy
 */
public class BlueprintDataParser {

  private String groupName = "";
  private String gameName = "";
  private SpriteDataParser mySpriteDataParser;
  private HitBoxDataParser myHitBoxDataParser;
  private List<EventData> myEventDataList;
  private PropertyParser propertyParser;

  /**
   * Constructs a new BlueprintDataParser and initializes the required parsers.
   */
  public BlueprintDataParser() {
    propertyParser = new PropertyParser();
    myHitBoxDataParser = new HitBoxDataParser();
  }

  /**
   * Extracts blueprint data from the provided XML root element.
   *
   * @param root      the root XML {@link Element} containing <code>&lt;game&gt;</code> nodes.
   * @param eventList a list of {@link EventData} objects to be associated with blueprint event
   *                  IDs.
   * @return a {@link Map} of blueprint data, keyed by their blueprint ID.
   * @throws BlueprintParseException if any parsing error occurs.
   */
  public Map<Integer, BlueprintData> getBlueprintData(Element root, List<EventData> eventList)
      throws BlueprintParseException {
    myEventDataList = eventList;
    NodeList gameNodes = root.getElementsByTagName("game");
    List<BlueprintData> gameObjectDataList = new ArrayList<>();
    for (int i = 0; i < gameNodes.getLength(); i++) {
      Node node = gameNodes.item(i);
      if (!(node instanceof Element)) {
        throw new BlueprintParseException("error.gameNode.notElement");
      }
      Element gameElement = (Element) node;
      gameName = gameElement.getAttribute("name");
      gameObjectDataList.addAll(parseByGame(gameElement));
    }
    return createBlueprintDataMap(gameObjectDataList);
  }

  /**
   * Creates a map from a list of {@link BlueprintData} records using their blueprint IDs as keys.
   *
   * @param blueprintDataList the list of {@link BlueprintData} records.
   * @return a {@link Map} where each key is a blueprint ID and the value is the corresponding
   * {@link BlueprintData} record.
   */
  private Map<Integer, BlueprintData> createBlueprintDataMap(
      List<BlueprintData> blueprintDataList) {
    Map<Integer, BlueprintData> blueprintDataMap = new HashMap<>();
    for (BlueprintData data : blueprintDataList) {
      blueprintDataMap.put(data.blueprintId(), data);
    }
    return blueprintDataMap;
  }

  /**
   * Parses the blueprint data contained within a <code>&lt;game&gt;</code> element.
   *
   * @param gameNode the XML {@link Element} representing a game.
   * @return a {@link List} of {@link BlueprintData} records extracted from the game element.
   * @throws BlueprintParseException if any parsing error occurs.
   */
  private List<BlueprintData> parseByGame(Element gameNode) throws BlueprintParseException {
    NodeList objectGroupNodes = gameNode.getElementsByTagName("objectGroup");
    List<BlueprintData> gameObjectsList = new ArrayList<>();
    for (int i = 0; i < objectGroupNodes.getLength(); i++) {
      Node node = objectGroupNodes.item(i);
      if (!(node instanceof Element)) {
        throw new BlueprintParseException("error.objectGroup.notElement");
      }
      Element objectGroup = (Element) node;
      groupName = objectGroup.getAttribute("name");
      gameObjectsList.addAll(parseByObjectGroup(objectGroup));
    }
    return gameObjectsList;
  }

  /**
   * Parses the blueprint data contained within an <code>&lt;objectGroup&gt;</code> element.
   *
   * @param objectGroupNode the XML {@link Element} representing an object group.
   * @return a {@link List} of {@link BlueprintData} records extracted from the object group.
   * @throws BlueprintParseException if any parsing error occurs.
   */
  private List<BlueprintData> parseByObjectGroup(Element objectGroupNode)
      throws BlueprintParseException {
    List<BlueprintData> gameObjectsGroupList = new ArrayList<>();
    NodeList gameObjectNodes = objectGroupNode.getElementsByTagName("object");
    for (int i = 0; i < gameObjectNodes.getLength(); i++) {
      Node node = gameObjectNodes.item(i);
      // Skip text nodes that are only whitespace.
      if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty()) {
        continue;
      }
      if (!(node instanceof Element)) {
        continue;
      }
      Element gameObjectNode = (Element) node;
      gameObjectsGroupList.add(parseGameObjectData(gameObjectNode));
    }
    return gameObjectsGroupList;
  }

  /**
   * Parses a single game object node into a {@link BlueprintData} record.
   * <p>
   * This method extracts basic attributes (ID, type, sprite name, and sprite file), creates the
   * corresponding {@link SpriteData} and {@link HitBoxData} objects, processes event identifiers,
   * and parses property nodes using the dedicated {@link PropertyParser}.
   * </p>
   *
   * @param gameObjectNode the XML {@link Element} representing a game object.
   * @return the {@link BlueprintData} record constructed from the game object.
   * @throws BlueprintParseException if a parsing error occurs or if a required attribute is not in
   *                                 the correct format.
   */
  private BlueprintData parseGameObjectData(Element gameObjectNode)
      throws BlueprintParseException {
    try {
      mySpriteDataParser = new SpriteDataParser();

      int id = Integer.parseInt(gameObjectNode.getAttribute("id"));
      String type = gameObjectNode.getAttribute("type");
      String spriteName = gameObjectNode.getAttribute("spriteName");
      String spriteFile = gameObjectNode.getAttribute("spriteFile");

      SpriteData spriteData = null;
      if (gameName != null && !gameName.isEmpty()) {
        spriteData = mySpriteDataParser.getSpriteData(gameName, groupName, type, spriteName,
            spriteFile);
      }

      HitBoxData hitBoxData = myHitBoxDataParser.getHitBoxData(gameObjectNode);
      List<EventData> eventDataList = getmyEventDataList(gameObjectNode);

      Map<String, Double> doubleProperties = propertyParser.parseDoubleProperties(gameObjectNode);
      Map<String, String> stringProperties = propertyParser.parseStringProperties(gameObjectNode);

      return new BlueprintData(
          id,
          gameName,
          groupName,
          type,
          spriteData,
          hitBoxData,
          eventDataList,
          stringProperties,
          doubleProperties
      );
    } catch (NumberFormatException e) {
      throw new BlueprintParseException("error.number.format");
    }
  }

  /**
   * Creates the event data list for the {@link BlueprintData} by retrieving events associated with
   * the game object.
   *
   * @param gameObjectNode the node containing the comma separated eventIDs attribute.
   * @return the full list of {@link EventData} objects, or an empty list if none are specified.
   */
  private List<EventData> getmyEventDataList(Element gameObjectNode) {
    String eventIDs = gameObjectNode.getAttribute("eventIDs");
    List<EventData> eventDataList = new ArrayList<>();
    if (eventIDs != null && !eventIDs.isEmpty()) {
      String[] eventIdArray = eventIDs.split(",");
      for (String eventId : eventIdArray) {
        eventDataList.add(getEventByID(eventId));
      }
    }
    return eventDataList;
  }

  /**
   * Retrieves the {@link EventData} matching the provided event ID from the internal event list.
   *
   * @param id the event ID to look for.
   * @return the matching {@link EventData} if found; otherwise, {@code null}.
   */
  private EventData getEventByID(String id) {
    for (EventData eventData : myEventDataList) {
      if (Objects.equals(id, eventData.eventId())) {
        return eventData;
      }
    }
    return null;
  }
}
