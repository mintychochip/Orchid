package mintychochip.ollivanders.spells;

import mintychochip.ollivanders.container.SpellMechanic;
import mintychochip.ollivanders.spells.shape.SpellArea;
import mintychochip.ollivanders.spells.shape.SpellSelf;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Blink extends SpellMechanic implements SpellArea, SpellSelf {
    @Override
    public boolean castArea() {
        Player player = context.getPlayer();
        if(context.getHitLocation() == null) {
            return false;
        }
        double v = player.getLocation().distance(getCastLocation());
        if(v > getEffectiveRange()) {
            return false;
        }
        player.teleport(setYawPitch(getCastLocation(),player));
        return true;
    }

    @Override
    public boolean castSelf() {
        Player player = context.getPlayer();

        Block targetBlockExact = player.getTargetBlockExact((int) Math.ceil(getEffectiveRange()));
        if(targetBlockExact == null) {
            return false;
        }
        player.teleport(setYawPitch(targetBlockExact.getLocation(),player));
        return true;
    }

    public Location setYawPitch(Location location, Player player) {
        location.setPitch(player.getLocation().getPitch());
        location.setYaw(player.getLocation().getYaw());
        return location;
    }
}