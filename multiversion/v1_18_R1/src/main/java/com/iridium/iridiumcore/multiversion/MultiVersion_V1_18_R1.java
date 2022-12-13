package com.iridium.iridiumcore.multiversion;

import com.moyskleytech.obsidian.material.ObsidianMaterial
;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import io.papermc.lib.PaperLib;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for working with methods that were changed during an update by Spigot.
 */
public class MultiVersion_V1_18_R1 extends MultiVersion {

    public MultiVersion_V1_18_R1(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    /**
     * Returns the material at a position in a chunk.
     *
     * @param chunk The snapshot of the chunk where the position is in
     * @param x     The relative x position of the block in the chunk
     * @param y     The relative y position of the block in the chunk
     * @param z     The relative z position of the block in the chunk
     * @return The material at the provided position in the chunk
     */
    @Override
    public ObsidianMaterial getMaterialAtPosition(ChunkSnapshot chunk, int x, int y, int z) {
        return ObsidianMaterial.valueOf(chunk.getBlockType(x, y, z));
    }

    @Override
    public boolean isPassable(Block block) {
        return block.isPassable();
    }

    @Override
    public CompletableFuture<Chunk> getChunkAt(World world, int x, int z) {
        return PaperLib.getChunkAtAsync(world, x, z, true);
    }

}
