import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalKoch extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarFractalKoch(g2d, 400, 400, 600, 600, 10);
    }

    private void dibujarFractalKoch(Graphics2D g, int x1, int y1, int x2, int y2, int nivel) {
        if (nivel == 0) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            int x3 = (x1 + x2) / 2;
            int y3 = (y1 + y2) / 2;
            int x4 = x3 + (x2 - x1) / 3;
            int y4 = y3 + (y2 - y1) / 3;
            dibujarFractalKoch(g, x1, y1, x3, y3, nivel - 1);
            dibujarFractalKoch(g, x3, y3, x4, y4, nivel - 1);
            dibujarFractalKoch(g, x4, y4, x2, y2, nivel - 1);
        }
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Fractal de Koch");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new FractalKoch());
        ventana.setSize(400, 400);
        ventana.setVisible(true);
    }
}