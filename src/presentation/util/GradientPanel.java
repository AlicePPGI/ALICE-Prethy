package presentation.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

	private static final long serialVersionUID = 630145374810661552L;

	public final static int HORIZONTAL = 0;
	public final static int VERTICAL = 1;
	public final static int DIAGONAL_LEFT = 2;
	public final static int DIAGONAL_RIGHT = 3;

    private Color startColor = Color.GRAY;  
    private Color endColor = Color.LIGHT_GRAY;  
	private int direction = DIAGONAL_LEFT;
	private boolean cyclic = true;
	private int maxLength;
	
	public GradientPanel() {
		this(DIAGONAL_LEFT);
	}
	
	public GradientPanel(int direction) {
		super(new BorderLayout());
		setOpaque(false);
		this.direction = direction;
	}
	
	public GradientPanel(LayoutManager layoutManager) {
		super(layoutManager);
		setOpaque(false);
		this.direction = DIAGONAL_LEFT;
	}

	public GradientPanel(Color aStart, Color aEnd) {
		super();
		this.startColor = aStart;
		this.endColor = aEnd;
		setOpaque(false);
	}

	public GradientPanel(Color aStart, Color aEnd, int direction) {
		super();
		this.startColor = aStart;
		this.endColor = aEnd;
		this.direction = direction;
		setOpaque(false);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
        int oldDirection = this.direction;  
		this.direction = direction;
        super.firePropertyChange("direction", oldDirection, direction);  
        repaint();  
	}

	public boolean isCyclic() {
		return cyclic;
	}

	public void setCyclic(boolean cyclic) {
		this.cyclic = cyclic;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

    public Color getEndColor() {  
        return endColor;  
    }  

    public void setEndColor(Color aColor) {  
        Color oldEndColor = endColor;  
        endColor = aColor;  
        super.firePropertyChange("endColor", oldEndColor, endColor);  
        repaint();  
    }  

    public Color getStartColor() {  
        return startColor;  
    }  

    public void setStartColor(Color aColor) {  
        Color oldStartColor = endColor;  
        startColor = aColor;  
        super.firePropertyChange("startColor", oldStartColor, startColor);  
        repaint();  
    }  

    public void reposition(){  
        this.repaint();  
    }  

    public void paintComponent(Graphics g) {
    	if (isOpaque()) {
    		super.paintComponent(g);
    		return;
    	}
    	int width = getWidth();
    	int height = getHeight();
    	GradientPaint paint = null;
    	Color sc = this.startColor;
    	Color ec = this.endColor;
    	
    	switch (this.direction) {
	    	case HORIZONTAL:
	    		paint = new GradientPaint(0, height /2, sc, width, height / 2, ec, cyclic);
	    		break;
	    	case VERTICAL:
	    		paint = new GradientPaint(width / 2, 0, sc, width / 2, maxLength > 0 ? maxLength : height, ec, cyclic);
	    		break;
	    	case DIAGONAL_LEFT:
	    		paint = new GradientPaint(0, 0, sc, width, height, ec, cyclic);
	    		break;
	    	case DIAGONAL_RIGHT:
	    		paint = new GradientPaint(width, 0, sc, 0, height, ec, cyclic);
	    		break;
    	}
    	if (paint == null) {
    		throw new RuntimeException("Invalid direction specified in GradientPanel");
    	}
    	Graphics2D g2d = (Graphics2D) g;
    	Paint oldPaint = g2d.getPaint();
    	g2d.setPaint(paint);
    	g2d.fillRect(0, 0, width, height);
    	g2d.setPaint(oldPaint);
    	super.paintComponent(g);
    }
}
