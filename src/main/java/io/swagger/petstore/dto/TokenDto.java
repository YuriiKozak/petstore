
package io.swagger.petstore.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenDto {

    private Integer code;
    private String type;
    private String message;

}
