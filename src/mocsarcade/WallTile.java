package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WallTile extends Tile
{
	public WallTile(int tx, int ty)
	{
		super(tx, ty);
	}

	public void render(Graphics graphics)
	{
		graphics.setColor(Color.darkGray);
		graphics.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public boolean isPassable()
	{
		return false;
	}
}