package LinealRegression;

import Table.*;
import Interfaces.Algorithm;
import java.util.Iterator;
import java.util.List;
import Operations.*;

public class LinealRegression implements Algorithm<Table, Double, Double>{

    private Double alfa;
    private Double beta;

    public LinealRegression(){

        alfa = 0.0;
        beta = 0.0;
    }

    private Double mean(List<Double> array){
        Double sum = 0.0;

        for (Double element : array){
            sum += element;
        }

        return sum/array.size();

    }

    private Double createAlfa(Double meanX, Double meanY, List<Double> listX, List<Double> listY){

        Iterator<Double> iterX = listX.listIterator();
        Iterator<Double> iterY = listY.listIterator();

        double numerator = 0.0;
        double denominator = 0.0;

        while(iterX.hasNext()){
            double X = iterX.next();
            double Y = iterY.next();

            numerator += (X - meanX) * (Y - meanY);
            denominator += Math.pow(X - meanX, 2);

        }

        return numerator/denominator;
    }

    private Double createBeta(Double meanX, Double meanY, Double alfa){

        return meanY - alfa * meanX;
    }


    public void train(Table table){

        List<Double> colX = table.getColumnAt(0);
        List<Double> colY = table.getColumnAt(1);

        Double meanX = Operations.mean(colX);
        Double meanY = Operations.mean(colY);

        alfa = createAlfa(meanX, meanY, colX, colY);
        beta = createBeta(meanX, meanY, alfa);

    }


    public Double estimate(Double sample){
        return alfa*sample + beta;
    }

    public Double getAlfa() {
        return alfa;
    }

    public Double getBeta() {
        return beta;
    }

    public void setAlfa(Double a){
        alfa = a;
    }

    public void setBeta(Double b){
        beta = b;
    }
}
