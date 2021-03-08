package com.github.charlyb01.music_control.client;

import com.github.charlyb01.music_control.categories.MusicCategories;
import com.github.charlyb01.music_control.categories.MusicCategory;
import com.github.charlyb01.music_control.config.ModConfig;
import com.github.charlyb01.music_control.event.SoundEvents;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class MusicControlClient implements ClientModInitializer {
    public static boolean init = false;

    public static boolean skip = false;
    public static boolean pause = false;
    public static boolean isPaused = false;
    public static boolean category = false;
    public static boolean random = false;
    public static boolean print = false;
    public static MusicCategory currentCategory = MusicCategory.GAME;
    public static String currentModCategory;

    private static KeyBinding skipMusic;
    private static KeyBinding pauseResume;
    private static KeyBinding changeCat;
    private static KeyBinding randomMusic;
    private static KeyBinding printMusic;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        SoundEvents.SOUNDS_LOADED.register(((soundManager) -> MusicCategories.init(MinecraftClient.getInstance())));

        skipMusic = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.music_control.skip",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT,
                "category.music_control.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (skipMusic.wasPressed()) {
                skip = true;
            }
        });

        pauseResume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.music_control.pause",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.music_control.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (pauseResume.wasPressed()) {
                pause = true;
            }
        });

        changeCat = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.music_control.category",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                "category.music_control.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (changeCat.wasPressed()) {
                if ((client.player != null && client.player.isCreative()) || ModConfig.get().cheat) {
                    category = true;
                }
            }
        });

        randomMusic = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.music_control.random",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DOWN,
                "category.music_control.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (randomMusic.wasPressed()) {
                if ((client.player != null && client.player.isCreative()) || ModConfig.get().cheat) {
                    random = true;
                }
            }
        });

        printMusic = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.music_control.print",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_CONTROL,
                "category.music_control.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (printMusic.wasPressed()) {
                print = true;
            }
        });
    }
}
