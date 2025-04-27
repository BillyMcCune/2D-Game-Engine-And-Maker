package oogasalad.engine.model.event.outcome;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.zip.DataFormatException;
import oogasalad.engine.model.animation.AnimationHandlerApi;
import oogasalad.engine.model.animation.DefaultAnimationHandler;
import oogasalad.engine.model.object.GameObject;
import oogasalad.exceptions.BlueprintParseException;
import oogasalad.exceptions.EventParseException;
import oogasalad.exceptions.GameObjectParseException;
import oogasalad.exceptions.HitBoxParseException;
import oogasalad.exceptions.LayerParseException;
import oogasalad.exceptions.LevelDataParseException;
import oogasalad.exceptions.PropertyParsingException;
import oogasalad.exceptions.SpriteParseException;

/**
 * StopObjectAnimationsOutcome stops the object from animating when even occurs
 *
 * @author Billy McCune
 */
public class stopObjectAnimationsOutcome implements Outcome {

  private AnimationHandlerApi animationHandler;

  /**
   * @param animationHandler the handler responsible for managing animations
   */
  public stopObjectAnimationsOutcome(AnimationHandlerApi animationHandler) {
    this.animationHandler = animationHandler;
  }


  @Override
  public void execute(GameObject gameObject, Map<String, String> stringParameters,
      Map<String, Double> doubleParameters)
      throws LayerParseException, EventParseException, BlueprintParseException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, DataFormatException, LevelDataParseException, PropertyParsingException, SpriteParseException, HitBoxParseException, GameObjectParseException, ClassNotFoundException, InstantiationException {
    animationHandler.goToBaseImage(gameObject);
  }
}
