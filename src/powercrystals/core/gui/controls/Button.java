package powercrystals.core.gui.controls;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import powercrystals.core.gui.Control;
import powercrystals.core.gui.GuiColor;
import powercrystals.core.gui.GuiRender;

public abstract class Button extends Control
{
	private String _text;
	
	public Button(GuiContainer containerScreen, int x, int y, int width, int height, String text)
	{
		super(containerScreen, x, y, width, height);
		_text = text;
	}

	public void setText(String text)
	{
		_text = text;
	}
	
	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		if(enabled && isPointInBounds(mouseX, mouseY))
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, containerScreen.mc.renderEngine.getTexture("/powercrystals/core/textures/button_hover.png"));
		}
		else if(enabled)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, containerScreen.mc.renderEngine.getTexture("/powercrystals/core/textures/button_enabled.png"));
		}
		else
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, containerScreen.mc.renderEngine.getTexture("/powercrystals/core/textures/button_disabled.png"));
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiRender.drawTexturedModalRect(x,             y,              0,               0,                width / 2, height / 2);
		GuiRender.drawTexturedModalRect(x,             y + height / 2, 0,               256 - height / 2, width / 2, height / 2);
		GuiRender.drawTexturedModalRect(x + width / 2, y,              256 - width / 2, 0,                width / 2, height / 2);
		GuiRender.drawTexturedModalRect(x + width / 2, y + height / 2, 256 - width / 2, 256 - height / 2, width / 2, height / 2);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		GuiColor textColor;
		if(enabled && isPointInBounds(mouseX, mouseY))
		{
			textColor = new GuiColor(16777120);
		}
		else if(enabled)
		{
			textColor = new GuiColor(14737632);
		}
		else
		{
			textColor = new GuiColor(-6250336);
		}
		
		String text = containerScreen.fontRenderer.trimStringToWidth(_text, width - 4);
		GuiRender.drawCenteredString(containerScreen.fontRenderer, text, x + width / 2, y + (height - 8) / 2, textColor.getColor());
	}
	
	@Override
	public boolean onMousePressed(int mouseX, int mouseY, int mouseButton)
	{
		containerScreen.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
		onClick();
		return true;
	}
	
	public abstract void onClick();
}
