package com.teampotato.screenofnibel.mixin;

import com.teampotato.screenofnibel.ScreenOfNibel;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Unique private static Music MUSIC_OF_NIBEL = null;

    @Inject(method = "getSituationalMusic", at = @At(value = "RETURN" , ordinal = 4), cancellable = true)
    private void screenOfNibel$getSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (MUSIC_OF_NIBEL == null) MUSIC_OF_NIBEL = new Music(ScreenOfNibel.SOUND_EVENT_OF_NIBEL.get(), 0, 0, true);
        cir.setReturnValue(MUSIC_OF_NIBEL);
    }
}
