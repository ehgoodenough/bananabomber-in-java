package mocsarcade;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Monkey
{
	public static HashMap<String, Image> images = new HashMap<String, Image>();
	private float x, y;
	private float speed;
	private Image image;
	private GameMap gamemap;
	public int power;
	private String color;
	private KeyScheme keyscheme;
	public int bombcount;
	
	public Monkey(GameMap gamemap, String color)
	{
		this.gamemap = gamemap;
		this.color = color;
		Random random = new Random();
		this.x = (random.nextInt(9) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.y = (random.nextInt(7) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.speed = 0.25f;
		this.power = 2;
		this.bombcount = 1;
		this.image = Monkey.images.get(this.color);
		this.keyscheme = new KeyScheme(this.color);
	}
	
	public void update(Input input, int delta)
	{
		float step = this.speed * delta;
		
		if(input.isKeyDown(this.keyscheme.moveNorth))
		{
			if(Math.floor(this.y / Tile.HEIGHT) == Math.floor((this.y - step) / Tile.HEIGHT)
			|| this.gamemap.getTile(this.x, this.y - step).isPassable())
			{
				this.y -= step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveSouth))
		{
			if(Math.floor(this.y / Tile.HEIGHT) == Math.floor((this.y + step) / Tile.HEIGHT)
			|| this.gamemap.getTile(this.x, this.y + step).isPassable())
			{
				this.y += step;
			}
		}

		if(input.isKeyDown(this.keyscheme.moveWest))
		{
			if(Math.floor(this.x / Tile.WIDTH) == Math.floor((this.x - step) / Tile.WIDTH)
			|| this.gamemap.getTile(this.x - step, this.y).isPassable())
			{
				this.x -= step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveEast))
		{
			if(Math.floor(this.x / Tile.WIDTH) == Math.floor((this.x + step) / Tile.WIDTH)
			|| this.gamemap.getTile(this.x + step, this.y).isPassable())
			{
				this.x += step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.dropBomb))
		{
			Tile tile = this.gamemap.getTile(this.x, this.y);
			
			if(!tile.hasBomb())
			{
				if(this.bombcount > 0)
				{
					tile.addBomb(new Bomb(tile, this.power, this));
					this.bombcount -= 1;
				}
			}
		}
	}
	
	public void render(Graphics graphics)
	{
		float x = this.getX() - this.getHalfWidth();
		float y = this.getY() - this.getHalfHeight();
		
		this.image.draw(x, y);
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}

	public int getTileyX()
	{
		return (int)(Math.floor(this.getX() / Tile.WIDTH)); 
	}

	public int getTileyY()
	{
		return (int)(Math.floor(this.getY() / Tile.HEIGHT)); 
	}
	
	public int getWidth()
	{
		return this.image.getWidth();
	}
	
	public int getHeight()
	{
		return this.image.getHeight();
	}
	
	public int getHalfWidth()
	{
		return this.getWidth() / 2;
	}
	
	public int getHalfHeight()
	{
		return this.getHeight() / 2;
	}

	public String getColor()
	{
		return this.color;
	}
	
	public Rectangle getRectangle()
	{
		int x = (int)(this.getX() - this.getHalfWidth());
		int y = (int)(this.getY() - this.getHalfHeight());
		int width = this.getWidth();
		int height = this.getHeight();
		
		return new Rectangle(x, y, width, height);
	}
}