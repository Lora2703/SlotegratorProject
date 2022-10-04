package api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterNewPlayerResponseBodyDTO {

    private Integer id;
    @JsonProperty("country_id")
    private String countryId;
    @JsonProperty("timezone_id")
    private String timeZoneId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String birthdate;
    @JsonProperty("bonuses_allowed")
    private String bonusesAllowed;
    @JsonProperty("is_verified")
    private String isVerified;
}

