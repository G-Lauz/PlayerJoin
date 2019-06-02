package fr.freebuild.playerjoin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.freebuild.playerjoin.utils.FormatParam;
import fr.freebuild.playerjoin.utils.Utils;

public class PlayerJoinListener implements Listener {

  /**
   * Called when player join
   * Overridade default message
   * 
   * @param event
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    String message;
    if (!event.getPlayer().hasPlayedBefore()) {
      message = Utils.getConfigString("FirstJoinMessage");

      final Integer counter = Utils.increaseCounter("PlayerCounter");
      message = Utils.format(message, FormatParam.COUNTER, counter.toString());

      if (PlayerJoin.plugin.getFireworkBuilder().getActivateOnJoin()) {
        PlayerJoin.plugin.getFireworkBuilder().spawn(event.getPlayer());
      }
    } else {
      message = Utils.getConfigString("JoinMessage");
    }
    message = Utils.format(message, FormatParam.PLAYER, event.getPlayer().getDisplayName());
    event.setJoinMessage(message);
  }

  /**
   * Called when player quit
   * Override default message
   * 
   * @param event
   */
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    String message = Utils.getConfigString("QuitMessage");
    message = Utils.format(message, FormatParam.PLAYER, event.getPlayer().getDisplayName());
    event.setQuitMessage(message);
  }
}