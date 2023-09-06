package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Hobby;
@Getter
@Setter
@ToString
public class HobbyAndInterest {
    private String hobby;
    private Long interest;

    public HobbyAndInterest(String hobby, Long interest) {
        this.hobby = hobby;
        this.interest = interest;
    }

}
