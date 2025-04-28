package oogasalad.engine.view.components;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.ResourceManager;
import oogasalad.ResourceManagerAPI;
import oogasalad.engine.model.object.ImmutableGameObject;
import oogasalad.engine.view.Display;
import oogasalad.engine.view.ViewState;

/**
 * This class hold the components for a new game screen once the player either completes a level or
 * loses a life. It displays the high score of the player currently.
 * @author Aksel Bell, Luke Nam
 */
public class NewGameComponents extends Display {

  private static final ResourceManagerAPI resourceManager = ResourceManager.getInstance();

  // contains a game over object if it is game ober
  // contains a play again
  // contains a high score object
  private final ViewState viewState;

  /**
   * Constructor for the NewGameComponents class that initializes the high score text.
   * TODO: Externalize high score configuration
   * @param viewState the current view state of the game
   */
  public NewGameComponents(ViewState viewState) {
    this.viewState = viewState;
    initialize();
  }

  /**
   * Renders the high score text on the game engine screen.
   */
  private void initialize() {
    VBox buttonContainer = new VBox();
    buttonContainer.setSpacing(20);
    buttonContainer.setLayoutX(250); // change to levelViewWidth / 2

    this.getChildren().add(buttonContainer);
  }

  @Override
  public void removeGameObjectImage(ImmutableGameObject gameObject) {
    throw new UnsupportedOperationException(resourceManager.getText("exceptions","CannotRemoveGameObjectImage"));
  }

  @Override
  public void addGameObjectImage(ImmutableGameObject gameObject) {
    throw new UnsupportedOperationException(resourceManager.getText("exceptions","CannotRemoveGameObjectImage"));
  }

  @Override
  public void renderPlayerStats(ImmutableGameObject player) {
    throw new UnsupportedOperationException(resourceManager.getText("exceptions","CannotRenderPlayerStats"));
  }

  @Override
  public void renderEndGameScreen(boolean gameWon) {
    throw new UnsupportedOperationException(resourceManager.getText("exceptions","CannotRenderEndGameScreen"));
  }

  @Override
  protected void setViewMode(ViewState viewState) throws IllegalStateException {
    return;
  }


}
