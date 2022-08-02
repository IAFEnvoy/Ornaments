package iafenvoy.ornaments.Registry;

import com.mojang.brigadier.arguments.StringArgumentType;

import iafenvoy.ornaments.Config.Configs;
import iafenvoy.ornaments.Items.Cape.Enum.ColorEnum;
import iafenvoy.ornaments.Items.Cape.Enum.Pattern;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Commands {
  private static final MinecraftClient client = MinecraftClient.getInstance();

  public static void register() {
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("ornaments")
        .then(ClientCommandManager.literal("cape")
            .then(ClientCommandManager.literal("copy")
                .executes(context -> {
                  StringBuilder sb = new StringBuilder();
                  sb.append(Integer.toHexString(
                      ((ColorEnum) Configs.INSTANCE.colorbase.getOptionListValue()).getDyeColor().getId()));
                  for (int i = 0; i < 8; i++) {
                    String pid = ((Pattern) Configs.INSTANCE.nameList[i].getOptionListValue()).getId();
                    if (pid.length() == 1)
                      pid += '-';
                    if (pid.length() == 2)
                      pid += '-';
                    sb.append(pid);
                    sb.append(Integer.toHexString(
                        ((ColorEnum) Configs.INSTANCE.colorList[i].getOptionListValue()).getDyeColor().getId()));
                  }
                  client.keyboard.setClipboard(sb.toString());
                  context.getSource().getPlayer().sendMessage(Text.of("Copy to clipboard!"), false);
                  return 0;
                }))
            .then(ClientCommandManager.literal("use")
                .then(ClientCommandManager.argument("id", StringArgumentType.word())
                    .executes(context -> {
                      String id = StringArgumentType.getString(context, "id");
                      Configs.INSTANCE.colorbase
                          .setOptionListValue(ColorEnum.getById(Integer.parseInt(id.substring(0, 1), 16)));
                      for (int i = 0; i < 8; i++) {
                        String pid = id.substring(1 + i * 4, 4 + i * 4);
                        pid = pid.replace("-", "");
                        Configs.INSTANCE.nameList[i].setOptionListValue(Pattern.getById(pid));
                        int color = Integer.parseInt(id.substring(4 + i * 4, 5 + i * 4), 16);
                        Configs.INSTANCE.colorList[i].setOptionListValue(ColorEnum.getById(color));
                      }
                      return 0;
                    })))));
  }
}
