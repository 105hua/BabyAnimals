/* Licensed under GNU General Public License v3.0 */
package com.joshuadev.babyAnimals;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

public final class BabyAnimals extends JavaPlugin {

    private PaperCommandManager<CommandSourceStack> commandManager;
    private AnnotationParser<CommandSourceStack> annotationParser;

    @Override
    public void onEnable() {
        // Plugin startup logic
        commandManager =
                PaperCommandManager.builder()
                        .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
                        .buildOnEnable(this);
        annotationParser = new AnnotationParser<>(commandManager, CommandSourceStack.class);
        try {
            annotationParser.parseContainers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
