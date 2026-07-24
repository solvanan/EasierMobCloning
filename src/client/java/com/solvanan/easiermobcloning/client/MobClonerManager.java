package com.solvanan.easiermobcloning.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.Text;

public class MobClonerManager {

    public static void cloneEntity(MinecraftClient client, Entity entity) {
        if (client.player == null || client.getNetworkHandler() == null) return;

        // Native Minecraft DataQueryHandler for full server NBT
        client.getNetworkHandler().getDataQueryHandler().queryEntityNbt(entity.getId(), (NbtCompound nbt) -> {
            // Null safety re-check inside async callback to resolve IDE warnings
            if (client.player == null || client.getNetworkHandler() == null) return;

            if (nbt == null || nbt.isEmpty()) {
                client.player.sendMessage(
                        Text.literal("§c[EasierMobCloning] Could not retrieve entity NBT from server."),
                        false
                );
                return;
            }

            Item spawnEggItem = SpawnEggItem.forEntity(entity.getType());
            if (spawnEggItem == null) {
                client.player.sendMessage(
                        Text.literal("§c[EasierMobCloning] No spawn egg found for " + entity.getType().getName().getString()),
                        false
                );
                return;
            }

            ItemStack eggStack = new ItemStack(spawnEggItem);

            // Strip positional and unique identity tags to prevent duplicate world conflicts
            nbt.remove("UUID");
            nbt.remove("Pos");
            nbt.remove("Motion");
            nbt.remove("Rotation");
            nbt.remove("Dimension");

            // Apply 1.21.11 Data Component
            eggStack.set(DataComponentTypes.ENTITY_DATA, TypedEntityData.create(entity.getType(), nbt));

            if (entity.hasCustomName()) {
                eggStack.set(DataComponentTypes.CUSTOM_NAME, entity.getCustomName());
            }

            // Put stack into currently active hotbar slot and sync with server
            int selectedSlot = client.player.getInventory().getSelectedSlot();
            int packetSlot = 36 + selectedSlot;

            client.player.getInventory().setStack(selectedSlot, eggStack);
            client.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(packetSlot, eggStack));

            client.player.sendMessage(
                    Text.literal("§a[EasierMobCloning] Cloned §f" + entity.getName().getString() + " §awith full NBT!"),
                    false
            );
        });
    }
}