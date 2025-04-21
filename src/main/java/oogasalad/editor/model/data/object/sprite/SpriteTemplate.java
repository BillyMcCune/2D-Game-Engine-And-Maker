package oogasalad.editor.model.data.object.sprite;

import java.util.Map;

/**
 * Represents a raw sprite definition loaded from storage, unassociated with any specific editor object.
 * <p>
 * Once assigned to an object, this definition will be converted into runtime {@link SpriteData}.
 * </p>
 *
 * @author Jacob You
 */
public class SpriteTemplate {

  private String name;
  private String spritePath;
  private FrameData baseFrame;
  private Map<String, FrameData> frames;
  private Map<String, AnimationData> animations;

  /**
   * Constructs a new SpriteDefinition.
   *
   * @param name        the identifier for this sprite definition
   * @param spritePath  the file path to the sprite image
   * @param baseFrame   the primary frame data (often used as the default)
   * @param frames      a map of frame names to their corresponding {@link FrameData}
   * @param animations  a map of animation names to their corresponding {@link AnimationData}
   */
  public SpriteTemplate(
      String name,
      String spritePath,
      FrameData baseFrame,
      Map<String, FrameData> frames,
      Map<String, AnimationData> animations
  ) {
    this.name = name;
    this.spritePath = spritePath;
    this.baseFrame = baseFrame;
    this.frames = frames;
    this.animations = animations;
  }

  /**
   * Returns the name of this sprite definition.
   *
   * @return the sprite definition's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the file path to the sprite image.
   *
   * @return the sprite image path
   */
  public String getSpriteFile() {
    return spritePath;
  }

  /**
   * Returns the base frame data for this sprite.
   *
   * @return the {@link FrameData} used as the base frame
   */
  public FrameData getBaseFrame() {
    return baseFrame;
  }

  /**
   * Returns the map of all frame data entries.
   *
   * @return a map from frame names to {@link FrameData}
   */
  public Map<String, FrameData> getFrames() {
    return frames;
  }

  /**
   * Returns the map of all animation data entries.
   *
   * @return a map from animation names to {@link AnimationData}
   */
  public Map<String, AnimationData> getAnimations() {
    return animations;
  }

  /**
   * Sets the name of this sprite definition.
   *
   * @param name the new name to assign
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the file path to the sprite image.
   *
   * @param spritePath the new image path to assign
   */
  public void setSpritePath(String spritePath) {
    this.spritePath = spritePath;
  }

  /**
   * Sets the base frame data for this sprite.
   *
   * @param baseFrame the new {@link FrameData} to use as the base frame
   */
  public void setBaseFrame(FrameData baseFrame) {
    this.baseFrame = baseFrame;
  }

  /**
   * Replaces the current frame data map with a new one.
   *
   * @param frames a map from frame names to {@link FrameData}
   */
  public void setFrames(Map<String, FrameData> frames) {
    this.frames = frames;
  }

  /**
   * Replaces the current animation data map with a new one.
   *
   * @param animations a map from animation names to {@link AnimationData}
   */
  public void setAnimations(Map<String, AnimationData> animations) {
    this.animations = animations;
  }
}
