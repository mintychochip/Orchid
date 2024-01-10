package mintychochip.ollivanders.wand.container;

import mintychochip.genesis.config.GenesisConfigurationSection;
import mintychochip.ollivanders.util.EnumUtil;
import mintychochip.ollivanders.wand.enums.WandModifier;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ComponentConfigurationSection extends GenesisConfigurationSection {
    protected Material defaultMaterial;

    protected final String itemPath;
    public ComponentConfigurationSection(ConfigurationSection configurationSection, String itemPath) {
        super(configurationSection, itemPath);
        this.itemPath = itemPath;
    }
    public Material getDefaultMaterial() {
        return defaultMaterial;
    }
    public String getItemPath() {
        return itemPath;
    }
    public WandBoost getDefaultWandBoost(String marker) {
        WandBoost wandBoost = new WandBoost();
        ConfigurationSection modifiers = main.getConfigurationSection(marker);
        if (modifiers != null) {
            for (String key : modifiers.getKeys(false)) {
                if (EnumUtil.isInEnum(WandModifier.class, key.toUpperCase())) {
                    switch (Enum.valueOf(WandModifier.class, key.toUpperCase())) {
                        case RANGE -> wandBoost.setRange(wandBoost.getRange() + modifiers.getDouble(key));
                        case EFFICIENCY -> wandBoost.setEfficiency(wandBoost.getEfficiency() + modifiers.getDouble(key));
                        case DURATION -> wandBoost.setDuration(wandBoost.getDuration() + modifiers.getDouble(key));
                        case MAGNITUDE -> wandBoost.setMagnitude(wandBoost.getMagnitude() + modifiers.getDouble(key));
                        case HASTE -> wandBoost.setHaste(wandBoost.getHaste() + modifiers.getDouble(key));
                    }
                }
            }
        }
        return wandBoost;
    }
}