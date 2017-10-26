package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    String id;
    String fullName;
    String greeting;
}
