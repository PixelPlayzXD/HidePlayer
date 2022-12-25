package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class reloadCommand implements Listener {

    private final HidePlayer HidePlayer;
    public reloadCommand(ga.pixelplayz.hideplayer.hideplayer.HidePlayer HidePlayer) {
        this.HidePlayer = HidePlayer;
    }

    public void onReload(CommandSender sender) {
        HidePlayer.reloadConfig();
        sender.sendMessage(ChatColor.WHITE+""+ ChatColor.BOLD+"HidePlayer >>> "+ChatColor.GREEN+"Config Reloaded");
    }
}