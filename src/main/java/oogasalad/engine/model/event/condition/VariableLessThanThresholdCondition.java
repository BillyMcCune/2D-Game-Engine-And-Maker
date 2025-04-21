package oogasalad.engine.model.event.condition;

import java.util.Map;
import java.util.logging.Logger;
import oogasalad.engine.model.object.GameObject;
import oogasalad.engine.model.object.Player;

/**
 * returns whether a dynamic amount is <= a specified threshold
 *
 * @author Gage Garcia
 */
public class VariableLessThanThresholdCondition implements Condition {
  private Logger LOG = Logger.getLogger(VariableLessThanThresholdCondition.class.getName());
  @Override
  public boolean isMet(GameObject gameObject, Map<String, String> stringParams, Map<String, Double> doubleParams) {
    String variableName = stringParams.get("variable");
    LOG.info("Dynamic var name: " + variableName);
    Double amount = gameObject.getDoubleParams().getOrDefault(variableName,0.0);
    Double threshold = doubleParams.get("threshold");
    return amount <= threshold;
  }
}
