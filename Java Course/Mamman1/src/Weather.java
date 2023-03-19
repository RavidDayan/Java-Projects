
public class Weather {
	private double[][] weatherCalander=new double[5][13];
	//constructor
	public Weather() {
		this.insertYear(2017);
		this.insertWeather();
	}
	public Weather(double year) {
		this.insertYear(year);
		this.insertWeather();
	}
	//methods
	public double getTemp(int year,int month)
	{
		return this.weatherCalander[year][month];
	}
	public int getYear(int year)
	{

		return (int)this.weatherCalander[year][0];
	}
	private  void insertWeather()
	{
		for(int i=0;i<this.weatherCalander.length;i++) {
			for(int j=1;j<this.weatherCalander[0].length;j++) {
				this.weatherCalander[i][j]=generateRandomTemp();
			}
		}
	}
	private void insertYear(double year) 
	{
		for(int i=0;i<this.weatherCalander.length;i++) {
			this.weatherCalander[i][0]=year+i;
		}
	}
	private static double generateRandomTemp(){
		return Math.random()*50;
	}
	public double maxWeather(int year){
		double max=this.weatherCalander[year][1];
		for(int month=1;month<12;month++) {
			if(this.weatherCalander[year][month]>max)
				max=this.weatherCalander[year][month];
		}
		return max;
	}
	public double minWeather(int year){
		double min=this.weatherCalander[year][1];
		for(int month=1;month<13;month++) {
			if(this.weatherCalander[year][month]<min) 
				min=this.weatherCalander[year][month];
		}
		return min;
	}
}