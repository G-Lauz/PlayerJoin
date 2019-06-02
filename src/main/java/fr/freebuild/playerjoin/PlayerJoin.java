package fr.freebuild.playerjoin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.freebuild.playerjoin.firework.FireworkBuilder;
import lombok.Getter;

public class PlayerJoin extends JavaPlugin {
  public static PlayerJoin plugin;
  @Getter
  private FireworkBuilder fireworkBuilder;

  public PlayerJoin() {
    PlayerJoin.plugin = this;
    this.fireworkBuilder = new FireworkBuilder();
  }


  /**
   * Called when the plugin is enabling
   */
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    this.saveResource("config.yml", false);
    this.fireworkBuilder.load();
  }
}