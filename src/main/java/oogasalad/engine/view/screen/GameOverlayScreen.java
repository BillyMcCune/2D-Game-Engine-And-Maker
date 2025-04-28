package oogasalad.engine.view.screen;

import java.util.Objects;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import oogasalad.ResourceManager;
import oogasalad.ResourceManagerAPI;
import oogasalad.engine.view.Display;
import oogasalad.engine.view.ViewState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract class for implementing special screens during a game, like losing, pausing, and
 * winning.
 *
 * @author Luke Nam
 */
public abstract class GameOverlayScreen extends Display {

  private static final ResourceManagerAPI resourceManager = ResourceManager.getInstance();

  private static final Logger LOG = LogManager.getLogger();
  private ViewState viewState;

  /**
   * Constructor for creating a new GameOverlayScreen for game screen actions, like pausing, losing,
   * and winning.
   *
   * @param viewState the state of the view
   */
  public GameOverlayScreen(ViewState viewState) {
    this.viewState = viewState;
  }

  /**
   * Creates a VBox containing explanatory text for the game overlay screen, such as "YOU WIN!" or
   * "GAME OVER!" following a win or a loss screen. The exact implementation depends based on what
   * the user intends to show on a screen-by-screen basis.
   *
   * @return VBox containing only Text objects
   */
  public abstract VBox createOverlayTextBox();

  /**
   * Creates a VBox containing buttons for the game overlay screen, such as a "Return to Home
   * Screen" button that takes you back home, or a "Restart Level" button to play the level again.
   *
   * @return VBoc containing only Button objects
   */
  public abstract VBox createOverlayButtonBox();

  /**
   * Helper method that creates a stylized JavaFX Text object given a message, font, width, and
   * specified alignment, to standardize the process for adding text for the overlay screen.
   *
   * @param message string message documenting what the Text should print
   * @param font    size of the message
   * @param width   wrapping width of the text box
   * @return Text object for a given message
   */
  public Text createStyledText(String message, Font font, int width) {
    Text text = new Text(message);
    text.setFont(font);
    text.setWrappingWidth(width);
    text.setTextAlignment(TextAlignment.CENTER);
    return text;
  }

  /**
   * Helper method that aligns a given VBox according to a spacing parameter
   *
   * @param box     object containing button elements
   * @param spacing pixel spacing between elements
   */
  public void alignBox(VBox box, double spacing) {
    box.setAlignment(Pos.CENTER);
    box.setSpacing(spacing);
  }

  /**
   * Given a specified property from a properties file, parse it as an integer and return it
   *
   * @param properties   selected properties file with the given key
   * @param propertyName key from the properties file
   * @return integer value of key
   */
  public int getIntegerValue(Properties properties, String propertyName) {
    return Integer.parseInt(Objects.requireNonNull(properties.getProperty(propertyName)).trim());
  }

}
