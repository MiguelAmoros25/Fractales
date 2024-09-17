import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalMandelbrot extends JPanel {
    private final int ancho = 900;
    private final int alto = 900;
    private final int maxIteraciones = 150;

    public FractalMandelbrot() {
        setPreferredSize(new java.awt.Dimension(ancho, alto));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                double cx = (x - ancho / 2) * 4.0 / ancho;
                double cy = (y - alto / 2) * 4.0 / alto;

                int iteraciones = calcularIteraciones(cx, cy);

                if (iteraciones < maxIteraciones) {
                    imagen.setRGB(x, y, getColor(iteraciones));
                } else {
                    imagen.setRGB(x, y, 0x000000); // Negro
                }
            }
        }

        g2d.drawImage(imagen, 0, 0, null);
    }

    private int calcularIteraciones(double cx, double cy) {
        double zx = 0;
        double zy = 0;
        int iteraciones = 0;

        while (iteraciones < maxIteraciones && (zx * zx + zy * zy) < 4) {
            double temp = zx * zx - zy * zy + cx;
            zy = 2 * zx * zy + cy;
            zx = temp;
            iteraciones++;
        }

        return iteraciones;
    }

    private int getColor(int iteraciones) {
        int r = (int) (iteraciones * 18 % 300);
        int g = (int) (iteraciones * 20 % 300);
        int b = (int) (iteraciones * 43 % 300);

        return (r << 18) | (g << 20) | b;
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Fractal de Mandelbrot");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new FractalMandelbrot());
        ventana.pack();
        ventana.setVisible(true);
    }
}
