/* Licensed under GNU General Public License v3.0 */
package com.joshuadev.babyAnimals.commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;
import org.incendo.cloud.annotations.processing.CommandContainer;

@CommandContainer
public class ToggleBaby {

    @CommandDescription("Toggle whether an animal is a baby or not.")
    @Command("togglebaby|tb")
    @Permission("babyanimals.togglebaby")
    @SuppressWarnings("unused")
    public void toggleBabyCommand(CommandSourceStack sourceStack) {
        // Ensure player is sending command.
        CommandSender sender = sourceStack.getSender();
        if (!(sender instanceof Player player)) {
            TextComponent notPlayerComponent =
                    Component.text("You must be a player to use this command.");
            sender.sendMessage(notPlayerComponent);
            return;
        }
        // Get and validate target animal.
        Entity targetEntity = player.getTargetEntity(5, false);
        if (!(targetEntity instanceof Animals animal)) {
            sender.sendMessage(
                    Component.text(
                            "You must be looking at an animal to toggle its baby status.",
                            NamedTextColor.RED));
            return;
        }
        // Set baby or adult depending on age, 0 is adult, anything under that is a baby.
        if (animal.getAge() == 0) {
            animal.setAge(-100);
            animal.setAgeLock(true);
        } else {
            animal.setAge(0);
            animal.setAgeLock(false);
        }
        player.sendMessage(
                Component.text(
                        "The age of your animal has successfully been toggled!",
                        NamedTextColor.GREEN));
    }
}
