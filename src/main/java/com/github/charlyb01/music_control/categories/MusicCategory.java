package com.github.charlyb01.music_control.categories;

import net.minecraft.util.Identifier;

import java.util.Map;

public enum MusicCategory {
    GAME (MusicCategories.GAME),
    NETHER (MusicCategories.NETHER),
    END (MusicCategories.END),
    DISC (MusicCategories.DISC),
    MODS (MusicCategories.MODS),
    ALL (MusicCategories.ALL);

    public final Map<Identifier, Integer> musics;

    MusicCategory(final Map<Identifier, Integer> map) {
        this.musics = map;
    }
}
