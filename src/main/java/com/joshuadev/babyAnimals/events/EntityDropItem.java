/* Licensed under GNU General Public License v3.0 */
package com.joshuadev.babyAnimals.events;

import com.joshuadev.babyAnimals.BabyAnimals;
import org.bukkit.entity.Turtle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EntityDropItem implements Listener {
    @EventHandler
    public void onEntityBreed(EntityDropItemEvent event) {
        if (!(event.getEntity() instanceof Turtle turtle)) return;
        PersistentDataContainer turtleContainer = turtle.getPersistentDataContainer();
        if (turtleContainer.has(BabyAnimals.HAS_GROWN_KEY, PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
}
