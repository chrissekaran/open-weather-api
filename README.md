##Weather API

###Problem
A mobile app queries a 3rd party API for weather data, but after the app becomes popular the rate limit of
the API is exceeded and we need to limit the number of requests made to the 3rd party API.

It is decided to introduce an integration layer between the app and the 3rd party API so that the app will no
longer call the 3rd party API directly, but instead call the integration layer API that will ensure the rate limit
towards the 3rd party is not exceeded while still serving data to the apps.

Information that needs to be shown in the app (and thus available via the API):
- A list of the user’s favourite locations where the temperature will be above a certain temperature the
next day.
- A list of temperatures for the next 5 days in one specific location.

###App API

The app facing API should be RESTful and return data in JSON format.

The API could have an interface like the following:

```
    /weather/summary?unit=<celsius|fahrenheit>&locations=<comma separated list of location ids>
    
    /weather/locations/<location_id>
```

It is not required to store the user’s favorites server side - the client will pass those as part of the request.

###3rd Party API

You should use OpenWeatherMap as the 3rd party weather API. Imagine we are only allowed to call the
API 10,000 times per day, but we want to support a much larger number of users.

You can sign up for free and get 60 calls per minute. For more information, see:
https://openweathermap.org/api

###Things to consider
- Keeping a sensible structure that can be expanded if the project should grow in the future. Apply
design patterns to help with this.
- Find a good way to ensure we stay within the rate limit of the 3rd party without compromising user
experience.
- If time permits try to handle different error scenarios and cases of invalid input.

###Things you don’t need to consider
- Hosting/deployment
- Documentation