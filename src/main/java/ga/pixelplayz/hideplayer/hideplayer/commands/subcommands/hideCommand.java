package ga.pixelplayz.hideplayer.hideplayer.commands.subcommands;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;
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
        String title = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.enabled.title"));
        String subtitle = ChatColor.translateAlternateColorCodes('&',HidePlayer.getConfig().getString("title.enabled.subtitle"));
        player.sendTitle(title,subtitle);
    }
}
