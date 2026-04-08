
package io.swagger.petstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class OrderDto {

    private Long id;
    private Long petId;
    private Integer quantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime shipDate;

    private Status status;
    private Boolean complete;

    public enum Status {
        placed, approved, delivered
    }

}
