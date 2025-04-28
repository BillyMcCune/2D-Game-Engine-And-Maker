package oogasalad.engine.view.screen;

import oogasalad.engine.model.object.ImmutableGameObject;
import oogasalad.engine.view.Display;
import oogasalad.engine.view.ViewState;

/**
 * User LogIn Screen
 *
 * @author Billy McCune
 */
public class UserLoginScreen extends Display {

  @Override
  public void removeGameObjectImage(ImmutableGameObject gameObject) {
//No implementation needed
  }

  @Override
  public void addGameObjectImage(ImmutableGameObject gameObject) {
//No implementation needed
  }

  @Override
  public void renderPlayerStats(ImmutableGameObject player) {
//No implementation needed
  }

  @Override
  public void renderEndGameScreen(boolean gameWon) {
//No implementation needed
  }

  @Override
  protected void setViewMode(ViewState viewState) throws IllegalStateException {
    return;
  }
}
