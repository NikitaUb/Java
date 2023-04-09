import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


public class FractalExplorer {
    private int Size;
    private JImageDisplay display;

    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    public FractalExplorer(int size) {
        Size = size;
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
    }

    public void createAndShowGUI() {
        display = new JImageDisplay(Size, Size);
        //display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal");
        JButton button = new JButton("Clear");
        frame.add(display, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void drawFractal() {

        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, Size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, Size, y);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) {
                    display.clearImage();
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }

            }
        }
        display.repaint();
    }
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();

            if (command.equals("Clear"))
            {
                fractal.getInitialRange(range);
                drawFractal();
            }

        }
    }
    private class MouseHandler extends MouseAdapter
    {
        /**
         * Когда обработчик получает событие щелчка мыши, он отображает пиксель-
         * координаты щелчка в области фрактала, который
         * отображается, а затем вызывает функцию RecenterAndZoomRange () генератора
         * метод с координатами, по которым был выполнен щелчок, и шкалой 0,5.
         */
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, Size, x);

            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, Size, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    /**
     * Статический метод main() для запуска FractalExplorer. Инициализирует новый
     * Экземпляр FractalExplorer с размером дисплея 600, вызывает
     * createAndShowGU () в объекте проводника, а затем вызывает
     * drawFractal() в проводнике, чтобы увидеть исходный вид.
     */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}

