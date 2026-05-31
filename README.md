# Miss Indicator

A RuneLite external plugin that shows a customizable visual indicator whenever your attack
misses (hits a 0) on an NPC or player. Built on top of the official
[example-plugin](https://github.com/runelite/example-plugin) template.

---

## Running in development mode

Open the project in IntelliJ IDEA, then run the **`run`** Gradle task
(open `build.gradle` and click the green triangle next to `run`), or:

```bash
./gradlew run
```

This builds a fat-jar via `shadowJar` and launches RuneLite with the plugin loaded.
The plugin will appear as **Miss Indicator** in the Plugin Hub list.

---

## Configuration

| Section | Setting | Default | Description |
|---------|---------|---------|-------------|
| Display Settings | Miss text | `MISS` | Text shown on a miss |
| | Show on NPCs | ✅ | Toggle for NPC combat |
| | Show on players (PvP) | ✅ | Toggle for PvP |
| | Display mode | Above target | Above target / above player / screen center |
| | Duration (ticks) | 3 | How long the indicator stays on screen |
| Text Style | Font | *(RuneScape Bold)* | Leave blank for the default RS font |
| | Font style | Bold | Plain / Bold / Italic / Bold Italic |
| | Font size | 16 | 8–40 pt |
| Colors | Miss text color | Red | RGBA color picker |
| | Outline / shadow color | Dark semi-transparent | RGBA color picker |
| | Background | Shadow | None / Shadow / Outline / Box |
| Animation | Float direction | Up | Up / Down / Left / Right / None |
| | Float distance | 30 px | How far the text drifts |
| | Fade out | ✅ | Fades text in the second half of its lifetime |
| Sound | Play sound on miss | ❌ | Plays the in-game block sound |

---

## How miss detection works

The plugin subscribes to `HitsplatApplied`. A hitsplat is treated as a miss when **both**:

1. `hitsplat.isMine()` is true (the local player's own damage), **and**
2. `hitsplat.getHitsplatType() == HitsplatID.BLOCK_ME` **or** the amount is `0` on any
   "mine" type (covers edge cases such as certain special attacks).

---

## Submitting to the Plugin Hub

Follow the [Plugin Hub README](https://github.com/runelite/plugin-hub#creating-new-plugins):

1. Fork the plugin-hub repository.
2. Create a PR adding a manifest entry pointing at this repository.
3. Fill in `displayName`, `author`, `description`, `tags`, and `plugins` in
   `runelite-plugin.properties`.

---

## License

BSD 2-Clause
