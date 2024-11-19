package com.example.library.model.dto.request;

import com.example.library.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoRequest {
    String login;
    String password;
    UserType type;
    public void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }
}
