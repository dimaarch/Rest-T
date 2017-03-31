package RestNG;

import java.util.HashMap;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.containsString;


public class Rest {
		
	//GET all {id} - http://swapi.co/api/planets/

	@Test (priority = 0,enabled = true)
	public void VerifyThatSiteIsUp() {
        given().when().get("http://swapi.co/api/planets/").then().statusCode(200);
    }
	
	@Test (priority = 1,enabled = true)
	public void VerifyPlanetsExpectedQuantity() {
        given().when().get("http://swapi.co/api/planets/61")
            .then().statusCode(200);
    }
	
	@Test (priority = 2,enabled = true)
	public void VerifyPlanetsNoMoreThan61() {
        given().when().get("http://swapi.co/api/planets/62")
            .then().statusCode(404);
    }
	
	@Test (priority = 3,enabled = true)
       public void VerifyPlanetCountString() {
       given().when().get("http://swapi.co/api/planets/").then()
           .body(containsString("61"));
   }
	
	
	@Test (priority = 4,enabled = true)
    public void VerifyTopLevelData() {
        given().when().get("http://swapi.co/api/planets/").then()
        .body("count",equalTo(61))
        .body("next",equalTo("http://swapi.co/api/planets/?page=2"))
        .body("previous",equalTo(null))
        .statusCode(200);
    }
		
//	@Test (priority = 5,enabled = true)
//    public void VerifySystemAddsPlanet() {
//        HashMap<String,String> Intro = new HashMap<>();
//        Intro.put("ProjectPurpose", "Test");
//
//        given()
//        .contentType("application/json")
//        .body(Intro)
//        .when().post("http://swapi.co/api/planets/").then()
//        .statusCode(200);
//    }
//	
//	
//	@Test (priority = 6,enabled = true)
//    public void VerifySystemDeletesPlanetJakku() {    
//        given().when().delete("http://swapi.co/api/planets/61/").then()
//        .statusCode(200);
//    }
	

	//Test cases for GET by {id} - http://swapi.co/api/planets/{id}
	
	 public static Response response;
	 public static String jsonAsString;
  
	
		 			
		@Test (priority = 5,enabled = true)
        public void Planet61NameAndUrl() {
        given().when().get("http://swapi.co/api/planets/61/").then()
        .body("name",equalTo("Jakku"))
        .body("url",equalTo("http://swapi.co/api/planets/61/"))
        .statusCode(200);
    }	
		
		@Test (priority = 6,enabled = true)
		  public void Planet61ResponseOutput() {			 			 
			  response =
			            given().when().
			                get("http://swapi.co/api/planets/61/").
			            then().
			            contentType(ContentType.JSON).  // check that the content type return from the API is JSON
			            extract().response(); // extract the response
			        // We convert the JSON response to a string, and save it in a variable called 'jsonAsString'
			        jsonAsString = response.asString();
			        System.out.println("Test Case: Planet61 Response Output: " + "\n" + jsonAsString + "\n");
		  }
}




