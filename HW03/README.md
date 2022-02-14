In this assignment you build a weather application using the OpenWeatherMap api.

API Details:
The current weather api should be used by this app:
1. Create an account at https://openweathermap.org/ and get an api key, which will be 
used to make the api calls.
2. You should use the Weather API, check the documentation for that is provided by the 
OpenWeatherMap APIs.

a. For the city and country information you should use the data provided in the 
support file.

b. All the retrieved information should be in the imperial units. Check the api 
documentation to retrieve the data in this unit. All data should be retrieved in JSON format.

c. The Current Weather API, check the documentation provided at https://openweathermap.org/current

d. The 5 Day Weather Forecast API, check the documentation provided at https://openweathermap.org/forecast5

e. To load the Weather Icon check the documentation provided at https://openweathermap.org/weather-conditions/

![image](https://user-images.githubusercontent.com/13596624/153963620-943b62a6-74db-43bc-baa3-510b32e10c1a.png)

**Part 1 : Cities List Screen**
The interface should be created to match the UI presented in Figure 1(a). The 
requirements are as follows:
1. This screen should use the cities list in the provided Data class.
a. You should list the city and country code as shown in Figure 1(a).
2. Clicking on a row item should start the Current Weather screen and should pass the 
required information to the Current Weather screen.

**Part 2 : Current Weather Screen**
The interface should be created to match the UI presented in Figure 1(b). The 
requirements are as follows:
1. This screen should retrieve the current weather information for the selected city. You 
should use the Current Weather API.
a. You should integrate OkHTTP library into your project and use it to make the API 
requests.
b. This screen should make the HTTP request, retrieve the weather information for 
the provided city, parse the JSON.
c. Create a Weather class that should hold the data returned in the JSON API 
response.
2. Display the weather information and shown in Figure 1(b). This screen should display 
the Weather Icon based on the retrieved weather information. You should load the 
weather icon based on the documentation provided at https://openweathermap.org/
weather-conditions
a. To load the image from a url use the Picasso library https://square.github.io/
picasso/
3. Clicking the “Forecast” button should show “Weather Forecast screen”, and should 
pass the required information to it.

**Part 3 : Weather Forecast Screen**
The interface should be created to match the UI presented in Figure 1(c). The 
requirements are as follows:
1. This screen should retrieve the weather forecast for the selected city. You should use 
the 5 Day Weather Forecast API.
a. You should use OKHttp to make the API request/
b. Create a Forecast class that should hold the data returned in the JSON API 
response. You should create an array of the retrieved Forecast items.
2. This screen should display the retrieved forecast in a ListView or RecyclerView as 
shown in Figure 1(c). Note that each cell should display, the date/time, weather icon, 
temperature, max temp, min temp, humidity, and the description. 
a. Use the Picasso library to load the weather icon for each of the row items. https://
square.github.io/picasso
