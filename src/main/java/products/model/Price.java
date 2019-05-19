package products.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String nowPrice;

    private String currency;

    public void setNow(Object now){
        nowPrice = now.toString();

    }
    public String getNow(){
        return nowPrice;
    }
}