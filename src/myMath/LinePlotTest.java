package myMath;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.filters.Convolution;
import de.erichseifert.gral.data.filters.Filter.Mode;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.plots.XYPlot.XYPlotArea2D;
import de.erichseifert.gral.plots.points.SizeablePointRenderer;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.graphics.Insets2D;
import myMath.Polynom;
import myMath.Polynom_able;

public class LinePlotTest extends JFrame {
	public LinePlotTest() {
		DataTable data=new DataTable(Double.class,Double.class);//this is an object the collect all the points
		DataTable data1=new DataTable(Double.class,Double.class);//this is an object the collect all the MaxMin points
		Polynom p1=new Polynom("0.2x^4-1.5x^3+3.0x^2-x^1-5");
		Polynom_able p2 = p1.copy();
		p2 = p1.derivative();
		System.out.println(p2);
		for (double x=-2.0;x<=6.0;x+=0.25)//this is a for to get all the points in the range
		{
			if(p2.f(x)>0 &&p2.f(x+0.2)<0 || p2.f(x)<0 && p2.f(x+0.2)>0) {//if the current point is above the x Axis and the next one is below the x axis
				double i = p2.root(x,x+0.6,0.00000000001);//finding the extremum
				data1.add(i,p1.f(i));
			}
		

			double y=p1.f(x);//geting the point of the polynom
			data.add(x,y);	
			System.out.println("X:"+x + "," + "Y:" +y);
		}
		
		DataSource filter = new Convolution(data, null, Mode.REPEAT, 1);//to cover the points that arents extremum
		XYPlot plot = new XYPlot(filter,data1);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(filter, lines);
		plot.setPointRenderers(filter, null);
		Color color = new Color(0.0f, 0.0f, 0.0f);
		plot.getPointRenderers(data1).get(0).setColor(color.GREEN);
		
		// Draw a tick mark and a grid line every 10 units along x axis
		double insetsTop = 20.0,
			       insetsLeft = 60.0,
			       insetsBottom = 60.0,
			       insetsRight = 40.0;
			plot.setInsets(new Insets2D.Double(
			    insetsTop, insetsLeft, insetsBottom, insetsRight));
			plot.getTitle().setText("Graph of a Polynom");
			getContentPane().add(new InteractivePanel(plot), BorderLayout.CENTER);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setMinimumSize(getContentPane().getMinimumSize());
	        setSize(800, 500);
	        System.out.println("AREA OF POLYNOM:"+p1.areaUnderAxisX(-2.0, 6.0, 0.001));
	        

	}
	
	public static void main(String[] args) {

		LinePlotTest frame = new LinePlotTest();
		frame.setVisible(true);

	}
}

