# WorldPlaceholder

This plugin outputs the information about the player's location in the dimension through Placeholder in the Tab or game chat.

- World - green color
- World_nether - red color
- World_end - purple color
Placeholder:
```
%worldplaceholder%
```

```
messages:
  noPermission: "&#dc143cYou do not have sufficient permissions to perform this action!"
  reloaded: "&#00ff7fPlugin successfully reloaded"

# It will look like &#c0c0c0◆
worldSymbol: "◆"
# Default color, used for worlds not specified below
defaultColor: "&#c0c0c0"

# You can use both HEX colors in the format &#abcdef and regular colors using &a
worlds:
  world: "&#00ff7f"
  world_nether: "&#dc143c"
  world_the_end: "&#8a2be2"
```
In the config.yml, you can change the colors as well as the symbol.
