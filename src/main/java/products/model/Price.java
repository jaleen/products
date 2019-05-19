package products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String attr1;

    private String currency;

    public void setNow(Object now){
        attr1 = now.toString();

    }
    public String getNow(){
        return attr1;
    }
}