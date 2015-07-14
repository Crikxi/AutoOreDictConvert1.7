package com.mattdahepic.autooredictconv;

import com.mattdahepic.autooredictconv.command.CommandConfig;
import com.mattdahepic.autooredictconv.config.Config;
import com.mattdahepic.autooredictconv.convert.Convert;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;

@Mod(modid = AutoOreDictConv.MODID,name = AutoOreDictConv.NAME,version = AutoOreDictConv.VERSION)
public class AutoOreDictConv {
    @Mod.Instance("autooredictconv")
    public static AutoOreDictConv instance;

    public static final String MODID = "autooredictconv";
    public static final String NAME = "Hotkey Ore Dictionary Converter";
    public static final String VERSION = "@VERSION@";

    public static MinecraftServer mcServer;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(instance);
        Config.load(new File(event.getModConfigurationDirectory(),"autooredictconvert.cfg"));
    }
    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {}
    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {}
    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandConfig());
        mcServer = event.getServer();
        OreDictionary.rebakeMap(); //metallurgy bug fix
    }
    @SubscribeEvent
    public void onTick (TickEvent.ServerTickEvent event) {
        Convert.convert();
    }
}
