package io.guthub.alexeymartynov.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiApi extends JavaPlugin {

    private static GuiApi instance;

    private GuiHandler guiHandler;

    @Override
    public void onEnable()
    {
        Bukkit.getLogger().severe("GuiAPI by bybyzyanka was enabled");
        Bukkit.getLogger().severe("Contact: https://vk.com/kai9595");

        instance = this;
        setupConfig();
        Gui.onEnable();
        guiHandler = new GuiHandler();
        Bukkit.getPluginManager().registerEvents(guiHandler, this);
    }

    @Override
    public void onDisable() {}

    public static GuiApi getInstance() { return instance; }

    private void setupConfig()
    {
        for(GuiContent content : GuiContent.values())
        {
            if(getConfig().getConfigurationSection(content.toString()) == null)
            {
                getConfig().set(content.toString() + ".id", "minecraft:stone");
                getConfig().set(content.toString() + ".name", "§e§l" + content.toString());
            }
        }

        saveConfig();
    }
}
