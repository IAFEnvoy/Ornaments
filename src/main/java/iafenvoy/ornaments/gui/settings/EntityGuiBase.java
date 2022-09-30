package iafenvoy.ornaments.gui.settings;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.gui.ButtonGroup;
import iafenvoy.ornaments.renderer.EntityRenderer;
import iafenvoy.ornaments.utils.MathUtil;
import net.minecraft.client.util.math.MatrixStack;

public class EntityGuiBase extends GuiBase implements SettingGui {
    protected final GuiBase parent;
    protected final ButtonGroup group = new ButtonGroup(20, 30, 50);
    private final boolean shouldRenderElytra;
    protected double defaultRotateX = 0, defaultRotateY = 0;
    private boolean isMouseDown = false;
    private double rotateX = 0, rotateY = 0;
    private double prevMouseX = 0, prevMouseY = 0;

    public EntityGuiBase(GuiBase parent, boolean shouldRenderElytra) {
        this.parent = parent;
        this.shouldRenderElytra = shouldRenderElytra;
        this.resetEntity();
    }

    @Override
    protected void drawTitle(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        this.drawString(matrices, this.getTitleString(), 90, 15, -1);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addButton(new ButtonGeneric(20, 10, 50, 20, StringUtils.translate("config.ornaments.back")), (button, mouseButton) -> {
            GuiBase.openGui(parent);
            this.resetEntity();
        });
        group.initGui(this);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        super.render(matrices, mouseX, mouseY, partialTicks);
        if (this.isMouseDown) {
            this.rotateX -= mouseX - this.prevMouseX;
            this.rotateY -= mouseY - this.prevMouseY;
            this.prevMouseX = mouseX;
            this.prevMouseY = mouseY;
        }
        EntityRenderer.render(matrices, (float) this.rotateX + 180, (float) this.rotateY, this.shouldRenderElytra, 50);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (MathUtil.between(mouseX, 250, 350) && MathUtil.between(mouseY, 50, 200)) {
            this.isMouseDown = true;
            this.prevMouseX = mouseX;
            this.prevMouseY = mouseY;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        this.isMouseDown = false;
        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    protected void resetEntity() {
        this.rotateX = this.defaultRotateX;
        this.rotateY = this.defaultRotateY;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        //Do nothing
        return true;
    }
}
