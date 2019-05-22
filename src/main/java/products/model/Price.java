package products.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private String now;
    private String was;
    private String currency;
}