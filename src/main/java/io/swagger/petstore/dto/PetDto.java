package io.swagger.petstore.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PetDto {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private Status status;

    @Data
    @Accessors(chain = true)
    public static class Category {
        private Long id;
        private String name;
    }

    @Data
    @Accessors(chain = true)
    public static class Tag {
        private Long id;
        private String name;
    }

    public enum Status {
        available, pending, sold
    }

}
