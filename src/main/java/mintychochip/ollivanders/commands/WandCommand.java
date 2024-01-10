package mintychochip.ollivanders.commands;

import mintychochip.genesis.config.GenesisConfigurationSection;
import mintychochip.genesis.container.AbstractItem;
import mintychochip.ollivanders.Ollivanders;
import mintychochip.ollivanders.wand.builder.ComponentBuilder;
import mintychochip.ollivanders.wand.builder.WandBuilder;
import mintychochip.ollivanders.wand.container.ComponentConfigurationSection;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            List<ItemStack> materials = new ArrayList<>();
            materials.add(new ItemStack(Material.STICK));
            materials.add(new ItemStack(Material.EMERALD));
            materials.add(new ItemStack(Material.STRING));
            ComponentConfigurationSection lens = Ollivanders.getComponentConfig().getMainConfigurationSection("lens", false);
            materials.add(new ComponentBuilder(new AbstractItem(Ollivanders.getInstance(),lens.getDefaultMaterial()), "CLASSIC",lens).defaultBuild());
            boolean b = Boolean.parseBoolean(strings[1]);

            ComponentConfigurationSection core = Ollivanders.getComponentConfig().getMainConfigurationSection(strings[0], b);
            materials.add(new ComponentBuilder(new AbstractItem(Ollivanders.getInstance(),core.getDefaultMaterial()),"CLASSIC",core).defaultBuild());

            WandBuilder wandBuilder;
            GenesisConfigurationSection wand = Ollivanders.getWandConfig().getMainConfigurationSection(strings[0].toUpperCase());
            try {
                wandBuilder = new WandBuilder(new AbstractItem(Ollivanders.getInstance(), Material.BLAZE_ROD),"CLASSIC",wand,materials);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.getInventory().addItem(wandBuilder.defaultBuild());
        }
        return false;
    }
}