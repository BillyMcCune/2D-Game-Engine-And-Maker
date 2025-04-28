package oogasalad.engine.view.screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.ResourceManager;
import oogasalad.ResourceManagerAPI;
import oogasalad.engine.model.object.ImmutableGameObject;
import oogasalad.engine.view.Display;

import javafx.scene.control.Button;
import oogasalad.engine.view.ViewState;
import oogasalad.engine.view.factory.ButtonActionFactory;

import java.util.Objects;

/**
 * An online lobby screen with a start button and a text displaying the number of players.
 */
public class OnlineLobby extends Display {
  private static ResourceManagerAPI resourceManager = ResourceManager.getInstance();
  private final String lobbyConfig = "engine.view.onlineLobby";
  private String lobbyStylesheet;
  private final int players;
  private final int lobby;
  private final ViewState viewState;

  /**
   * Instantiate an online lobby.
   * @param players number of players in the lobby
   */
  public OnlineLobby(int players, ViewState viewState, int lobby) {
    this.players = players;
    this.lobby = lobby;
    this.viewState = viewState;
    initialize();
  }

  /**
   * Creates the user interface for the online lobby, such as the logo and the number of players and lobby number
   */
  public void initialize() {
    String lobbyStylesheetFilepath = resourceManager.getConfig(lobbyConfig,
            "lobby.stylesheet");
    lobbyStylesheet = Objects.requireNonNull(getClass().getResource(lobbyStylesheetFilepath))
            .toExternalForm();

    HBox topBar = createTopBar();
    HBox mainContent = new HBox();
    VBox gamePanel = createGameBox();
    mainContent.getChildren().addAll(gamePanel);

    HBox buttonBar = createButtonBar();
    VBox root = new VBox(topBar, mainContent, buttonBar);
    root.getStylesheets().add(lobbyStylesheet);
    this.getChildren().add(root);
  }

  /**
   * JavaFX component containing the lobby and player text
   * @return HBox for lobby and player text
   */
  private HBox createTopBar() {
    int lobbyInfoHeight = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.info.height"));
    int lobbyInfoPadding = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.info.padding"));
    int lobbyInfoSpacing = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.info.spacing"));
    HBox topBar = new HBox();
    topBar.setPrefHeight(lobbyInfoHeight);
    topBar.setPadding(new Insets(lobbyInfoPadding));
    topBar.setSpacing(lobbyInfoSpacing);
    topBar.setAlignment(Pos.CENTER);
    topBar.getStyleClass().add("top-bar");
    addTopBarLabels(topBar);
    return topBar;
  }

  /**
   * Add the text to the topBar, showing the lobby number and player count
   * @param topBar the bar to add text to
   */
  private void addTopBarLabels(HBox topBar) {
    String lobbyNumberText = resourceManager.getConfig(lobbyConfig, "lobby.number.text");
    String playersText = resourceManager.getConfig(lobbyConfig, "lobby.players.text");
    Label lobbyNumber = new Label(STR."\{lobbyNumberText} \{lobby}");
    Label playerCount = new Label(STR."\{playersText} \{players}");
    lobbyNumber.getStyleClass().add("info-label");
    playerCount.getStyleClass().add("info-label");

    HBox.setHgrow(lobbyNumber, Priority.ALWAYS);
    HBox.setHgrow(playerCount, Priority.ALWAYS);
    topBar.getChildren().addAll(lobbyNumber, playerCount);
  }

  /**
   * Create the game box as shown on the left side of the multiplayer screen
   * @return game VBox
   */
  private VBox createGameBox() {
    int gameBoxSpacing = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.info.spacing"));
    VBox gameBox = new VBox(gameBoxSpacing);

    int gamePanelWidth = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "game.panel.width"));
    int gamePanelHeight = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "game.panel.height"));

    gameBox.setPrefSize(gamePanelWidth, gamePanelHeight);
    gameBox.setAlignment(Pos.CENTER);
    gameBox.getStyleClass().add("game-box");

    ImageView lobbyLogo = createLobbyLogo();
    gameBox.getChildren().addAll(lobbyLogo);
    return gameBox;
  }

  private ImageView createLobbyLogo() {
    ImageView lobbyLogo = new ImageView();
    try {
      String logoFilepath = resourceManager.getConfig(lobbyConfig, "lobby.logo.filepath");
      Image gameImage = new Image(getClass().getResourceAsStream(logoFilepath));
      lobbyLogo.setImage(gameImage);
    } catch (NullPointerException e) {
      throw new NullPointerException(
              String.format("OOGASalad lobby filepath not found: %s", e.getMessage()));
    }
    scaleLobbyLogo(lobbyLogo);
    return lobbyLogo;
  }

  /**
   * Scales the splash logo given the configuration's image width and height.
   * @param lobbyLogo the logo we are expected to scale
   */
  private void scaleLobbyLogo(ImageView lobbyLogo) {
    int logoWidth = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.logo.width"));
    int logoHeight = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "lobby.logo.height"));
    lobbyLogo.setFitWidth(logoWidth);
    lobbyLogo.setFitHeight(logoHeight);
    lobbyLogo.setPickOnBounds(true);
    lobbyLogo.setPreserveRatio(true);
    lobbyLogo.setId("lobbyLogo");
  }

  /**
   * Creates a bar of buttons that allows the user to navigate between home and the game
   * @return
   */
  private HBox createButtonBar() {
    int buttonWidth = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "button.width"));
    String startButtonText = resourceManager.getConfig(lobbyConfig, "button.start.text");
    String homeButtonText = resourceManager.getConfig(lobbyConfig, "button.home.text");
    int buttonSpacing = Integer.parseInt(
            resourceManager.getConfig(lobbyConfig, "player.box.spacing"));

    HBox buttonBar = new HBox(buttonSpacing);
    buttonBar.setAlignment(Pos.CENTER);
    buttonBar.getStyleClass().add("button-bar");

    Button startButton = createLinkedButton(startButtonText, "splashButtonStartEngine");
    Button homeButton = createLinkedButton(homeButtonText, "levelHomeButton");

    startButton.setPrefWidth(buttonWidth / 2.0);
    homeButton.setPrefWidth(buttonWidth / 2.0);

    buttonBar.getChildren().addAll(startButton, homeButton);
    return buttonBar;
  }

  /**
   * Creates a linked button given its text field and its ID
   * @param buttonText text of the provided button
   * @param buttonID ID of the button to link
   * @return linked button
   */
  private Button createLinkedButton(String buttonText, String buttonID) {
    Button button = new Button(buttonText);
    ButtonActionFactory factory = new ButtonActionFactory(viewState);
    button.setOnAction(event -> {
      factory.getActionAndSendServerMessage(buttonID).run();
    });
    return button;
  }

  @Override
  public void removeGameObjectImage(ImmutableGameObject gameObject) {
    // No game objects to remove in the lobby.
  }

  @Override
  public void addGameObjectImage(ImmutableGameObject gameObject) {
    //
  }

  @Override
  public void renderPlayerStats(ImmutableGameObject player) {
    // No player stats to render in the lobby.
  }
}
