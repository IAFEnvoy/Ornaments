package iafenvoy.ornaments.Client;

import iafenvoy.ornaments.multiplayer.OrnamentsNetwork;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

@Environment(EnvType.CLIENT)
public class Commands {

  public static void registry() {
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("Ornaments")
        .then(ClientCommandManager.literal("Refresh").executes((context) -> {
          OrnamentsNetwork.clear();
          return 0;
        })));
  }
}
