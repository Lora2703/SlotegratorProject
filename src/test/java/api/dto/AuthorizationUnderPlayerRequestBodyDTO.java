package api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorizationUnderPlayerRequestBodyDTO {

    @JsonProperty("grant_type")
    private String grantType;
    private String username;
    private String password;

}
