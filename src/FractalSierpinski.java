import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalSierpinski extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarFractalSierpinski(g2d, 55, 55, 200, 200, 7);
    }

    private void dibujarFractalSierpinski(Graphics2D g, int x1, int y1, int x2, int y2, int nivel) {
        if (nivel == 0) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            int x3 = (x1 + x2) / 2;
            int y3 = (y1 + y2) / 2;
            dibujarFractalSierpinski(g, x1, y1, x3, y3, nivel - 1);
            dibujarFractalSierpinski(g, x3, y3, x2, y2, nivel - 1);
            dibujarFractalSierpinski(g, x1, y3, x3, y2, nivel - 1);
            dibujarFractalSierpinski(g, x3, y1, x2, y3, nivel - 1);
        }
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Fractal de Sierpinski");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new FractalSierpinski());
        ventana.setSize(400, 400);
        ventana.setVisible(true);
    }
}
