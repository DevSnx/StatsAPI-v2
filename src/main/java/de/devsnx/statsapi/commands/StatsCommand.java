package de.devsnx.statsapi.commands;

import de.devsnx.statsapi.StatsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

/**
 * @author DevSnx
 * @since 25.07.2023
 */

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }




        return true;
    }

    private void sendStats(UUID uuid, Player player){
        if(player.getUniqueId().equals(uuid)){
            for(String message : StatsAPI.getPlugin(StatsAPI.class).getConfig().getStringList("message.stats_from_me")){
                message = message.replace("&", "§");



            }
        }else{
            for(String message : StatsAPI.getPlugin(StatsAPI.class).getConfig().getStringList("message.stats_from_other")){
                message = message.replace("&", "§");




            }
        }
    }
}
