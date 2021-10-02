package ornaments.Commands;

// import java.io.File;
// import java.io.IOException;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

@Environment(EnvType.CLIENT)
public class Commands {

  public static void init() {
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("DebugSystem").then(ClientCommandManager
        .argument("R1", StringArgumentType.word())
        .then(ClientCommandManager.argument("G1", StringArgumentType.word())
            .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word()).then(
                        ClientCommandManager.argument("B2", StringArgumentType.word()).executes(Commands::debug))))))));
  }

  private static int debug(CommandContext<FabricClientCommandSource> context) {
    try {
      x = Float.parseFloat(StringArgumentType.getString(context, "R1"));
      y = Float.parseFloat(StringArgumentType.getString(context, "G1"));
      z = Float.parseFloat(StringArgumentType.getString(context, "B1"));
      xr = Float.parseFloat(StringArgumentType.getString(context, "R2"));
      yr = Float.parseFloat(StringArgumentType.getString(context, "G2"));
      zr = Float.parseFloat(StringArgumentType.getString(context, "B2"));
    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }
    System.out.println(StringArgumentType.getString(context, "R1"));
    return 0;
  }

  public static float x = 0.0F, y = 0.0F, z = 0.0F, xr = 0.0F, yr = 0.0F, zr = 0.0F;
}
