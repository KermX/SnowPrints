package me.kermx.snowprints;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class SnowPrints extends JavaPlugin {
    private static final Random random = new Random();
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("SnowPrints enabled successfully!");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers())
                    SnowPrints.this.playerHandler(player);
            }
        }, 10L, 5L);

    }

    private void playerHandler(Player player) {
        if (player.getGameMode() != GameMode.SURVIVAL || player.isSneaking())
            return;
        blockHandler(player.getLocation().getBlock(), player);
    }

    private void blockHandler(Block block, Player player) {
        if (block.getType() == Material.SNOW && player.isSprinting())
            block.setType(Material.AIR);
        if (block.getType() == Material.SNOW && random.nextInt(200) < 60)
            block.setType(Material.AIR);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SnowPrints disabled!");
    }
}
