package oogasalad.engine.model.event.condition;

import java.util.Map;
import java.util.logging.Logger;
import oogasalad.engine.model.object.GameObject;
import oogasalad.engine.model.object.Player;

/**
 * returns whether a dynamic amount is >= a specified threshold
 *
 * @author Gage Garcia
 */
public class VariableGreaterThanThresholdCondition implements Condition {

  @Override
  public boolean isMet(GameObject gameObject) {
    Map<String, String> stringParams = gameObject.getStringParams();
    Map<String, Double> doubleParams = gameObject.getDoubleParams();
    String variableName = stringParams.get("variable");
    Double amount = doubleParams.get(variableName);
    Double threshold = doubleParams.get("threshold");
    return amount > threshold;
  }
}
