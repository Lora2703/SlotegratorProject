package api.dto;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Constants;
import utils.PropertyLoader;

public class RequestSpecifications {
    public final static String baseUrl = PropertyLoader.getPropertyValue(Constants.API_PROPERTIES, "url");
    public static RequestSpecification basicSpec =
            new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    .build();
}
