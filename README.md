# HidePlayer

Hide Other Players With The Click Of An Item On Your Server.

### Versions Supported

1.8 - Current

## Installation

Installation is very simple

- Drag And Drop The Plugin JAR File Inside Your **plugins** Folder

## Configuration
```yaml
world:
  enabled: "true" # Should the visibility toggle item be given to players in a specific world.
  name: "world" # Name of the world.
specific-rank: "false" #Should the join item only be given to people with the permission hideplayer.item
item:
  enabled: "true" # Should an item to toggle visibility be given to players.
  item-slot: 5  # Which slot should the visibility toggle item be given to player (1 - 9)
  droppable: "false" # Should players be able to drop the item
title:
  enabled: "true" # Should title's be shown to players when toggling visibility.
  hidden:
    title: "&cPLAYERS HIDDEN" # Title with color code to be shown when visibility of toggled to hidden.
    subtitle: "&7Players are now invisible!" # Subtitle with color code to be shown when visibility of toggled to hidden.
  shown:
    title: "&aPLAYERS SHOWN" # Title with color code to be shown when visibility of toggled to show.
    subtitle: "&7Players are now visible!" # Subtitle with color code to be shown when visibility of toggled to show.
cooldown:
  enabled: "true" # Cooldown between toggling visibility through right-click of item.
  time: 3000 # Cooldown time in milliseconds (3000 Milliseconds = 3 Seconds)
  message:
    enabled: "false"  # Should there be a message when cooldown is not over.
    message: "&cYou must wait &e{time}s &cbetween use!" # Color coded message. Use {time} for getting remaining time in seconds
```
## License

[MIT](https://choosealicense.com/licenses/mit/)
