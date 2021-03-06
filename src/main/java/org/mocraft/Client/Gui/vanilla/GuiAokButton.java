package org.mocraft.Client.Gui.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;

/**
 * Created by Clode on 2016/10/22.
 */
@SideOnly(Side.CLIENT)
public class GuiAokButton extends GuiButton {

    public GuiAokButton(int id, int x, int y, int wdidth, int height, String string) {
        super(id, x, y, wdidth, height, string);
    }

    public void mouseClicked(GuiAokScreen screen, int mouseX, int mouseY, int mouseBtn) {
        if (mouseBtn == 0) {
            if(mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height) {
                screen.actionPerformed(this);
            }
        }
    }

}
