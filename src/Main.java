import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        XYSeries seriesStandard = new XYSeries("Algoritmo Padrão");
        XYSeries seriesStrassen = new XYSeries("Algoritmo de Strassen");

        int[] matrixSizes = {10, 20, 30, 40, 50};
        for (int size : matrixSizes) {
            double standardExecutionTime = MatrixMultiplicationTimePlot.measureExecutionTime(size);
            double strassenExecutionTime = StrassenMatrixMultiplication.measureExecutionTime(size);
            seriesStandard.add(size, standardExecutionTime);
            seriesStrassen.add(size, strassenExecutionTime);
        }

        new Grafico(seriesStandard, seriesStrassen);
    }
}
