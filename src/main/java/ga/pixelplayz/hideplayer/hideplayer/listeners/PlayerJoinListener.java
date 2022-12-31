package ga.pixelplayz.hideplayer.hideplayer.listeners;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


import java.util.Collections;

import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.hidden;
import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.shown;

public class PlayerJoinListener implements Listener {
    private final HidePlayer HidePlayer;
    RightClickListener obj;
    public PlayerJoinListener(HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
        obj = new RightClickListener(HidePlayer);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        hidden.put(player.getUniqueId(), "false");
        shown.put(player.getUniqueId(), "true");
        if(HidePlayer.getConfig().getString("item.enabled").equalsIgnoreCase("true")){
            if(HidePlayer.getConfig().getString("world.enabled").equalsIgnoreCase("true")){
                if((player.getWorld().getName()).equals(HidePlayer.getConfig().getString("world.name"))){
                    giveItem(player);
                }
            } else {
                giveItem(player);
            }
        }
    }

    public void giveItem(Player player){
        ItemStack item_enabled = obj.ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.GREEN+"Visible "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"));
        int slot = (HidePlayer.getConfig().getInt("item.item-slot"))-1;
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HidePlayer, new Runnable() {
            public void run() {
                player.getInventory().setItem(slot, item_enabled);
            }
        }, 20);
    }
}
