package me.trae.factions.framework;

import me.trae.factions.framework.enums.PluginProperty;
import me.trae.factions.framework.interfaces.ISpigotPlugin;
import me.trae.factions.utility.UtilFormat;
import me.trae.factions.utility.UtilMessage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public abstract class SpigotPlugin extends JavaPlugin implements ISpigotPlugin {

    private final LinkedHashMap<String, SpigotManager> MANAGERS = new LinkedHashMap<>();
    private final LinkedHashMap<PluginProperty, Object> PROPERTIES = new LinkedHashMap<>();

    @Override
    public LinkedHashMap<String, SpigotManager> getManagers() {
        return this.MANAGERS;
    }

    @Override
    public LinkedHashMap<PluginProperty, Object> getProperties() {
        return this.PROPERTIES;
    }

    @Override
    public void initializePlugin() {
        UtilMessage.log(this.getPluginName(), UtilFormat.toPairString("Plugin Status", ChatColor.GOLD + "Enabling"));

        this.getManagers().values().forEach(SpigotManager::registerModules);

        for (final SpigotManager manager : this.getManagers().values()) {
            manager.initializeFrame();

            String message = "No modules found.";
            if (manager.hasModules()) {
                message = "Showing " + ChatColor.YELLOW + manager.getModules().size() + ChatColor.GRAY + " Modules: [" + manager.getModules().values().stream().map(module -> ChatColor.GREEN + module.getName()).collect(Collectors.joining(ChatColor.GRAY + ", ")) + ChatColor.GRAY + "]";
            }

            UtilMessage.log(manager.getName(), "Enabled! " + message);
        }

        UtilMessage.log(this.getPluginName(), UtilFormat.toPairString("Plugin Status", ChatColor.GREEN + "Enabled"));
    }

    @Override
    public void shutdownPlugin() {
        UtilMessage.log(this.getPluginName(), UtilFormat.toPairString("Plugin Status", ChatColor.GOLD + "Disabling"));

        for (final SpigotManager manager : this.getManagers().values()) {
            manager.shutdownFrame();

            String message = "No modules found.";
            if (manager.hasModules()) {
                message = "Showing " + ChatColor.YELLOW + manager.getModules().size() + ChatColor.GRAY + " Modules: [" + manager.getModules().values().stream().map(module -> ChatColor.RED + module.getName()).collect(Collectors.joining(ChatColor.GRAY + ", ")) + ChatColor.GRAY + "]";
            }

            UtilMessage.log(manager.getName(), "Disabled! " + message);
        }

        UtilMessage.log(this.getPluginName(), UtilFormat.toPairString("Plugin Status", ChatColor.RED + "Disabled"));
    }

    @Override
    public void onEnable() {
        this.registerManagers();
        this.initializePlugin();
    }

    @Override
    public void onDisable() {
        this.shutdownPlugin();
    }
}