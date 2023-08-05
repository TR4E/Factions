package me.trae.factions.framework.interfaces;

import me.trae.factions.framework.SpigotManager;

public interface ISpigotModule<M extends SpigotManager> {

    M getManager();
}