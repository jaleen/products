package products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private String now;
    private String was;
    private String then;
    private String currency;

    @JsonIgnore
    public boolean isDiscounted(){
        if(was!=null && now !=null){

            if(new BigDecimal(was).compareTo(new BigDecimal(now))==1){
                return true;
            }
        }

        return false;
    }

}