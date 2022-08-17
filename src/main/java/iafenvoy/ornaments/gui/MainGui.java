package iafenvoy.ornaments.gui;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.ConfigManager;
import iafenvoy.ornaments.config.CapeInfo;
import iafenvoy.ornaments.config.ShoulderPlayerInfo;
import iafenvoy.ornaments.config.ToolInfo;
import iafenvoy.ornaments.config.WingInfo;
import iafenvoy.ornaments.gui.buttons.OpenGuiButton;
import iafenvoy.ornaments.gui.settings.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class MainGui extends GuiBase implements SettingGui {
    private final ButtonGroup group = new ButtonGroup(20, 30, 50);
    private final Screen parent;

    public MainGui(Screen parent) {
        this.title = StringUtils.translate("config.ornaments.title");
        this.parent = parent;
        group.newProperty("config.ornaments.cape");
        group.add(new OpenGuiButton("cape", "config.ornaments.cape", 80, new CapeGui(CapeInfo.cape, "config.ornaments.cape", false, this)));
        group.add(new OpenGuiButton("left_elytra", "config.ornaments.left_elytra", 80, new CapeGui(CapeInfo.leftElytra, "config.ornaments.left_elytra", true, this)));
        group.add(new OpenGuiButton("right_elytra", "config.ornaments.right_elytra", 80, new CapeGui(CapeInfo.rightElytra, "config.ornaments.right_elytra", true, this)));
        group.newProperty("config.ornaments.wings");
        group.add(new OpenGuiButton("wings", "config.ornaments.wings", 80, new WingGui(WingInfo.wing, "config.ornaments.wings", this)));
        group.newProperty("config.ornaments.backtools");
        group.add(new OpenGuiButton("left_back_tool", "config.ornaments.left_back_tool", 80, new ToolGui(ToolInfo.leftBackTool, "config.ornaments.left_back_tool", this)));
        group.add(new OpenGuiButton("right_back_tool", "config.ornaments.right_back_tool", 80, new ToolGui(ToolInfo.rightBackTool, "config.ornaments.right_back_tool", this)));
        group.newProperty("config.ornaments.side_item");
        group.add(new OpenGuiButton("left_back_tool", "config.ornaments.left_side_item", 80, new ToolGui(ToolInfo.leftBeltTool, "config.ornaments.left_side_item", this)));
        group.add(new OpenGuiButton("right_back_tool", "config.ornaments.right_side_item", 80, new ToolGui(ToolInfo.rightBeltTool, "config.ornaments.right_side_item", this)));
        group.newProperty("config.ornaments.shoulder_player");
        group.add(new OpenGuiButton("left_shoulder_player", "config.ornaments.left_shoulder_player", 80, new ShoulderPlayerGui(ShoulderPlayerInfo.leftPlayer, "config.ornaments.left_shoulder_player", this)));
        group.add(new OpenGuiButton("right_shoulder_player", "config.ornaments.right_shoulder_player", 80, new ShoulderPlayerGui(ShoulderPlayerInfo.rightPlayer, "config.ornaments.right_shoulder_player", this)));
    }

    @Override
    protected void drawTitle(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        this.drawString(matrices, this.getTitleString(), 90, 15, -1);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addButton(new ButtonGeneric(20, 10, 50, 20, StringUtils.translate("config.ornaments.back")), (button, mouseButton) -> GuiBase.openGui(parent));
        group.initGui(this);
    }

    @Override
    public void onClose() {
        ConfigManager.INSTANCE.save();
        super.onClose();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        //Do nothing
        return true;
    }
}