package oogasalad.engine.view.camera;

import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.scene.Group;
import oogasalad.engine.model.object.ImmutableGameObject;
import oogasalad.engine.view.LevelDisplay;

/**
 * The {@code TimeCamera} class implements the {@link Camera} interface to simulate a camera that
 * follows a specific {@link ImmutableGameObject} in the game world. It adjusts the position of the entire
 * game world to keep the object centered on screen.
 *
 * @author Alana Zinkin
 */
public class TrackerCamera implements Camera {

  private static final ResourceBundle LEVEL_RESOURCES = ResourceBundle.getBundle(
      LevelDisplay.class.getPackage().getName() + ".Level");

  /**
   * Horizontal offset to center the camera based on level width.
   */
  private static final double CAMERA_OFFSET_X = Double.parseDouble(
      LEVEL_RESOURCES.getString("LevelWidth")) / 2.0;

  /**
   * Vertical offset to center the camera based on level height.
   */
  private static final double CAMERA_OFFSET_Y = Double.parseDouble(
      LEVEL_RESOURCES.getString("LevelHeight")) / 2.0;

  private ImmutableGameObject viewObjectToTrack;
  /**
   * Updates the camera view by translating the game world to center the followed object. The game
   * world is moved in the opposite direction of the object's position, so that the object remains
   * centered on the screen.
   *
   * @param gameWorld      the root {@link Group} representing the entire game scene
   */
  @Override
  public void updateCamera(Group gameWorld) {
    if (gameWorld == null) {
      throw new NullPointerException(LEVEL_RESOURCES.getString("GameWorldNull"));
    }
    try {
      gameWorld.setTranslateX(CAMERA_OFFSET_X - viewObjectToTrack.getXPosition());
      gameWorld.setTranslateY(CAMERA_OFFSET_Y - viewObjectToTrack.getYPosition());
    } catch (Exception e) {
      throw new NoSuchElementException(LEVEL_RESOURCES.getString("ObjectDoesntExist"));
    }
  }

  /**
   * sets the view object to track for the camera
   * @param viewObjectToTrack the View Object that the camera tracks
   */
  public void setViewObjectToTrack(ImmutableGameObject viewObjectToTrack) {
    this.viewObjectToTrack = viewObjectToTrack;
  }
}

