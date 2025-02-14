package mintychochip.ollivanders.api;

import mintychochip.ollivanders.Ollivanders;
import mintychochip.ollivanders.container.Context;
import mintychochip.ollivanders.container.Spell;
import mintychochip.ollivanders.container.SpellMechanic;
import mintychochip.ollivanders.handler.CooldownManager;
import mintychochip.ollivanders.handler.ExperienceHandler;
import mintychochip.ollivanders.items.container.WandData;
import mintychochip.ollivanders.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SpellCastEvent extends OllivandersEvent {
    private Spell spell;
    private Context context;
    private WandData wandData;
    private final Player player;

    public SpellCastEvent(Spell spell, WandData wandData, Context context, CooldownManager cooldownManager) {
        this.spell = spell;
        this.context = context;
        this.wandData = wandData;
        player = context.getPlayer();
        SpellMechanic mechanic = spell.getMechanic();
        if (!Permissions.costBypass(player)) {
            int cost = Math.round((float) (spellCostFactor(player) * mechanic.getMechanicSettings().getCost()));
            ExperienceHandler.reducePlayerLevel(cost, player);
            Bukkit.broadcastMessage(cost + "");
        }
        cooldownManager.addCooldown(mechanic.getName() + "-" + mechanic.getShape(), player.getUniqueId());
    }

    public double spellCostFactor(Player sender) {
        double factor = 1;
        if (Permissions.quarterOfCost(sender)) {
            return 0.25;
        }
        if (Permissions.oneThirdOfCost(sender)) {
            return 0.33;
        }
        if (Permissions.halfOfCost(sender)) {
            return 0.5;
        }
        return factor;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Player getPlayer() {
        return player;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public WandData getWandData() {
        return wandData;
    }

    public void setWandData(WandData wandData) {
        this.wandData = wandData;
    }

}
