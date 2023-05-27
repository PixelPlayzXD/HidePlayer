package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class helpCommand {
    public void onHelp(CommandSender sender){
        sender.sendMessage(ChatColor.DARK_GRAY+"------------[--: "+ChatColor.GREEN+"HidePlayer "+ChatColor.DARK_GRAY+":--]------------\n   "+ChatColor.YELLOW+"/help "+ChatColor.GRAY+"- Display This Menu\n    "+ChatColor.YELLOW+"/hide "+ChatColor.GRAY+"- Hide All Players\n    "+ChatColor.YELLOW+"/show "+ChatColor.GRAY+"- Show All Players\n    "+ChatColor.YELLOW+"/reload "+ChatColor.GRAY+"- Reload The Plugin\n "+ChatColor.DARK_GRAY+"--------------------------------------------");
    }
}
