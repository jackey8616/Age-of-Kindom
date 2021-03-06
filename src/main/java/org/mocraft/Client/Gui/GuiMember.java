package org.mocraft.Client.Gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import org.mocraft.AgeOfKingdom;
import org.mocraft.Client.Gui.vanilla.GuiAokButton;
import org.mocraft.Client.Gui.vanilla.GuiAokContainer;
import org.mocraft.Inventory.ContainerCore;
import org.mocraft.Network.common.GuiMemberMessage;
import org.mocraft.TileEntity.TileCore;

/**
 * Created by Clode on 2016/10/22.
 */
@SideOnly(Side.CLIENT)
public class GuiMember extends GuiAokContainer {

    private int btnId = 0;

    private EntityPlayer player;
    private TileCore tile;
    private GuiTextField txtName;
    private GuiAokButton btnInvite, btnKick, btnCancel;
    private static String message;

    public GuiMember(TileCore tile, EntityPlayer player) {
        super(new ContainerCore(tile));
        this.tile = tile;
        this.player = player;
    }

    public static void announceMessage(String msg) {
        message = msg;
    }

    @Override
    public void initGui() {
        this.txtName = new GuiTextField(fontRendererObj, 10, 40, 100, 20);
        this.txtName.setFocused(true);

        this.buttonList.add(btnInvite = new GuiAokButton(btnId++, 10, 60, 100, 20, StatCollector.translateToLocal("gui.member.button.invite")));
        this.buttonList.add(btnKick = new GuiAokButton(btnId++, 10 + 100, 60, 100, 20, StatCollector.translateToLocal("gui.member.button.kick")));
        this.buttonList.add(btnCancel = new GuiAokButton(btnId++, 10 + 200, 60, 100, 20, StatCollector.translateToLocal("gui.member.button.cancel")));

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.txtName.drawTextBox();

        this.drawString(fontRendererObj, message, 10, 100, 0xffffff);

        for(Object obj : this.buttonList) {
            ((GuiButton) obj).drawButton(mc, p_146976_2_, p_146976_3_);
        }
    }

    @Override
    protected void keyTyped(char c, int keyCode) {
        super.keyTyped(c, keyCode);
        this.txtName.textboxKeyTyped(c, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int btnMouse) {
        super.mouseClicked(mouseX, mouseX, btnMouse);
        this.txtName.mouseClicked(mouseX, mouseY, btnMouse);
        this.btnInvite.mouseClicked(this, mouseX, mouseY, btnMouse);
        this.btnKick.mouseClicked(this, mouseX, mouseY, btnMouse);
        this.btnCancel.mouseClicked(this, mouseX, mouseY, btnMouse);
    }

    public void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 0: {
                if(!this.txtName.getText().equals(""))
                    AgeOfKingdom.channel.sendToServer(new GuiMemberMessage(player, txtName.getText(), GuiMemberMessage.Type.INVITE_MEMBER));
                break;
            }
            case 1: {
                if(!this.txtName.getText().equals(""))
                    AgeOfKingdom.channel.sendToServer(new GuiMemberMessage(player, txtName.getText(), GuiMemberMessage.Type.KICK_PLAYER));
                break;
            }
            case 2: {
                player.closeScreen();
                break;
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.txtName.updateCursorCounter();
    }


}
