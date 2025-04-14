package oogasalad.engine.view.camera;

import java.util.ResourceBundle;
import javafx.scene.Group;
import oogasalad.Main;
import oogasalad.engine.view.LevelDisplay;

/**
 * AutoScrollingCamera provides an automatic scrolling behavior for the game.
 * <p>
 * Every time updateCamera is called (e.g. once per frame in the game loop), the camera moves by a
 * fixed scroll speed. This is achieved by adjusting the translation of the game world.
 */
public class AutoScrollingCamera implements Camera {

  private static final ResourceBundle LEVEL_RESOURCES = ResourceBundle.getBundle(
      LevelDisplay.class.getPackage().getName() + ".Level");

  private static final ResourceBundle EXCEPTIONS = ResourceBundle.getBundle(
      Main.class.getPackageName() + "." + "Exceptions");

  private double scrollSpeedX;
  private double scrollSpeedY; // Change if vertical scrolling is needed

  // Current accumulated offsets for the camera
  private double currentOffsetX = Double.parseDouble(LEVEL_RESOURCES.getString("CurrentOffsetX"));
  ;
  private double currentOffsetY = Double.parseDouble(LEVEL_RESOURCES.getString("CurrentOffsetY"));
  ;

  /**
   * Updates the camera, causing the game world to scroll automatically. This method should be
   * called at a regular interval (e.g., each frame).
   *
   * @param gameWorld the JavaFX Group representing the game world
   * @throws NullPointerException if gameWorld is null
   */
  @Override
  public void updateCamera(Group gameWorld) {
    if (gameWorld == null) {
      throw new NullPointerException(EXCEPTIONS.getString("GameWorldNull"));
    }
    // Increment the current offsets by the scroll speeds.
    currentOffsetX += scrollSpeedX;
    currentOffsetY += scrollSpeedY;
    // Apply the offsets to the game world.
    // Negative translation moves the world in the opposite direction to simulate camera movement.
    gameWorld.setTranslateX(-currentOffsetX);
    gameWorld.setTranslateY(-currentOffsetY);
  }

  /**
   * sets the X scroll speed for the camera
   *
   * @param scrollSpeedX the X scroll speed
   */
  public void setScrollSpeedX(double scrollSpeedX) {
    this.scrollSpeedX = scrollSpeedX;
  }

  /**
   * sets the Y scroll speed for the camera
   *
   * @param scrollSpeedY the Y scroll speed
   */
  public void setScrollSpeedY(double scrollSpeedY) {
    this.scrollSpeedY = scrollSpeedY;
  }

}
