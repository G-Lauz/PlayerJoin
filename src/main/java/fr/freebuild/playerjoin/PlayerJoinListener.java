package fr.freebuild.playerjoin;

import me.glauz.pluginmessagemanager.actions.ActionsHandler;
import me.glauz.pluginmessagemanager.api.PluginMessageReceiver;
import me.glauz.pluginmessagemanager.api.SendPluginMessageErrorException;
import me.glauz.pluginmessagemanager.misc.Callback;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.freebuild.playerjoin.utils.FormatParam;
import fr.freebuild.playerjoin.utils.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayerJoinListener implements Listener {

  final ScheduledExecutorService executorService;

  public PlayerJoinListener() {
    executorService = Executors.newSingleThreadScheduledExecutor();

    PluginMessageReceiver.getInstance().setActionsHandler(new PluginMessageHandler());
  }

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

    // HACK
    // We need to wait until the player is connected to a server in order to use the plugin messaging channel
    // used by PluginMessageManager API
    // TODO: Fix in a next iteration of PluginMessageManager
    String finalMessage = message;
    executorService.schedule(() -> broadcastThisGroupServer(event.getPlayer(), finalMessage), 2, TimeUnit.SECONDS);
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
    broadcastThisGroupServer(event.getPlayer(), message);
  }

  private void broadcastThisGroupServer(Player player, String message) {
    try {
      PluginMessageReceiver.getInstance().broadcast(player, "survival", message);
    } catch (SendPluginMessageErrorException spmee) {
      // TODO
    }
  }
}