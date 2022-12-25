package ga.pixelplayz.hideplayer.hideplayer;

import ga.pixelplayz.hideplayer.hideplayer.commands.hidePlayerCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.hideCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.reloadCommand;
import ga.pixelplayz.hideplayer.hideplayer.listeners.PlayerJoinListener;
import ga.pixelplayz.hideplayer.hideplayer.listeners.RightClickListener;
import ga.pixelplayz.hideplayer.hideplayer.miscellaneous.TabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HidePlayer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("§fHidePlayer >>> §aPlugin Has Been Enabled");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new RightClickListener(this),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        Bukkit.getPluginManager().registerEvents(new reloadCommand(this),this);
        Bukkit.getPluginManager().registerEvents(new hideCommand(this), this);
        getCommand("hideplayer").setExecutor(new hidePlayerCommand(this));
        getCommand("hideplayer").setTabCompleter(new TabCompletion());
    }

    @Override
    public void onDisable() {
        System.out.println("§fHidePlayer >>> §cPlugin Has Been Disabled");
    }
}
