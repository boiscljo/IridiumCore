package com.iridium.iridiumcore;

import com.iridium.iridiumcore.multiversion.*;
import com.iridium.iridiumcore.nms.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("Convert2MethodRef")
public enum MinecraftVersion {

    /**
     * IntelliJ will recommend you to replace these with method reference.
     * However, this would break the plugins on some machines running the HotSpot VM.
     * Just leave this as it is and add new versions down below in the same way.
     */
    DEFAULT(() -> new NMSDefault(), MultiversionDefault::new);
    
    private final Supplier<NMS> nmsSupplier;
    private final JavaPluginSupplier<MultiVersion> multiVersionSupplier;

    MinecraftVersion(Supplier<NMS> nmsSupplier, JavaPluginSupplier<MultiVersion> multiVersionSupplier) {
        this.nmsSupplier = nmsSupplier;
        this.multiVersionSupplier = multiVersionSupplier;
    }

    public NMS getNms() {
        return nmsSupplier.get();
    }

    public MultiVersion getMultiVersion(JavaPlugin javaPlugin) {
        return multiVersionSupplier.get(javaPlugin);
    }

    @NotNull
    public static MinecraftVersion byName(String version) {
        for (MinecraftVersion minecraftVersion : values()) {
            if (minecraftVersion.name().equalsIgnoreCase(version)) {
                return minecraftVersion;
            }
        }

        return DEFAULT;
    }

}
