package me.trae.factions.event.interfaces;

import me.trae.factions.utility.UtilJava;
import org.bukkit.command.CommandSender;

public interface ISenderEvent {

    CommandSender getSender();

    default <T extends CommandSender> T getSenderCasted(final Class<T> clazz) {
        return UtilJava.cast(clazz, this.getSender());
    }
}