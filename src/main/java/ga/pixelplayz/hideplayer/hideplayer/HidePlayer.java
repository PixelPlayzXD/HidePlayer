package ga.pixelplayz.hideplayer.hideplayer;

import ga.pixelplayz.hideplayer.hideplayer.commands.hidePlayerCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.hideCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.reloadCommand;
import ga.pixelplayz.hideplayer.hideplayer.listeners.ItemDropListener;
import ga.pixelplayz.hideplayer.hideplayer.listeners.PlayerJoinListener;
import ga.pixelplayz.hideplayer.hideplayer.listeners.RightClickListener;
import ga.pixelplayz.hideplayer.hideplayer.miscellaneous.TabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class HidePlayer extends JavaPlugin implements Listener {
    public static HashMap<UUID, String> hidden = new HashMap<>();
    public static HashMap<UUID, String> shown = new HashMap<>();
    @Override
    public void onEnable() {
        this.getLogger().info(ChatColor.WHITE+"HidePlayer >>> "+ChatColor.GREEN+"Plugin Has Been Enabled");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(this).register();
        }
        Bukkit.getPluginManager().registerEvents(new RightClickListener(this),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(this), this);
        Bukkit.getPluginManager().registerEvents(new reloadCommand(this),this);
        Bukkit.getPluginManager().registerEvents(new hideCommand(this), this);
        getCommand("hideplayer").setExecutor(new hidePlayerCommand(this));
        getCommand("hideplayer").setTabCompleter(new TabCompletion());
    }

    @Override
    public void onDisable() {
        this.getLogger().info(ChatColor.WHITE+"HidePlayer >>> "+ChatColor.RED+"Plugin Has Been Disabled");
    }
}
