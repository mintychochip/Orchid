package mintychochip.ollivanders.events;

import Shape;
import mintychochip.ollivanders.container.WizardMechanic;
import mintychochip.ollivanders.container.WizardSpell;
import org.bukkit.event.HandlerList;

public class LaserCastEvent extends WizardEvent {

    public LaserCastEvent(Shape shape, WizardMechanic mechanic, WizardSpell spell) {
        super(shape, mechanic, spell);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
