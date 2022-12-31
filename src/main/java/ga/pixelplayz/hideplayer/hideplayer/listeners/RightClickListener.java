package ga.pixelplayz.hideplayer.hideplayer.listeners;

import ga.pixelplayz.hideplayer.hideplayer.HidePlayer;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.hideCommand;
import ga.pixelplayz.hideplayer.hideplayer.commands.subcommands.showCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.hidden;

public class RightClickListener implements Listener {
    private final HidePlayer HidePlayer;

    hideCommand obj1;
    showCommand obj2;
    private final HashMap<UUID, Long> cooldown;
    private final HashMap<UUID, Long> cooldown2;
    public RightClickListener(HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
        this.cooldown = new HashMap<>();
        this.cooldown2 = new HashMap<>();
        obj1 = new hideCommand(HidePlayer);
        obj2 = new showCommand(HidePlayer);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRightClick (PlayerInteractEvent event) {
        if(HidePlayer.getConfig().getString("item.enabled").equalsIgnoreCase("true")){
            Player player = event.getPlayer();
            if(HidePlayer.getConfig().getString("cooldown.enabled").equals("true")){
                int Seconds = (HidePlayer.getConfig().getInt("cooldown.time"))/1000;
                if(!cooldown.containsKey(player.getUniqueId())){
                    cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                    runCommand(event);
                } else {
                    long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                    if(timeElapsed >= HidePlayer.getConfig().getLong("cooldown.time")){
                        cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                        runCommand(event);
                    } else {
                        ItemStack inHandItem = player.getInventory().getItemInHand();
                        if (inHandItem.equals(ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.RED+"Hidden "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"))) || inHandItem.equals(ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.GREEN+"Visible "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility")))) {
                            if (HidePlayer.getConfig().getString("cooldown.message.enabled").equalsIgnoreCase("true")) {
                                if(!cooldown2.containsKey(player.getUniqueId())){
                                    cooldown2.put(player.getUniqueId(),System.currentTimeMillis());
                                    sendCooldownMsg(player, timeElapsed);
                                } else {
                                    long timeElapsed2 = System.currentTimeMillis() - cooldown2.get(player.getUniqueId());
                                    if(timeElapsed2 >= 750){
                                        cooldown2.put(player.getUniqueId(),System.currentTimeMillis());
                                        sendCooldownMsg(player, timeElapsed);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                runCommand(event);
            }
        }
    }

    public void sendCooldownMsg (Player player, long timeElapsed) {
        double timeRemaining = (double)timeElapsed/1000;
        String msg = (HidePlayer.getConfig().getString("cooldown.message.message")).replace("{time}",Integer.toString((HidePlayer.getConfig().getInt("cooldown.time")/1000)-((int)Math.round(timeRemaining))));
        msg = ChatColor.translateAlternateColorCodes('&',msg);
        player.sendMessage(msg);
    }

    public void runCommand (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item_enabled = ItemMaker("on",(ChatColor.WHITE+"Players: "+ChatColor.GREEN+"Visible "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"));
        ItemStack item_disabled = ItemMaker("off",(ChatColor.WHITE+"Players: "+ChatColor.RED+"Hidden "+ChatColor.GRAY+"(Right Click)"), Collections.singletonList(ChatColor.GRAY + "Right-click to toggle player visibility"));
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            int slot = (HidePlayer.getConfig().getInt("item.item-slot"))-1;
            ItemStack inHandItem = event.getItem();
            if(inHandItem.equals(item_disabled)) {
                obj2.onShow(player, item_enabled, slot);
            } else if(inHandItem.equals(item_enabled)) {
                obj1.onHide(player, item_disabled, slot);
            }
        }
    }

    public ItemStack ItemMaker(String on_off, String itemName, List<String> loreList) {
        ItemStack ServerSelector;
        if (on_off.equals("on")) {
            ServerSelector = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        } else {
            ServerSelector = new ItemStack(Material.INK_SACK, 1, (byte) 8);
        }
        ItemMeta ServerSelectorMeta = ServerSelector.getItemMeta();
        ServerSelectorMeta.setDisplayName(itemName);
        ServerSelectorMeta.setLore(loreList);
        ServerSelector.setItemMeta(ServerSelectorMeta);
        return ServerSelector;
    }

}