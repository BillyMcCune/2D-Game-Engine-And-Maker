# Game Engine and Editor

## It's Thyme to Eat: SALAD

## Billy McCune, Alana Zinkin

This project implements an authoring environment and player for multiple related games.

### Timeline

* Start Date: 3/25/25
* Finish Date: 4/27/25
* Hours Spent: 25 hours/week across ~5 weeks

### Attributions

* Resources used for learning
  * [SOLID Design Principles](https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design#single-responsibility-principle)
    used to understand design principles
  * [Refactoring Guru](https://refactoring.guru/design-patterns/factory-method) used for
    understanding design patterns - specifically factory
  * [Undoing Git commits](https://stackoverflow.com/questions/22682870/how-can-i-undo-pushed-commits-using-git)

### Running the Program

* Main class: Main.java
* Data files needed:

  * Level Data: selected level/game file within the doc folder which is separated by game type
  * Resource Properties files (config, css, gameIcons, i18n, server, and shared folders)
* Interesting data files:

  * [Server.properties](src/main/resources/oogasalad/server/Server.properties)
    * Links the OOGASalad application to a WebSocket server address stored in this `properties` file for multiplayer support
* Key/Mouse inputs:

  * Key and Mouse inputs are entirely decided by the user within the level file, allowing for
    total flexibility

### Configuring OpenAI API Key

The chat assistant feature requires an OpenAI API key. You can configure it in one of the following
ways (in order of precedence):

1. **Environment Variable**: Set the `OPENAI_API_KEY` environment variable

   ```bash
   export OPENAI_API_KEY=your_key_here
   ```
2. **Java System Property**: Pass the API key as a system property

   ```bash
   java -DOPENAI_API_KEY=your_key_here -jar oogasalad.jar
   ```
3. **Config File**: Add your API key to `config.properties` in the resources directory

   ```properties
   OPENAI_API_KEY=your_key_here
   ```
4. **Dot Env File**: Create a `.env` file in the project root

   ```
   OPENAI_API_KEY=your_key_here
   ```

For security reasons, options #1 and #2 are recommended for production use.

* Features implemented:

  * Users can play a variety of scrolling platform games with a variety of different behaviors
  * The editor is able to save a basic game file and render it
  * Networked players - players may play in multi-player mode
  * Animations
  * AI ChatBot to assist with building the editor
  * Players have profiles that they can make and update where their high scores are saved for each
    game
  * Explicit software for making new sprite sheets - allows them to be parsed and generated within
    the editor to be used
  * Prefabs are interesting because they introduce a powerful concept of reusability into game development. Instead of building every game object from scratch, designers can create 'templates' with pre-set properties and behaviors. This not only speeds up level creation but also allows for dynamic, consistent changes – tweaking a prefab updates all its instances.

