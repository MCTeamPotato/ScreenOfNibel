package com.teampotato.screenofnibel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(ScreenOfNibel.MOD_ID)
public class ScreenOfNibel {
    public static final String MOD_ID = "screenofnibel";

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);
    public static final RegistryObject<SoundEvent> SOUND_EVENT_OF_NIBEL = SOUND_EVENTS.register(MOD_ID, () -> new SoundEvent(new ResourceLocation(MOD_ID, MOD_ID)));

    public ScreenOfNibel() {
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}