import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
public class DrawLinesController {
	private int currentYear=-1;
	private Weather weather=new Weather();
	private double xPadding;
	private double yPadding;
	@FXML
	private Canvas canvas;
	@FXML
	void nextYearButtonPressed(ActionEvent event) {
		xPadding=100;
		yPadding=100;
		this.advanceYear();
		drawGraph();
		drawMonths();

	}
	private void advanceYear()
	{
		this.currentYear+=1;
		if(this.currentYear==5)
		{
			this.currentYear=0;
		}

	}
	private  void drawMonths() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		double xPlace=60;
		Color barColor=Color.GRAY;
		for(int month=1;month<13;month++)
		{
			if(weather.getTemp(currentYear, month)==weather.maxWeather(currentYear))
			{
				barColor=Color.RED;
			}
			else if(weather.getTemp(currentYear, month)==weather.minWeather(currentYear)) {
				barColor=Color.BLUE;
			}
			else {
				barColor=Color.GRAY;
			}
			xPlace+=canvas.getWidth()/16;
			gc.setFill(barColor);
			gc.fillRect(xPlace,canvas.getHeight()-yPadding-10*weather.getTemp(currentYear, month), 30, 10*weather.getTemp(currentYear, month));
		}
	}
	private void drawGraph() {
		double xPlace=70;
		double yPlace=70;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//y axis
		gc.strokeLine(xPadding,yPadding,xPadding,canvas.getHeight()-yPadding);
		//x axis
		gc.strokeLine(xPadding,canvas.getHeight()-yPadding,canvas.getWidth()-xPadding,canvas.getHeight()-yPadding);
		//draw text
		gc.strokeText(String.valueOf(weather.getYear(currentYear)),canvas.getWidth()*0.5,yPadding-30);
		gc.strokeText("Tempeture",xPlace,yPadding-10);
		gc.strokeText("Months",canvas.getWidth()-xPadding+10,canvas.getHeight()-yPadding+3);
		for(int month=1;month<13;month++)
		{
			xPlace+=canvas.getWidth()/16;
			gc.strokeText(String.valueOf(month),xPlace,canvas.getHeight()-65);
		}
		for(int scale=10;scale<60;scale+=10)
		{
			yPlace+=canvas.getHeight()/7;
			gc.strokeText(String.valueOf(scale),70,canvas.getHeight()-yPlace);
		}
	}
}