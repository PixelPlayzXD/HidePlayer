package ga.pixelplayz.hideplayer.hideplayer.commands;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.helpCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.hideCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.reloadCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.showCommand;
import ga.pixelplayz.hideplayer.hideplayer.listeners.RightClickListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.omg.CORBA.COMM_FAILURE;

import java.util.Collections;

public class hidePlayerCommand implements CommandExecutor {
    private final HidePlayer HidePlayer;
    helpCommand obj1;
    reloadCommand obj2;
    hideCommand obj3;
    showCommand obj4;
    RightClickListener obj5;

    public hidePlayerCommand(HidePlayer HidePlayer) {
        this.HidePlayer = HidePlayer;
        obj1 = new helpCommand();
        obj2 = new reloadCommand(HidePlayer);
        obj3 = new hideCommand(HidePlayer);
        obj4 = new showCommand(HidePlayer);
        obj5 = new RightClickListener(HidePlayer);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            isPlayer(args, sender);
        } else {
            notPlayer(args, sender);
        }
        return true;

    }

    public void notPlayer(String[] args, CommandSender sender){
        if(args.length == 0) {
            obj1.onHelp(sender);
        } else if (args[0].equalsIgnoreCase("reload")){
            obj2.onReload(sender);
        } else if (args[0].equalsIgnoreCase("hide")) {
            sender.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"HidePlayer >>> "+ChatColor.RED+"Please run the command as a player.");
        } else if (args[0].equalsIgnoreCase("show")) {
            sender.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"HidePlayer >>> "+ChatColor.RED+"Please run the command as a player.");
        } else if (args[0].equalsIgnoreCase("help")){
            obj1.onHelp(sender);
        }
    }

    public void isPlayer(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.hasPermission("hideplayer")) {
                obj1.onHelp(sender);
            }
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission("hideplayer.reload")) {
                obj2.onReload(sender);
            }
        } else if (args[0].equalsIgnoreCase("hide")) {
            if (player.hasPermission("hideplayer.hide")){
                int slot = (HidePlayer.getConfig().getInt("inventory.item-slot"))-1;
                ItemStack item = obj5.ItemMaker("off",(ChatColor.WHITE+"Players: "+ChatColor.RED+"Hidden "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"));
                obj3.onHide(player, item, slot);
            }
        } else if (args[0].equalsIgnoreCase("show")) {
            if (player.hasPermission("hideplayer.show")){
                int slot = (HidePlayer.getConfig().getInt("inventory.item-slot"))-1;
                ItemStack item = obj5.ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.GREEN+"Visible "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"));
                obj4.onShow(player, item, slot);
            }
        } else if (args[0].equalsIgnoreCase("help")) {
            if (player.hasPermission("hideplayer.show")){
                obj1.onHelp(sender);
            }
        }
    }


}
