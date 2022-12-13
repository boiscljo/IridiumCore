package com.iridium.iridiumcore;

import com.moyskleytech.obsidian.material.ObsidianMaterial
;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Map;

@NoArgsConstructor
public class Background {

    public Item filler = new Item(ObsidianMaterial.valueOf("BLACK_STAINED_GLASS_PANE"), 1, " ", Collections.emptyList());
    public Map<Integer, Item> items;

    public Background(Map<Integer, Item> items) {
        this.items = items;
    }

    public Background(Map<Integer, Item> items, Item filler) {
        this.items = items;
        this.filler = filler;
    }

}
