package me.trae.factions.framework;

import me.trae.factions.command.CommandManager;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.framework.interfaces.ISpigotModule;
import me.trae.factions.utility.UtilJava;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class SpigotModule<M extends SpigotManager> extends Frame implements ISpigotModule<M> {

    private final M manager;

    public SpigotModule(final M manager) {
        super(manager.getInstance());

        this.manager = manager;
    }

    @Override
    public M getManager() {
        return this.manager;
    }

    @Override
    public void initializeFrame() {
        if (this instanceof Listener) {
            Bukkit.getServer().getPluginManager().registerEvents(UtilJava.cast(Listener.class, this), this.getInstance());
        }

        if (this instanceof AbstractCommand<?, ?>) {
            this.getInstance().getManagerByClass(CommandManager.class).addCommand(UtilJava.cast(AbstractCommand.class, this));
        }

        super.initializeFrame();
    }

    @Override
    public void shutdownFrame() {
        if (this instanceof Listener) {
            HandlerList.unregisterAll(UtilJava.cast(Listener.class, this));
        }

        if (this instanceof AbstractCommand<?, ?>) {
            this.getInstance().getManagerByClass(CommandManager.class).removeCommand(UtilJava.cast(AbstractCommand.class, this));
        }

        super.shutdownFrame();
    }
}