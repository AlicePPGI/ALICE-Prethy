package presentation.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;  

/** 
* 
* @author Marcilio 
*/  
public class CurvedGradientPanel extends JPanel {  
  
  
	private static final long serialVersionUID = -3241115924204301070L;
	
	public static final int VERTICAL = 0;  
    public static final int HORIZONTAL = 1;  
    private int roundRectSize = 15;  
  
    /** 
     * The start color of the gradient 
     */  
    private Color startColor = new Color(114, 166, 252);  
  
    /** 
     * The end color of the gradient 
     */  
    private Color endColor = new Color(255, 255, 255);  
  
    /** 
     * The direction of the gradient 
     */  
    private int direction = VERTICAL;  

    // ------------------------------------------------------------------------------------------------------------------  
    // Constructors and getter/setter methods  
    // ------------------------------------------------------------------------------------------------------------------  
  
    /** 
     * Create a default SimpleGradientPanel instance. 
     */  
    public CurvedGradientPanel() {  
        super();  
        startColor = Color.DARK_GRAY;  
        endColor = Color.white;  
        direction = 3;  
        roundRectSize = 0;  
    }  
  
    /** 
     * Create a SimpleGradientPanel with the given start and end colors. 
     * 
     * @param aStart 
     *            The start color for the gradient. 
     * @param aEnd 
     *            The end color for the gradient. 
     */  
    public CurvedGradientPanel(Color aStart, Color aEnd) {  
        super();  
        startColor = aStart;  
        endColor = aEnd;  
    }  
  
    /** 
     * Create a SimpleGradientPanel with the given start and end colors. 
     * 
     * @param aStart 
     *            The start color for the gradient. 
     * @param aEnd 
     *            The end color for the gradient. 
     * @param aDirection 
     *            The direction of the gradient. 
     */  
    public CurvedGradientPanel(Color aStart, Color aEnd, int aDirection) {  
        super();  
        startColor = aStart;  
        endColor = aEnd;  
        direction = aDirection;  
    }  
  
    /** 
     * Create a SimpleGradientPanel with the given start and end colors. 
     * 
     * @param aStart 
     *            The start color for the gradient. 
     * @param aEnd 
     *            The end color for the gradient. 
     * @param aDirection 
     *            The direction of the gradient. 
     * @param aRoundRectSise 
     *            The rounded size of the Rectangle. 
     */  
    public CurvedGradientPanel(Color aStart, Color aEnd, int aDirection, int aRoundRectSise) {  
        super();  
        startColor = aStart;  
        endColor = aEnd;  
        direction = aDirection;  
        roundRectSize = aRoundRectSise;  
    }
 
    /** 
     * Get the ending color of the gradient. 
     */  
    public Color getEndColor() {  
        return endColor;  
    }  
  
    /** 
     * Set the ending color to use. 
     * 
     * @param aColor 
     *            The color to use. 
     */  
    public void setEndColor(Color aColor) {  
        Color oldEndColor = endColor;  
        endColor = aColor;  
        super.firePropertyChange("endColor", oldEndColor, endColor);  
        repaint();  
    }  
  
    /** 
     * Get the start color of the gradient 
     */  
    public Color getStartColor() {  
        return startColor;  
    }  
  
    /** 
     * Set the starting color. 
     * 
     * @param aColor 
     *            The color to use 
     */  
    public void setStartColor(Color aColor) {  
        Color oldStartColor = endColor;  
        startColor = aColor;  
        super.firePropertyChange("startColor", oldStartColor, startColor);  
        repaint();  
    }  
  
    /** 
     * Get the direction (vertical or horizontal) of the gradient. 
     */  
    public int getDirection() {  
        return direction;  
    }  
  
    /** 
     * Set the direction 
     * 
     * @param aDirection 
     *            The direction of the gradient 
     */  
    public void setDirection(int aDirection) {  
        int oldDirection = direction;  
        direction = aDirection;  
        super.firePropertyChange("direction", oldDirection, aDirection);  
        repaint();  
    }  
  
    /** 
     * Reposiciona os botoes de controle toda vez que alterar o tamanho do Form 
     */  
    public void reposition(){  
        this.repaint();  
    }  
  
    // ------------------------------------------------------------------------------------------------------------------  
    // Custom painting methods  
    // ------------------------------------------------------------------------------------------------------------------  
  
    /** 
     * Override the default paintComponent method to paint the gradient in the 
     * panel. 
     * 
     * @param g 
     */  
    public void paintComponent(Graphics g) {  
        Dimension dim = getSize();  
        Graphics2D g2 = (Graphics2D) g;  
        Insets inset = getInsets();  
        int vWidth = dim.width - (inset.left + inset.right);  
        int vHeight = dim.height - (inset.top + inset.bottom);
  
        if (direction == HORIZONTAL) {  
            paintHorizontalGradient(g2, inset.left, inset.top, vWidth, vHeight, dim.width);  
        } else {  
            paintVerticalGradient(g2, inset.left, inset.top, vWidth, vHeight, dim.height);  
        }
    }  
  
    /** 
     * Paints a vertical gradient background from the start color to the end 
     * color. 
     */  
    private void paintVerticalGradient(Graphics2D g2d, int x, int y, int w, int h, int height) {  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
//        GradientPaint p1;  
//        GradientPaint p2;  
        GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);  
        g2d.setPaint(GP);  
        g2d.setPaint(Color.BLACK);  
        g2d.drawRoundRect(0, 0, w - 1, h - 1, roundRectSize, roundRectSize);  
        g2d.setPaint(GP);  
        g2d.fillRoundRect(1, 1, w - 2, h - 2, roundRectSize, roundRectSize);  
        g2d.setPaint(Color.WHITE);  
        g2d.drawRoundRect(1, 1, w - 3, h - 3, roundRectSize, roundRectSize);  
        g2d.setPaint(new Color(255, 255, 255, 100));  
        GeneralPath path = new GeneralPath();  
        path.moveTo(-5, 1);  
        path.quadTo(w / 2, h / 2, w, 0);  
        path.closePath();  
        g2d.fill(path);  
    }  
  
    /** 
     * Paints a horizontal gradient background from the start color to the end 
     * color. 
     */  
    private void paintHorizontalGradient(Graphics2D g2d, int x, int y, int w, int h, int width) {  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
//        GradientPaint p1;  
//        GradientPaint p2;  
        GradientPaint GP = new GradientPaint(0, 0, startColor, w, 0, endColor, true);  
        g2d.setPaint(GP);  
        g2d.setPaint(Color.BLACK);  
        g2d.drawRoundRect(0, 0, w - 1, h - 1, 10, 10);  
        g2d.setPaint(GP);  
        g2d.fillRoundRect(1, 1, w - 2, h - 2, 10, 10);  
        g2d.setPaint(new Color(255, 255, 255, 100));  
        GeneralPath path = new GeneralPath();  
        path.moveTo(-5, 1);  
        path.quadTo(w / 2, h / 2, w, 0);  
        path.closePath();  
        g2d.fill(path);  
    }  

}
