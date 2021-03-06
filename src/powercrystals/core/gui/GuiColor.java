package powercrystals.core.gui;

public class GuiColor
{
	private int _color;
	
	public GuiColor(int color)
	{
		_color = color;
	}
	
	public GuiColor(int r, int g, int b, int a)
	{
		_color = (b & 0xFF) | (g & 0xFF) << 8 | (r & 0xFF) << 16 | (a & 0xFF) << 24;
	}
	
	public int getColor()
	{
		return _color;
	}
	
	public int getIntR()
	{
		return (_color >> 16) & 0xFF;
	}
	
	public int getIntG()
	{
		return (_color >> 8) & 0xFF;
	}
	
	public int getIntB()
	{
		return _color & 0xFF;
	}
	
	public int getIntA()
	{
		return (_color >> 24) & 0xFF;
	}
}
