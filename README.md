# LocalGlobalChat

![Plugin Banner](https://img.shields.io/badge/Minecraft-1.21.4-blue?style=for-the-badge)

**LocalGlobalChat** is a lightweight Minecraft plugin that adds **local and global chat functionality** for specific worlds. Players can communicate in a configurable local radius or across the entire world using a simple prefix.

---

## Features

- 🌍 **World-specific chat** – the plugin only works in worlds listed in the config.
- 🗣️ **Local chat** – messages visible to players within a configurable radius.
- 💬 **Global chat** – prefix your message with `!` to broadcast it to the entire world.
- 🎨 **Custom formatting**:
    - Local chat: `[L] <player> -> message` (blue arrow)
    - Global chat: `[G] <player> -> message` (green arrow)
- 🔧 Fully configurable via `config.yml`.

---

## Installation

1. Download the latest `LocalGlobalChat.jar`.
2. Place it in your server's `plugins/` folder.
3. Start or restart your server.
4. A default `config.yml` will be generated in `plugins/LocalGlobalChat/`.

---

## Configuration (`config.yml`)

```yaml
worlds:
  world:
    radius: 250      # Local chat range in blocks
  mines:
    radius: 150      # Local chat range for another world
```
License

This project is licensed under the MIT License.
You can use, modify, and distribute it freely, even commercially, as long as the original copyright and license are retained.