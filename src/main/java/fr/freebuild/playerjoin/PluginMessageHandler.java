package fr.freebuild.playerjoin;

import me.glauz.pluginmessagemanager.actions.ActionsHandler;
import org.bukkit.Bukkit;

public class PluginMessageHandler extends ActionsHandler {

    @Override
    public void onBroadcastReceived(String data) {
        Bukkit.getServer().broadcastMessage(data);
    }
}
