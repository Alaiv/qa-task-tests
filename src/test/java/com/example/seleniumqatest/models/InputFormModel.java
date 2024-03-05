package com.example.seleniumqatest.models;


import com.example.seleniumqatest.constants.Gender;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class InputFormModel {
    private final String email;
    private final String name;
    private final Gender gender;
    private String checkBoxValue1;
    private String checkBoxValue2;
    private String radioValue;
}
