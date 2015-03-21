package Doctorado;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class GraficaEntrenamiento {

	Chart2D chart;
	ITrace2D trace;
	    GraficaEntrenamiento(){
		    // Create a chart:  
		    chart = new Chart2D();
		    // Create an ITrace: 
		    trace = new Trace2DSimple(); 
		    chart.addTrace(trace);    
			   
		    JFrame frame = new JFrame("Cálculo del error");
		    frame.getContentPane().add(chart);
		    frame.setSize(600,400);
		   
		    frame.setVisible(true);
		  }
	 public void pinta(int x, int y)
	 {
		 trace.addPoint(x,y);
	 }
}
