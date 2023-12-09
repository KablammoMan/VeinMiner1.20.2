# VeinMiner 1.20.2
A customisable Minecraft SpigotMC/Paper/Purpur VeinMiner plugin that I developed.
# Features
- The plugin stores data in the remote location of `plugins/kablammoman/veinminer/` to guarantee that no conflicts between data of other plugins whilst maintaining persistent storage over server restarts.
- The plugin checks what tool the player has in their hand and applies the effects of that tool when breaking blocks (e.g. fortune will make ores drop more, using the wrong tool will result in 0 drops)
# Commands
- Customise what blocks are treated as "vein-minerable" with `/toggleveintype <block_types>` (separate with spaces)
- Customise the maximum distance between the source block and others that will break if they're the same type with `/setbreakrange <range>`
- Reset the configuration to the defaults with `/resetveinminer`
- Toggle on or off the functionality of the plugin (still allows for plugin commands) with `/toggleveinminer`
All of the above commands require operator status on the server to use.
