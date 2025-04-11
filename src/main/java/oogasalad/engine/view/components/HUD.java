package oogasalad.engine.view.components;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class HUD extends Group {

  public void initialRender() {
    Text playerScore = new Text("Score: " + "1000");
  }


}
