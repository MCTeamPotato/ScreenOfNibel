package com.teampotato.screenofnibel.mixin;

import com.teampotato.screenofnibel.ScreenOfNibel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
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
    @Shadow @Nullable public Screen screen;
    @Unique private static Music MUSIC_OF_NIBEL = null;

    @Inject(method = "getSituationalMusic", at = @At(value = "RETURN" , ordinal = 4), cancellable = true)
    private void screenOfNibel$getSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (MUSIC_OF_NIBEL == null) MUSIC_OF_NIBEL = new Music(ScreenOfNibel.SOUND_EVENT_OF_NIBEL.get(), 0, 0, true);
        cir.setReturnValue(MUSIC_OF_NIBEL);
    }

    @Inject(method = "getSituationalMusic", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;dimension()Lnet/minecraft/resources/ResourceKey;", shift = At.Shift.BEFORE), cancellable = true)
    private void screenOfNibel$playNibelOnLoadingWorld(CallbackInfoReturnable<Music> cir) {
        if (MUSIC_OF_NIBEL == null) MUSIC_OF_NIBEL = new Music(ScreenOfNibel.SOUND_EVENT_OF_NIBEL.get(), 0, 0, true);
        if (this.screen instanceof LevelLoadingScreen) cir.setReturnValue(MUSIC_OF_NIBEL);
    }
}
