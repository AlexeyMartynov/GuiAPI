package io.github.alexeymartynov.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GuiApi {

    private GuiHandler guiHandler;
    private File file;
    private FileConfiguration config;

    private static GuiApi instance;

    public void setup(JavaPlugin plugin)
    {
        if(plugin == null)
            return;

        File folder = new File(plugin.getDataFolder() + File.separator + "GuiAPI");
        if (!folder.exists())
            folder.mkdir();

        file = new File(folder + File.separator + "config.yml");
        if (!file.exists())
        {
            try { file.createNewFile(); }
            catch (Exception exception) {}
        }

        config = YamlConfiguration.loadConfiguration(file);

        Bukkit.getLogger().severe("GuiAPI by bybyzyanka was enabled");
        Bukkit.getLogger().severe("Contact: https://vk.com/kai9595");

        instance = this;
        setupConfig();
        Gui.onEnable(plugin);
        guiHandler = new GuiHandler();
        Bukkit.getPluginManager().registerEvents(guiHandler, plugin);
    }

    public static GuiApi getInstance() { return instance; }

    public FileConfiguration getConfig() { return config; }

    public void saveConfig()
    {
        try { config.save(file); }
        catch(Exception exception) {}
    }

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
