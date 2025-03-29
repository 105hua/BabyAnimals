/* Licensed under GNU General Public License v3.0 */
package com.joshuadev.babyAnimals;

import com.joshuadev.babyAnimals.events.EntityDropItem;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import java.util.logging.Logger;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

public final class BabyAnimals extends JavaPlugin {

    public static final NamespacedKey HAS_GROWN_KEY = new NamespacedKey("babyanimals", "has_grown");
    public static Logger logger;
    private PaperCommandManager<CommandSourceStack> commandManager;
    private AnnotationParser<CommandSourceStack> annotationParser;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EntityDropItem(), this);
        logger = getLogger();
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
