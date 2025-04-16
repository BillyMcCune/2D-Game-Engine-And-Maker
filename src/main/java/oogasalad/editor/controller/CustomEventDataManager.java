package oogasalad.editor.controller;

import java.util.UUID;
import oogasalad.editor.model.data.EditorLevelData;
import oogasalad.editor.model.data.EditorObject;
import oogasalad.editor.model.data.object.event.AbstractEventMapData;
import oogasalad.editor.model.data.object.event.EditorEvent;

/**
 * Manages the Custom Event related data of a specific object.
 */
public class CustomEventDataManager extends EditorEventDataManager {

  @Override
  protected AbstractEventMapData createDataIfAbsent(EditorObject object) {
    return object.getCustomEventData();
  }

  public CustomEventDataManager(EditorLevelData level) {
    super(level);
  }

  /**
   * Sets an event for the specified object.
   * @param objectId The UUID of the object.
   * @param eventId The ID of the event.
   * @param event The event to set.
   */
  public void setEvent(UUID objectId, String eventId, EditorEvent event) {
    EditorObject object = getObjectById(objectId);
    if (object != null) {
      object.getCustomEventData().setEvent(eventId, event);
    }
  }
}
