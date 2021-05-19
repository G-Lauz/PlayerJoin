package fr.freebuild.playerjoin;

import me.glauz.pluginmessagemanager.api.PluginMessageReceiver;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.freebuild.playerjoin.firework.FireworkBuilder;
import lombok.Getter;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoin extends JavaPlugin {
  public static PlayerJoin plugin;
  private PluginMessageReceiver pluginMessageReceiver;
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

    try {
      this.pluginMessageReceiver = PluginMessageReceiver.getInstance();
      this.pluginMessageReceiver.initialize(this.plugin);
    } catch (Exception ioe) {
      getLogger().warning("The plugin will not communicate with others server as " +
                                "PluginMessageManager failed to initialize:");
      getLogger().warning(ioe.getMessage());
    }

    getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    this.saveResource("config.yml", false);
    this.fireworkBuilder.load();
  }
}