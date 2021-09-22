package ornaments.Commands;

// import java.io.File;
// import java.io.IOException;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.TranslatableText;
import ornaments.Data.GlobalData;
import ornaments.Data.SpliceableText;
import ornaments.OFcape.Get;

@Environment(EnvType.CLIENT)
public class Commands {
  private static String playernow = "";

  public static void init() {
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("Ornaments")
        .then(ClientCommandManager.literal("Help").executes(Commands::showhelp))
        .then(ClientCommandManager.literal("Credit").executes(Commands::showcredit))
        .then(ClientCommandManager.literal("SwitchPlayer").then(
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

  private static int set_optifine_cape(CommandContext<FabricClientCommandSource> context) {
    if (playernow.isEmpty()) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.nullplayer"), false);
      return 0;
    }
    if (!context.getSource().getPlayer().getName().asString().equals(playernow)) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.capeonlymine"), false);
      return 0;
    }
    Get.GetCape(context.getSource().getPlayer(), StringArgumentType.getString(context, "PlayerName"));
    GlobalData.setKey(playernow, "cape", StringArgumentType.getString(context, "PlayerName"));
    context.getSource().getPlayer().sendMessage(SpliceableText.makeText("command.succeed.succeedsetcape", playernow,
        StringArgumentType.getString(context, "PlayerName")), false);
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
    if (playernow.isEmpty()) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.nullplayer"), false);
      return 0;
    }
    Get.HideCape(context.getSource().getPlayer());
    GlobalData.setKey(playernow, "cape", "");
    context.getSource().getPlayer().sendMessage(SpliceableText.makeText("command.succeed.succeedhidecape", playernow),
        false);
    return 0;
  }

  private static int set_wings(CommandContext<FabricClientCommandSource> context) {
    if (playernow.isEmpty()) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.nullplayer"), false);
      return 0;
    }
    float r1 = 0.0F, g1 = 0.0F, b1 = 0.0F, r2 = 0.0F, g2 = 0.0F, b2 = 0.0F;
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
    GlobalData.setKey(playernow, "wing", context.getInput().split(" ")[2]);
    GlobalData.setKey(playernow, "wr1", String.valueOf(r1));
    GlobalData.setKey(playernow, "wg1", String.valueOf(g1));
    GlobalData.setKey(playernow, "wb1", String.valueOf(b1));
    GlobalData.setKey(playernow, "wr2", String.valueOf(r2));
    GlobalData.setKey(playernow, "wg2", String.valueOf(g2));
    GlobalData.setKey(playernow, "wb2", String.valueOf(b2));
    context.getSource().getPlayer().sendMessage(
        SpliceableText.makeText("command.succeed.succeedsetwing", playernow, context.getInput().split(" ")[2]), false);
    return 0;
  }

  private static int hide_wings(CommandContext<FabricClientCommandSource> context) {
    if (playernow.isEmpty()) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.nullplayer"), false);
      return 0;
    }
    GlobalData.setKey(playernow, "wing", "");
    context.getSource().getPlayer().sendMessage(SpliceableText.makeText("command.succeed.succeedhidewing", playernow),
        false);
    return 0;
  }

  private static int set_visible(CommandContext<FabricClientCommandSource> context) {
    if (playernow.isEmpty()) {
      context.getSource().getPlayer().sendMessage(new TranslatableText("command.error.nullplayer"), false);
      return 0;
    }
    String arg = context.getInput().split(" ")[3];
    if (arg.equals("true")) {
      GlobalData.setKey(playernow, "hide", String.valueOf(true));
      context.getSource().getPlayer()
          .sendMessage(SpliceableText.makeText("command.succeed.setvisible", playernow, "true"), false);
    } else if (arg.equals("false")) {
      GlobalData.setKey(playernow, "hide", String.valueOf(false));
      context.getSource().getPlayer()
          .sendMessage(SpliceableText.makeText("command.succeed.setvisible", playernow, "false"), false);
    } else
      return 1;
    return 0;
  }

  private static int SetPlayerName(CommandContext<FabricClientCommandSource> context) {
    playernow = StringArgumentType.getString(context, "PlayerName");
    context.getSource().getPlayer().sendMessage(SpliceableText.makeText("command.succeed.changeplayer", playernow),
        false);
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

  public enum State {
    IDLE, CROUCHING, FLYING
  }
}
