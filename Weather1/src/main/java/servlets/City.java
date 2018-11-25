package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;



/**
 * Servlet implementation class City
 */
@WebServlet("/City")
public class City extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 OWM owm = new OWM("d6d1c071278a740d791b9f901de84673").setUnit(Unit.METRIC);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public City() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 CurrentWeather cityWeather=CheckCity(request.getParameter("city"));
		//CHECK CITY 
		 if(cityWeather==null)
			 request.getRequestDispatcher("index.jsp").forward(request, response);
   
		  response.setContentType("text/html");
	    //PRINT WEATHER DATA
		  out.println(WriteWeatherData(cityWeather));
		  
		  out.println("<a href='index.jsp'>Powrot</a>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	///////////////////////////////////FUNKCJE POMOCNICZE
	private String WriteWeatherData (CurrentWeather cwd) {
		StringBuffer sb=new StringBuffer();
		String s;
		if (cwd.hasRespCode() && cwd.getRespCode() == 200) {

            // checking if city name is available
            if (cwd.hasCityName())    
                sb.append("<p>Miasto: "+cwd.getCityName()+"</p>");
          
            // checking if cloud data is available
            if (cwd.hasCloudData() && cwd.getCloudData().hasCloudiness())           
            	sb.append("<p>Poziom zachmurzenia: "+cwd.getCloudData().getCloudiness()+" %</p>");
        
            // checking if temperature data is available
            if (cwd.hasMainData() && cwd.getMainData().hasTemp()) 
            	sb.append("<p>Temperatura: "+cwd.getMainData().getTemp()+" °C</p>");
        
            // checking if pressure data is available
            if (cwd.hasMainData() && cwd.getMainData().hasPressure()) 
            	sb.append("<p>Ciesnienie: "+cwd.getMainData().getPressure()+" hPa</p>");
            
            // checking if wind data is available
            if (cwd.hasWindData() && cwd.getWindData().hasSpeed()) 
            	sb.append("<p>Predkosc wiatru: "+cwd.getWindData().getSpeed()+" m/s</p>");
            
            s=sb.toString();
        }
		else
			s="Sorry some error have occurred";
		
		return s;
		
	}
	
	private CurrentWeather CheckCity (String cityName) {
		CurrentWeather cityWeather= null;
		 try {
		 switch (cityName) {
         case "warszawa":  	cityWeather = owm.currentWeatherByCityName("Warsaw, PL");
                  break;
         case "gdansk":  	cityWeather = owm.currentWeatherByCityName("Gdansk, PL");
                  break;
         case "krakow":  	cityWeather = owm.currentWeatherByCityName("Krakow, PL");
                  break;
         case "wroclaw":  	cityWeather = owm.currentWeatherByCityName("Wroclaw, PL");
                  break;
         case "poznan":  	cityWeather = owm.currentWeatherByCityName("Poznan, PL");
                  break;
         case "lodz":  		cityWeather = owm.currentWeatherByCityName("Lodz, PL");
                  break;
         case "katowice":  	cityWeather = owm.currentWeatherByCityName("Katowice, PL");
                  break;
     }
	} catch (APIException e) {
        e.printStackTrace();
    }
			
		return cityWeather;
	}

}
