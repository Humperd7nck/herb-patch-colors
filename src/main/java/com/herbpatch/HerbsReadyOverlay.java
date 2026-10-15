package com.herbpatch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

/**
 * Compact herb readiness indicator. RuneLite manages its position via Alt-drag.
 */
public class HerbsReadyOverlay extends Overlay
{
    static final int BASE_WIDTH = 130;
    static final int BASE_HEIGHT = 84;
    private static final int LEAF_WIDTH = 46;
    private static final int LEAF_HEIGHT = 34;
    private static final BufferedImage LEAF_ICON = loadLeafIcon();

    private final Client client;
    private final HerbPatchConfig config;
    private final HerbReadinessTracker tracker;

    @Inject
    public HerbsReadyOverlay(Client client, HerbPatchConfig config, HerbReadinessTracker tracker)
    {
        this.client = client;
        this.config = config;
        this.tracker = tracker;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (client.getGameState() != GameState.LOGGED_IN || !config.showHerbsReadyHud())
        {
            return null;
        }

        HerbsReadyHudState.State state = HerbsReadyHudState.resolve(
            true, tracker.getReadiness().allReady(), config.showNotReadyState());
        if (state == HerbsReadyHudState.State.HIDDEN)
        {
            return null;
        }
        final boolean ready = state == HerbsReadyHudState.State.READY;

        final double scale = Math.max(75, Math.min(150, config.herbsHudScalePercent())) / 100.0;
        final int width = (int) Math.round(BASE_WIDTH * scale);
        final int height = (int) Math.round(BASE_HEIGHT * scale);
        final Color background = ready ? config.herbsHudReadyColor() : config.herbsHudNotReadyColor();
        final Color textColor = config.herbsHudTextColor();
        final String label = ready ? "Herbs Ready" : "Not Ready";

        Graphics2D g = (Graphics2D) graphics.create();
        try
        {
            g.scale(scale, scale);
            int alpha = Math.max(0, Math.min(100, config.herbsHudOpacity())) * 255 / 100;
            g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), alpha));
            g.fillRect(0, 0, BASE_WIDTH, BASE_HEIGHT);

            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2f));
            g.drawRect(1, 1, BASE_WIDTH - 2, BASE_HEIGHT - 2);

            g.setColor(textColor);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(label, (BASE_WIDTH - fm.stringWidth(label)) / 2, 17);

            if (LEAF_ICON != null)
            {
                // Preserve the original pixel-art appearance of the leaf icon.
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                g.drawImage(LEAF_ICON, (BASE_WIDTH - LEAF_WIDTH) / 2, 31,
                    LEAF_WIDTH, LEAF_HEIGHT, null);
            }
        }
        finally
        {
            g.dispose();
        }
        return new Dimension(width, height);
    }

    private static BufferedImage loadLeafIcon()
    {
        try (InputStream stream = HerbsReadyOverlay.class.getResourceAsStream(
            "/com/herbpatch/herb-leaf.png"))
        {
            if (stream == null)
            {
                return null;
            }
            return ImageIO.read(stream);
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
