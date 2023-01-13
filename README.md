# PixelPlayz Custom Item

Hide Other Players With The Click Of An Item On Your Server.

### Versions Supported

1.8 - Current

## Installation

Installation is very simple

- Drag And Drop The Plugin JAR File Inside Your **plugins** Folder

## Configuration
```white
world:
  enabled: "true" # Should the visibility toggle item be given to players in a specific world.
  name: "world" # Name of the world.
inventory:
  item-slot: 5  # Which slot should the visibility toggle item be given to player (1 - 9)
title:
  enabled:
    title: "&cPLAYERS HIDDEN" # Title with color code to be shown when visibility of toggled to hidden.
    subtitle: "&7Players are now invisible!" # Subtitle with color code to be shown when visibility of toggled to hidden.
  disabled:
    title: "&aPLAYERS SHOWN" # Title with color code to be shown when visibility of toggled to show.
    subtitle: "&7Players are now visible!" # Subtitle with color code to be shown when visibility of toggled to show.
cooldown:
  enabled: "true" # Cooldown between toggling visibility through right-click of item.
  time: 3000 # Cooldown time in milliseconds (3000 Milliseconds = 3 Seconds)
```
## License

[MIT](https://choosealicense.com/licenses/mit/)