package fr.freebuild.playerjoin.utils;

import org.bukkit.ChatColor;

import fr.freebuild.playerjoin.PlayerJoin;
import lombok.experimental.UtilityClass;

/**
 * Global Utility Class
 */
@UtilityClass
public class Utils {

  /**
   * Change String too support color
   * 
   * @param toChange String to convert colors
   * @return
   */
  public String toColor(String toChange) {
    return ChatColor.translateAlternateColorCodes('&', toChange);
  }

  /**
   * Get config string variable into colors
   * 
   * @param path Path to config
   * @return
   */
  public String getConfigString(String path) {
    return Utils.toColor(PlayerJoin.plugin.getConfig().getString(path));
  }

  /**
   * Replace a format parameter inside a message
   * 
   * @param message Message
   * @param param Key of parameter to replace
   * @param value Value of parameter to replace
   * @return Message modified
   */
  public String format(String message, FormatParam param, String value) {
    return message.replace(param.getValue(), value);
  }

  /**
   * Increase the counter and save config file
   * 
   * @param path Path to counter
   * @return Return counter increased of 1
   */
  public Integer increaseCounter(String path) {
    Integer counter = PlayerJoin.plugin.getConfig().getInt(path, 0);
    counter += 1;
    PlayerJoin.plugin.getConfig().set(path, counter);
    PlayerJoin.plugin.saveConfig();
    return counter;
  }
}
