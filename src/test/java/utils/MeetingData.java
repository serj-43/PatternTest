package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingData{
    private final String name;
    private final String phone;
    private final String city;
}
