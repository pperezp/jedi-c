/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.grafico;

import cl.grafico.modelo.DatoGrafico;
import cl.grafico.modelo.Ente;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.TableOrder;

/**
 *
 * @author Pato Pérez
 */
public class Grafico {
    private List<DatoGrafico> datos;
    private String titulo;
    private String XLabel;
    private String YLabel;
    private Tipo tipo;

    
    public static enum Tipo{
        BARRA_3D, LINEAL_3D, BARRA, LINEAL , AREA/*, TORTA*/;
        @Override
        public String toString(){
            return this.name();
        }
    }
    
    public Grafico(List<DatoGrafico> datos, String titulo, String XLabel, String YLabel, Tipo tipo) {
        this.datos = datos;
        this.titulo = titulo;
        this.XLabel = XLabel;
        this.YLabel = YLabel;
        this.tipo = tipo;
    }

    public String getXLabel() {
        return XLabel;
    }

    public void setXLabel(String XLabel) {
        this.XLabel = XLabel;
    }

    public String getYLabel() {
        return YLabel;
    }

    public void setYLabel(String YLabel) {
        this.YLabel = YLabel;
    }

    public List<DatoGrafico> getDatos() {
        return datos;
    }

    public void setDatos(List<DatoGrafico> datos) {
        this.datos = datos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public ChartPanel getGraficoComoPanel() throws IOException{
        JFreeChart chart = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(DatoGrafico dato : this.getDatos()){
            for(Ente ent : dato.getEntes()){
                dataset.setValue(ent.getValorY(), ent.getNombre(), dato.getxLabel());
            }
        }
//        DefaultPieDataset ds = new DefaultPieDataset();
//        for(DatoGrafico dato : this.getDatos()){
//            for(Ente ent : dato.getEntes()){
//                ds.setValue(dato.getxLabel(),ent.getValorY());
//            }
//        }
        switch(this.tipo){
            case BARRA:{
                chart = ChartFactory.createBarChart(this.getTitulo(), this.XLabel,
                this.getYLabel(), dataset, PlotOrientation.VERTICAL, true,
                true, false);
                break;
            }case AREA:{
                chart = ChartFactory.createAreaChart(this.getTitulo(), this.XLabel,
                this.getYLabel(), dataset, PlotOrientation.VERTICAL, true,
                true, false);
                break;
            }case LINEAL:{
                chart = ChartFactory.createLineChart(this.getTitulo(), this.XLabel,
                this.getYLabel(), dataset, PlotOrientation.VERTICAL, true,
                true, false);
                break;
            }
                case LINEAL_3D:{
                chart = ChartFactory.createLineChart3D(this.getTitulo(), this.XLabel,
                this.getYLabel(), dataset, PlotOrientation.VERTICAL, true,
                true, false);
                break;
            }case BARRA_3D:{
                chart = ChartFactory.createBarChart3D(this.getTitulo(), this.XLabel,
                this.getYLabel(), dataset, PlotOrientation.VERTICAL, true,
                true, false);
                break;
            }
//                case TORTA:{
//                chart = ChartFactory.createMultiplePieChart3D(this.getTitulo(), dataset,TableOrder.BY_COLUMN, true,
//                true, false);
//                break;
//            }
        }
        
//        DefaultPieDataset dps = new DefaultPieDataset();
//        chart = ChartFactory.createPieChart(titulo, null, true, true, true);
        chart.getPlot().setBackgroundPaint(Color.white);
        BufferedImage img = ImageIO.read(getClass().getResource("/imagenes/fondoGrafico.png"));
        chart.setBackgroundImage(img);
//        chart.getCategoryPlot().setBackgroundPaint(Color.white);
//        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(0,89,171));
//        chart.getCategoryPlot().getRenderer().setSeriesPaint(1, new Color(210,32,32));
//        chart.getCategoryPlot().getRenderer().setSeriesPaint(2, new Color(87,198,210));
//        chart.getCategoryPlot().getRenderer().setSeriesPaint(3, new Color(255,245,106));
        return new ChartPanel(chart);
    }
}
