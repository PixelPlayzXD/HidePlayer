package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.hidden;
import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.shown;

public class hideCommand implements Listener {
    private final HidePlayer HidePlayer;
    public hideCommand(ga.pixelplayz.hideplayer.hideplayer.HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
        }
    public void onHide(Player player, ItemStack item, int slot){
        World world = player.getWorld();
        List<Player> players = world.getPlayers();
        for (Player notCommandRunner : players) {
            if (!notCommandRunner.equals(player)) {
                player.hidePlayer(notCommandRunner);
            }
        }
        if(HidePlayer.getConfig().getString("world.enabled").equalsIgnoreCase("true")){
            if(player.getWorld().getName().equals(HidePlayer.getConfig().getString("world.name"))){
                player.getInventory().setItem(slot, item);
            }
        }
        if(HidePlayer.getConfig().getString("title.enabled").equalsIgnoreCase("true")){
            String title = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.hidden.title"));
            String subtitle = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.hidden.subtitle"));
            player.sendTitle(title,subtitle);
        }
        hidden.put(player.getUniqueId(),"true");
        shown.put(player.getUniqueId(),"false");
    }
    public void onHideNoItem(Player player){
        World world = player.getWorld();
        List<Player> players = world.getPlayers();
        for (Player notCommandRunner : players) {
            if (!notCommandRunner.equals(player)) {
                player.hidePlayer(notCommandRunner);
            }
        }
        hidden.put(player.getUniqueId(),"true");
        shown.put(player.getUniqueId(),"false");
        if(HidePlayer.getConfig().getString("title.enabled").equalsIgnoreCase("true")){
            String title = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.hidden.title"));
            String subtitle = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.hidden.subtitle"));
            player.sendTitle(title,subtitle);
        }
    }
}
