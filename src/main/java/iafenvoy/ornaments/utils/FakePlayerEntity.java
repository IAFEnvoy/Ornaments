package iafenvoy.ornaments.utils;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalLong;

public class FakePlayerEntity {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static class DummyClientPlayerEntity extends ClientPlayerEntity {
        public static final DummyClientPlayerEntity INSTANCE = new DummyClientPlayerEntity(null);
        private Identifier skin = null;

        public DummyClientPlayerEntity(@Nullable Identifier skin) {
            super(MinecraftClient.getInstance(), DummyClientWorld.INSTANCE, DummyClientPlayNetworkHandler.INSTANCE, null, null, false, false);
            this.loadSkin(skin);
        }

        public void loadSkin(Identifier skin) {
            if (skin == null)
                client.getSkinProvider().loadSkin(super.getGameProfile(), (type, identifier, texture) -> {
                    if (type == MinecraftProfileTexture.Type.SKIN)
                        this.skin = identifier;
                }, true);
            else
                this.skin = skin;
        }

        @Override
        public boolean hasSkinTexture() {
            return true;
        }

        @Override
        public Identifier getSkinTexture() {
            return skin == null ? DefaultSkinHelper.getTexture(this.getUuid()) : skin;
        }

        @Override
        public boolean isPartVisible(PlayerModelPart modelPart) {
            return true;
        }

        @Override
        public Text getDisplayName() {
            if (client.player == null || client.player instanceof DummyClientPlayerEntity)
                return Text.of(client.getSession().getUsername());
            return client.player.getDisplayName();
        }

        @Override
        protected PlayerListEntry getPlayerListEntry() {
            return null;
        }

        @Override
        public boolean isSpectator() {
            return false;
        }

        @Override
        public boolean isCreative() {
            return true;
        }
    }

    public static class DummyClientWorld extends ClientWorld {
        public static final DummyClientWorld INSTANCE = new DummyClientWorld();

        public DummyClientWorld() {
            super(DummyClientPlayNetworkHandler.INSTANCE, new Properties(Difficulty.EASY, false, true), null, DummyDimensionType.INSTANCE, 0, () -> null, null, false, 0L);
        }
    }

    private static class DummyClientPlayNetworkHandler extends ClientPlayNetworkHandler {
        public static final DummyClientPlayNetworkHandler INSTANCE = new DummyClientPlayNetworkHandler();

        public DummyClientPlayNetworkHandler() {
            super(client, null, new ClientConnection(NetworkSide.CLIENTBOUND), client.getSession().getProfile());
        }
    }

    private static class DummyDimensionType extends DimensionType {
        public static final DummyDimensionType INSTANCE = new DummyDimensionType();

        public DummyDimensionType() {
            super(OptionalLong.empty(), true, false, false, false, 1f, false, false, false, false, 0, BlockTags.INFINIBURN_OVERWORLD.getId(), DimensionType.OVERWORLD_ID, 1f);
        }
    }
}
