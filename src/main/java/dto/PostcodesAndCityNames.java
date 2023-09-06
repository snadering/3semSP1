package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostcodesAndCityNames {

    private int postcode;
    private String cityName;

    public PostcodesAndCityNames(int postcode, String cityName) {
        this.postcode = postcode;
        this.cityName = cityName;
    }
}
