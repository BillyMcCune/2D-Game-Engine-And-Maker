package oogasalad.engine.controller.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.zip.DataFormatException;
import oogasalad.engine.model.object.ImmutableGameObject;
import oogasalad.engine.view.camera.Camera;
import oogasalad.exceptions.BlueprintParseException;
import oogasalad.exceptions.EventParseException;
import oogasalad.exceptions.GameObjectParseException;
import oogasalad.exceptions.HitBoxParseException;
import oogasalad.exceptions.LayerParseException;
import oogasalad.exceptions.LevelDataParseException;
import oogasalad.exceptions.PropertyParsingException;
import oogasalad.exceptions.SpriteParseException;
import oogasalad.fileparser.records.LevelData;

/**
 * Interface for interacting with GameControllers
 *
 * @author Gage Garcia, Alana Zinkin
 */

public interface GameControllerAPI {

  /**
   * @return list of records of game objects only containing relevant information for the view
   */
  List<ImmutableGameObject> getImmutableObjects();

  /**
   * @return list of immutable player game objects
   */
  List<ImmutableGameObject> getImmutablePlayers();

  /**
   * returns view object data using id
   *
   * @param uuid string version of uuid
   * @return view object model data
   */
  ImmutableGameObject getViewObjectByUUID(String uuid);

  /**
   * Advances the game state by one "tick" or step, typically by: 1) Calling each phase controller
   * (input, physics, collision, etc.) 2) Resolving any post-update tasks (e.g. removing destroyed
   * objects) 3) Tracking which objects have changed for rendering
   */
  void updateGameState()
      throws LayerParseException, EventParseException, BlueprintParseException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, DataFormatException, LevelDataParseException, PropertyParsingException, SpriteParseException, HitBoxParseException, GameObjectParseException, ClassNotFoundException, InstantiationException;

  /**
   * Loads a new level or scene, potentially calling file loaders to retrieve data and
   * re-initializing internal structures (objects, controllers, etc.).
   */
  void setLevelData(LevelData data)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

  /**
   * @return the camera object which translates the game scene
   */
  Camera getCamera();

  /**
   * Returns the current player object associated with the editor or game.
   *
   * @return the player object, or {@code null} if no player is currently set
   */
  Object getPlayer();

  /**
   * Returns the name of the currently selected game.
   *
   * @return the current game name, or {@code null} if no game is selected
   */
  String getCurrentGameName();

  /**
   * Returns the name of the currently selected level.
   *
   * @return the current level name, or {@code null} if no level is selected
   */
  String getCurrentLevelName();

  /**
   * deprecated method
   */
  @Deprecated
  void getUpdatedObjects();

  /**
   * Loads a new level or scene, potentially calling file loaders to retrieve data and
   * re-initializing internal structures (objects, controllers, etc.).
   */
  @Deprecated
  void loadLevel(LevelData data);
}