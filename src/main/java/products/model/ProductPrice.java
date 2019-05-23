package products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {

    private String now;
    private String was;
    private String then1;
    private String currency;

    public void setNow(Object now){
        this.now = now.toString();

    }
    public String getNow(){
        return now;
    }
    @JsonIgnore
    public boolean isDiscounted(){
        if(was!=null && !was.equals("") && now !=null && NumberUtils.isCreatable(now)){

                if (new BigDecimal(was).compareTo(new BigDecimal(now)) == 1) {
                    return true;
                }
        }

        return false;
    }

}