package api;

import api.dto.*;
import cache.TestCache;
import cache.TestCacheKey;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.Constants;
import utils.PropertyLoader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiRestClient {

    @Test
    public void asendGetGuestTokenRequest() {

        GuestTokenRequestBodyDTO guestTokenRequestBody = GuestTokenRequestBodyDTO
                .builder().grantType("client_credentials")
                .scope("guest:default").build();

        Response response = given(RequestSpecifications.basicSpec)
                .auth()
                .preemptive()
                .basic(PropertyLoader.getPropertyValue(Constants.API_PROPERTIES, "username"),
                        PropertyLoader.getPropertyValue(Constants.API_PROPERTIES, "password"))
                .body(guestTokenRequestBody)
                .log().all()
                .when().post(Constants.ACCESS_TOKEN_REQUEST_ENDPOINT)
                .then().log().all().statusCode(200)
                .and().extract().response()
                .then().assertThat().statusCode(200)
                .and().extract().response();

        GuestTokenResponseBodyDTO guestTokenResponseBody = response.as(GuestTokenResponseBodyDTO.class);

        TestCache.putInTestCacheMap(TestCacheKey.ACCESS_TOKEN.toString(), guestTokenResponseBody.getAccessToken());
    }

    @Test
    public void bsendRegisterNewPlayerRequest() {

        Faker faker = new Faker();
        String username = faker.name().username().replace(".", "");
        String password = faker.internet().password(9, 10) + "=";
        String email = username + "@example.com";
        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        TestCache.putInTestCacheMap(TestCacheKey.USERNAME.toString(), username);
        TestCache.putInTestCacheMap(TestCacheKey.PASSWORD_CHANGE.toString(), password);
        TestCache.putInTestCacheMap(TestCacheKey.EMAIL.toString(), email);
        TestCache.putInTestCacheMap(TestCacheKey.NAME.toString(), name);
        TestCache.putInTestCacheMap(TestCacheKey.SURNAME.toString(), surname);

        RegisterNewPlayerRequestBodyDTO registerNewPlayerRequestBody = RegisterNewPlayerRequestBodyDTO.builder()
                .username(username)
                .passwordChange(password)
                .passwordRepeat(password)
                .email(email)
                .name(name)
                .surname(surname)
                .build();

        Response response = given(RequestSpecifications.basicSpec)
                .header("Authorization", "Bearer " + TestCache.getFromTestCacheMap(TestCacheKey.ACCESS_TOKEN.toString()))
                .body(registerNewPlayerRequestBody)
                .log().all()
                .post(Constants.PLAYER_REQUEST_ENDPOINT)
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("username", equalTo(username))
                .assertThat().body("email", equalTo(email))
                .assertThat().body("name", equalTo(name))
                .assertThat().body("surname", equalTo(surname))
                .and().extract().response();

        RegisterNewPlayerResponseBodyDTO registerNewPlayerResponseBody = response.as(RegisterNewPlayerResponseBodyDTO.class);

        TestCache.putInTestCacheMap(TestCacheKey.PLAYER_ID.toString(), registerNewPlayerResponseBody.getId());
    }

    @Test
    public void csendAuthorizationUnderPlayerRequest() {

        AuthorizationUnderPlayerRequestBodyDTO authorizationUnderPlayerRequestBody = AuthorizationUnderPlayerRequestBodyDTO
                .builder().grantType("password")
                .username((String) TestCache.getFromTestCacheMap(TestCacheKey.USERNAME.toString()))
                .password((String) TestCache.getFromTestCacheMap(TestCacheKey.PASSWORD_CHANGE.toString()))
                .build();

        Response response = given(RequestSpecifications.basicSpec)
                .auth()
                .preemptive()
                .basic(PropertyLoader.getPropertyValue(Constants.API_PROPERTIES, "username"),
                        PropertyLoader.getPropertyValue(Constants.API_PROPERTIES, "password"))
                .body(authorizationUnderPlayerRequestBody)
                .log().all()
                .when().post(Constants.ACCESS_TOKEN_REQUEST_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200)
                .and().extract().response();
        response.prettyPrint();

        AuthorizationUnderPlayerResponseBodyDTO authorizationUnderPlayerResponseBody =
                response.as(AuthorizationUnderPlayerResponseBodyDTO.class);

        TestCache.putInTestCacheMap(TestCacheKey.ACCESS_TOKEN.toString(), authorizationUnderPlayerResponseBody.getAccessToken());
    }

    @Test
    public void dsendGetSinglePlayerProfile() {

        Response response = given(RequestSpecifications.basicSpec)
                .header("Authorization", "Bearer " + TestCache.getFromTestCacheMap(TestCacheKey.ACCESS_TOKEN.toString()))
                .get(Constants.PLAYER_REQUEST_ENDPOINT + "/" + TestCache.getFromTestCacheMap(TestCacheKey.PLAYER_ID.toString()))
                .then().assertThat().statusCode(200)
                .assertThat().body("id", equalTo(TestCache.getFromTestCacheMap(TestCacheKey.PLAYER_ID.toString())))
                .assertThat().body("username", equalTo(TestCache.getFromTestCacheMap(TestCacheKey.USERNAME.toString())))
                .assertThat().body("name", equalTo(TestCache.getFromTestCacheMap(TestCacheKey.NAME.toString())))
                .assertThat().body("surname", equalTo(TestCache.getFromTestCacheMap(TestCacheKey.SURNAME.toString())))
                .and().extract().response();
        response.prettyPrint();
    }

    @Test
    public void esendGetOtherPlayerProfile() {

        Response response = given(RequestSpecifications.basicSpec)
                .header("Authorization", "Bearer " + TestCache.getFromTestCacheMap(TestCacheKey.ACCESS_TOKEN.toString()))
                .get(Constants.PLAYER_REQUEST_ENDPOINT + "/" + RandomStringUtils.randomNumeric(10))
                .then().assertThat().statusCode(404)
                .extract().response();
        response.prettyPrint();
    }
}
