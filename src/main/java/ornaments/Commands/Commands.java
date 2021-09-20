package ornaments.Commands;

// import java.io.File;
// import java.io.IOException;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.TranslatableText;
import ornaments.OFcape.Get;
import ornaments.Client.client;
import ornaments.Client.config;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class Commands {

  public static void init() {
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("Ornaments")
        .then(ClientCommandManager.literal("Help").executes(Commands::showhelp))
        .then(ClientCommandManager.literal("Credit").executes(Commands::showcredit))
        .then(ClientCommandManager.literal("SetPlayerName").then(
            ClientCommandManager.argument("PlayerName", StringArgumentType.word()).executes(Commands::SetPlayerName)))
        .then(ClientCommandManager.literal("Cape")
            .then(ClientCommandManager.literal("FromOptifine")
                .then(ClientCommandManager.argument("PlayerName", StringArgumentType.word())
                    .executes(Commands::set_optifine_cape)))
            // .then(ClientCommandManager.literal("FromDisk")
            // .then(ClientCommandManager.argument("TextureName",
            // StringArgumentType.word())
            // .executes(Commands::set_picture_cape)))
            .then(ClientCommandManager.literal("Hide").executes(Commands::hide_cape)))
        .then(ClientCommandManager.literal("Wings")
            .then(ClientCommandManager.literal("SetVisibleWithElytra")
                .then(ClientCommandManager.literal("true").executes(Commands::set_visible))
                .then(ClientCommandManager.literal("false").executes(Commands::set_visible)))
            .then(ClientCommandManager.literal("Feathered")
                .then(ClientCommandManager.argument("R1", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                          .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                            .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                                .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                                  .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Dragon")
            .then(ClientCommandManager.argument("R1", StringArgumentType.word())
            .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                  .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                          .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Light")
            .then(ClientCommandManager.argument("R1", StringArgumentType.word())
            .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                  .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                          .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Discords")
            .then(ClientCommandManager.argument("R1", StringArgumentType.word())
            .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                  .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                          .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Zanzas")
            .then(ClientCommandManager.argument("R1", StringArgumentType.word())
            .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                  .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                          .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Tech")
            .then(ClientCommandManager.argument("R1", StringArgumentType.word())
            .then(ClientCommandManager.argument("G1", StringArgumentType.word())
                .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                  .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word())
                        .then(ClientCommandManager.argument("B2", StringArgumentType.word())
                          .executes(Commands::set_wings))))))))
            .then(ClientCommandManager.literal("Hide").executes(Commands::hide_wings))));
  }

  private static int set_optifine_cape(CommandContext<FabricClientCommandSource> context)
      throws CommandSyntaxException {
    Get.GetCape(context.getSource().getPlayer(), StringArgumentType.getString(context, "PlayerName"));
    config.setCape(StringArgumentType.getString(context, "PlayerName"));
    return 0;
  }

  // private static int set_picture_cape(CommandContext<FabricClientCommandSource>
  // context)
  // throws CommandSyntaxException {
  // File f = new File("");
  // String cf = null;
  // try {
  // cf = f.getCanonicalPath();
  // } catch (IOException e) {
  // e.printStackTrace();
  // return 1;
  // }
  // Get.SetCape(context.getSource().getPlayer(),
  // cf + "\\Ornaments\\Capes\\" + StringArgumentType.getString(context,
  // "TextureName") + ".png");
  // return 0;
  // }

  private static int hide_cape(CommandContext<FabricClientCommandSource> context) {
    Get.HideCape(context.getSource().getPlayer());
    config.setCape("");
    return 0;
  }

  private static int set_wings(CommandContext<FabricClientCommandSource> context) {
    float r1 = 0.0F, g1 = 0.0F, b1 = 0.0F,r2 = 0.0F, g2 = 0.0F, b2 = 0.0F;
    try {
      r1 = Float.parseFloat(StringArgumentType.getString(context, "R1"));
      g1 = Float.parseFloat(StringArgumentType.getString(context, "G1"));
      b1 = Float.parseFloat(StringArgumentType.getString(context, "B1"));
      r2 = Float.parseFloat(StringArgumentType.getString(context, "R2"));
      g2 = Float.parseFloat(StringArgumentType.getString(context, "G2"));
      b2 = Float.parseFloat(StringArgumentType.getString(context, "B2"));
    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }
    client.player.setWings(context.getInput().split(" ")[2]);
    client.player.setColor(r1,g1,b1,r2,g2,b2);
    return 0;
  }

  private static int hide_wings(CommandContext<FabricClientCommandSource> context) {
    client.player.setWings("");
    return 0;
  }

  private static int set_visible(CommandContext<FabricClientCommandSource> context) {
    String arg = context.getInput().split(" ")[3];
    if (arg.equals("true"))
      config.setHide(true);
    else if (arg.equals("false"))
      config.setHide(false);
    else
      return 1;
    return 0;
  }

  private static int SetPlayerName(CommandContext<FabricClientCommandSource> context) {
    config.playername = StringArgumentType.getString(context, "PlayerName");
    config.saveConfig();
    return 0;
  }

  private static int showhelp(CommandContext<FabricClientCommandSource> context) {
    context.getSource().getPlayer().sendMessage(new TranslatableText("empty"), false);
    context.getSource().getPlayer().sendMessage(new TranslatableText("help.title"), false);
    for (int i = 1; i <= 10; i++) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("help.text" + String.valueOf(i)), false);
    }
    return 0;
  }

  private static int showcredit(CommandContext<FabricClientCommandSource> context) {
    context.getSource().getPlayer().sendMessage(new TranslatableText("empty"), false);
    context.getSource().getPlayer().sendMessage(new TranslatableText("credit.title"), false);
    for (int i = 1; i <= 6; i++) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("credit.text" + String.valueOf(i)), false);
    }
    return 0;
  }
}
