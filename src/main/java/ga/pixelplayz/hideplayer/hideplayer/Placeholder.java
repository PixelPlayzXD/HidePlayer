package ga.pixelplayz.hideplayer.hideplayer;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.hidden;
import static ga.pixelplayz.hideplayer.hideplayer.HidePlayer.shown;

public class Placeholder extends PlaceholderExpansion{
    private final HidePlayer HidePlayer;
    public Placeholder(HidePlayer HidePlayer){
        this.HidePlayer = HidePlayer;
    }
    @Override
    @NotNull
    public String getAuthor() {
        return "PixelPlayz";
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "hideplayer";
    }

    @Override
    @NotNull
    public String getVersion() {
        return "1.4.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String placeholder) {
        if(placeholder.equalsIgnoreCase("hidden")){
            return hidden.get(player.getUniqueId());
        }

        if(placeholder.equalsIgnoreCase("shown")){
            return shown.get(player.getUniqueId());
        }
        return null;
    }
}
