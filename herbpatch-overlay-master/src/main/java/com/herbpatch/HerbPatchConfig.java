package com.herbpatch;

import lombok.AllArgsConstructor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(HerbPatchConfig.CONFIG_GROUP)
public interface HerbPatchConfig extends Config {
    String CONFIG_GROUP = "herbpatchoverlay";

    @ConfigItem(
        keyName = "overlaySelector",
        name = "Overlay type",
        description = "Configure overlay type",
        position = 101
    )
    default OverlayType overlaySelector() {
        return OverlayType.HULL;
    }

    @ConfigItem(
        keyName = "renderIfOvergrown",
        name = "Render overgrown overlay",
        description = "Render overlay on overgrown herb patches",
        position = 102
    )
    default boolean renderIfOvergrown() {
        return true;
    }

    @ConfigItem(
        keyName = "renderIfEmpty",
        name = "Render empty overlay",
        description = "Render overlay on empty herb patches",
        position = 103
    )
    default boolean renderIfEmpty() {
        return true;
    }


    @ConfigSection(
        name = "Render Options",
        description = "Options relating to rendering",
        position = 200
    )
    String renderOptionsSection = "optionsOutline";

    @ConfigItem(
        keyName = "outlineThickness",
        name = "Outline Thickness",
        description = "Outline thickness for overlay type Outline",
        section = renderOptionsSection,
        position = 201
    )
    default int outlineThickness() {
        return 2;
    }

    @ConfigItem(
        keyName = "outlineFeather",
        name = "Outline Feather",
        description = "Outline feather for overlay type Outline",
        section = renderOptionsSection,
        position = 202
    )
    default int outlineFeather() {
        return 0;
    }

    @ConfigItem(
        keyName = "fillAlpha",
        name = "Hull/Tile Fill Alpha",
        description = "Fill alpha for overlay type Hull and Tile",
        section = renderOptionsSection,
        position = 203
    )
    default int fillAlpha() {
        return 10;
    }

    @ConfigSection(
            name = "Herb Overlay Color",
            description = "Custom overlay colors for herbs",
            position = 300
    )
    String customColorSection = "customColor";

    @ConfigItem(
            keyName = "overgrownColor",
            name = "Overgrown",
            description = "",
            section = customColorSection,
            position = 301
    )
    default Color overgrownColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "emptyColor",
            name = "Empty",
            description = "",
            section = customColorSection,
            position = 302
    )
    default Color emptyColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "unripeColor",
            name = "Unripe",
            description = "",
            section = customColorSection,
            position = 303
    )
    default Color unripeColor() {
        return Color.YELLOW;
    }

    @ConfigItem(
            keyName = "matureColor",
            name = "Mature",
            description = "",
            section = customColorSection,
            position = 304
    )
    default Color matureColor() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "diseasedColor",
            name = "Diseased",
            description = "",
            section = customColorSection,
            position = 305
    )
    default Color diseasedColor() {
        return Color.MAGENTA;
    }

    @ConfigItem(
            keyName = "deadColor",
            name = "Dead",
            description = "",
            section = customColorSection,
            position = 306
    )
    default Color deadColor() {
        return Color.RED;
    }

    @ConfigItem(
        keyName = "enableCompostColor",
        name = "Highlight composted herbs",
        description = "Use the compost color for growing patches tracked as composted by RuneLite Time Tracking",
        section = customColorSection,
        position = 307
    )
    default boolean enableCompostColor() {
        return true;
    }

    @ConfigItem(
        keyName = "compostColor",
        name = "Composted (growing)",
        description = "Only replaces Growing; empty, mature, diseased and dead keep their own colors",
        section = customColorSection,
        position = 308
    )
    default Color compostColor() {
        return new Color(56, 189, 248);
    }

    @AllArgsConstructor
    enum OverlayType {
        HULL("Hull"),
        OUTLINE("Outline"),
        TILE("Tile");

        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }
}
