# EasierMobCloning 🧬

**EasierMobCloning** is a lightweight, client-side Fabric mod for **Minecraft 1.21.11** that lets you clone any mob—including full server-side NBT data like Villager trade offers, health, custom names, and equipment—directly into a Spawn Egg.

---

## ✨ Features

* **Full NBT Capture:** Clones complete server-side NBT data (including Villager `Offers`, level, EXP, equipment, and custom attributes).
* **Intuitive Controls:** Hold `Ctrl` and **Middle-Click (Pick Block)** on any mob in Creative mode.
* **100% Client-Side:** Works on servers without requiring any server-side mod installation (requires OP permissions on the server to query NBT).
* **1.21.11 Ready:** Built natively using Minecraft 1.21.11's `minecraft:entity_data` Data Component system and native `DataQueryHandler`.
* **Clean Spawning:** Automatically strips unique positional (`Pos`, `Motion`, `Rotation`) and identity (`UUID`) tags to prevent world spawning bugs or duplicates.

---

## 🎮 How to Use

1. Ensure you are in **Creative Mode** and have **OP/Admin permissions** on the server/world.
2. Crosshair target any mob (e.g., a Villager with custom or locked trades).
3. Hold **`Left Ctrl`** (or `Right Ctrl`) and press **`Pick Block`** (Middle Mouse Click).
4. A Spawn Egg containing the exact replica of the entity will instantly be placed into your active hotbar slot.
5. Right-click the ground to spawn an exact duplicate!

---

## 📋 Requirements

* **Minecraft:** `1.21.11`
* **Fabric Loader:** `>=0.15.0`
* **Java Development Kit:** `Java 21`

---

## 🛠️ Building from Source

```bash
git clone [https://github.com/YourUsername/EasierMobCloning.git](https://github.com/YourUsername/EasierMobCloning.git)
cd EasierMobCloning
./gradlew build