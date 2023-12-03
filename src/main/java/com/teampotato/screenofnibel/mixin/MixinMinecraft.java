package com.teampotato.screenofnibel.mixin;

import com.teampotato.screenofnibel.ScreenOfNibel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Unique private static Music MUSIC_OF_NIBEL = null;

    @Shadow @Nullable public Screen screen;

    @Inject(method = "getSituationalMusic", at = @At("HEAD"), cancellable = true)
    private void screenOfNibel$getSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (this.screen instanceof TitleScreen) {
            if (MUSIC_OF_NIBEL == null) {
                MUSIC_OF_NIBEL = new Music(ScreenOfNibel.SOUND_EVENT_OF_NIBEL.get(), 0, 0, true);
            }
            cir.setReturnValue(MUSIC_OF_NIBEL);
        }
    }
}
