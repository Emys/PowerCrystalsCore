package powercrystals.core.gui.controls;

import net.minecraft.client.gui.inventory.GuiContainer;

import org.lwjgl.opengl.GL11;

import powercrystals.core.gui.Control;
import powercrystals.core.gui.GuiColor;
import powercrystals.core.gui.GuiRender;

public abstract class SliderVertical extends Control
{
	private int _value;
	private int _valueMax;
	
	private boolean _isDragging;
	
	public int borderColor = new GuiColor(120, 120, 120, 255).getColor();
	public int backgroundColor = new GuiColor(0, 0, 0, 255).getColor();
	
	protected SliderVertical(GuiContainer containerScreen, int x, int y, int width, int height, int maxValue)
	{
		super(containerScreen, x, y, width, height);
		_valueMax = maxValue;
	}
	
	public void setValue(int value)
	{
		if(value != _value && value >= 0 && value <= _valueMax)
		{
			_value = value;
			onValueChanged(_value);
		}
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		GuiRender.drawRect(x - 1, y - 1, x + width + 1, y + height + 1, borderColor);
		GuiRender.drawRect(x, y, x + width, y + height, backgroundColor);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		int sliderWidth = width;
		int sliderHeight = 8;
		int sliderX = x;
		int sliderY = y + (height - sliderHeight) * _value / _valueMax;
		
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
		GuiRender.drawTexturedModalRect(sliderX,                   sliderY,                    0,                     0,                      sliderWidth / 2, sliderHeight / 2);
		GuiRender.drawTexturedModalRect(sliderX,                   sliderY + sliderHeight / 2, 0,                     256 - sliderHeight / 2, sliderWidth / 2, sliderHeight / 2);
		GuiRender.drawTexturedModalRect(sliderX + sliderWidth / 2, sliderY,                    256 - sliderWidth / 2, 0,                      sliderWidth / 2, sliderHeight / 2);
		GuiRender.drawTexturedModalRect(sliderX + sliderWidth / 2, sliderY + sliderHeight / 2, 256 - width / 2,       256 - sliderHeight / 2, sliderWidth / 2, sliderHeight / 2);
	}
	
	@Override
	public boolean onMousePressed(int mouseX, int mouseY, int mouseButton)
	{
		_isDragging = true;
		return true;
	}
	
	@Override
	public void onMouseReleased(int mouseX, int mouseY)
	{
		_isDragging = false;
	}
	
	@Override
	public void updateTick(int mouseX, int mouseY)
	{
		if(_isDragging)
		{
			setValue(_valueMax * (mouseY - y) / height);
		}
	}
	
	public abstract void onValueChanged(int value);
}
