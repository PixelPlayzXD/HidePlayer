package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class helpCommand {
    public void onHelp(CommandSender sender){
        sender.sendMessage(ChatColor.GOLD+"hideplayer:\n   "+ChatColor.YELLOW+" help: "+ChatColor.WHITE+"display this menu\n    "+ChatColor.YELLOW+"hide: "+ChatColor.WHITE+"hide all players\n    "+ChatColor.YELLOW+"show: "+ChatColor.WHITE+"show all players\n    "+ChatColor.YELLOW+"reload: "+ChatColor.WHITE+"reload the plugin");
    }
}
