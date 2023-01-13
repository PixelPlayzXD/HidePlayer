package ga.pixelplayz.hideplayer.hideplayer.listeners;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class ItemDropListener implements Listener {
    private final HidePlayer HidePlayer;
    RightClickListener obj;
    public ItemDropListener(HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
        obj = new RightClickListener(HidePlayer);
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        ItemStack item = event.getItemDrop().getItemStack();
        if(item.equals(obj.ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.GREEN+"Visible "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"))) || item.equals(obj.ItemMaker("off",(ChatColor.WHITE+"Players: "+ChatColor.RED+"Hidden "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility")))){
            if(event.getPlayer().hasPermission("hideplayer.dropitem")){
                event.setCancelled(true);
            }
        }
    }
}
