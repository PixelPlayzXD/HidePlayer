package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.*;

public class showCommand implements Listener {
    private final HidePlayer HidePlayer;
    public showCommand(HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
    }
    public void onShow(Player player, ItemStack item, int slot){
        World world = player.getWorld();
        List<Player> players = world.getPlayers();
        for (Player notCommandRunner : players) {
            if (!notCommandRunner.equals(player)) {
                player.showPlayer(notCommandRunner);
            }
        }
        if(HidePlayer.getConfig().getString("item.enabled").equalsIgnoreCase("true")){
            if(HidePlayer.getConfig().getString("world.enabled").equalsIgnoreCase("true")){
                if(player.getWorld().getName().equals(HidePlayer.getConfig().getString("world.name"))){
                    player.getInventory().setItem(slot, item);
                }
            }
        }
        if(HidePlayer.getConfig().getString("title.enabled").equalsIgnoreCase("true")){
            String title = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.shown.title"));
            String subtitle = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.shown.subtitle"));
            player.sendTitle(title,subtitle);
        }
        hidden.put(player.getUniqueId(),"false");
        shown.put(player.getUniqueId(),"true");
    }
    public void onShowNoItem(Player player){
        World world = player.getWorld();
        List<Player> players = world.getPlayers();
        for (Player notCommandRunner : players) {
            if (!notCommandRunner.equals(player)) {
                player.showPlayer(notCommandRunner);
            }
        }
        hidden.put(player.getUniqueId(),"false");
        shown.put(player.getUniqueId(),"true");
        if(HidePlayer.getConfig().getString("title.enabled").equalsIgnoreCase("true")){
            String title = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.shown.title"));
            String subtitle = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.shown.subtitle"));
            player.sendTitle(title,subtitle);
        }
    }
}