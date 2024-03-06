package com.example.seleniumqatest.Factories;

import com.example.seleniumqatest.constants.Gender;
import com.example.seleniumqatest.constants.InputFormConstants;
import com.example.seleniumqatest.models.InputFormModel;

public abstract class InputFormModelFactory {
    public static InputFormModel allFieldValidModel() {
        return InputFormModel.builder()
                .name("Goodname")
                .email("good@mail.com")
                .gender(Gender.Female)
                .checkBoxValue1(InputFormConstants.CHECKBOX_FIRST)
                .radioValue(InputFormConstants.RADIO_FIRST)
                .build();
    }

    public static InputFormModel allFieldValidOneCharNameModel() {
        return InputFormModel.builder()
                .name("G")
                .email("good@yandex.com")
                .gender(Gender.Male)
                .checkBoxValue1(InputFormConstants.CHECKBOX_SECOND)
                .radioValue(InputFormConstants.RADIO_SECOND)
                .build();
    }

    public static InputFormModel allFieldValidLongNameModel() {
        return InputFormModel.builder()
                .name("VeryLongName123#@!#!$83123000000000000123)qwerrtyu")
                .email("anotherGood@google.com")
                .gender(Gender.Male)
                .checkBoxValue1(InputFormConstants.CHECKBOX_FIRST)
                .radioValue(InputFormConstants.RADIO_THIRD)
                .build();
    }

    public static InputFormModel allFieldValidBothCheckboxesModel() {
        return InputFormModel.builder()
                .name("SomeName")
                .email("anotherGood@google.com")
                .gender(Gender.Male)
                .checkBoxValue1(InputFormConstants.CHECKBOX_FIRST)
                .checkBoxValue2(InputFormConstants.CHECKBOX_SECOND)
                .radioValue(InputFormConstants.RADIO_THIRD)
                .build();
    }

    public static InputFormModel allFieldValidOnlyRequiredModel() {
        return InputFormModel.builder()
                .name("SomeName")
                .email("anotherGood@google.com")
                .gender(Gender.Male)
                .build();
    }

    public static InputFormModel invalidEmailData() {
        return InputFormModel.builder()
                .name("SomeName")
                .email("google.com")
                .gender(Gender.Male)
                .build();
    }

    public static InputFormModel invalidEmailSecondData() {
        return InputFormModel.builder()
                .name("SomeName")
                .email("test@@com")
                .gender(Gender.Female)
                .checkBoxValue1(InputFormConstants.CHECKBOX_FIRST)
                .radioValue(InputFormConstants.RADIO_THIRD)
                .build();
    }

    public static InputFormModel blankEmailData() {
        return InputFormModel.builder()
                .name("SomeName")
                .email("")
                .gender(Gender.Female)
                .build();
    }

    public static InputFormModel blankNameData() {
        return InputFormModel.builder()
                .name("")
                .email("testmail@mail.io")
                .gender(Gender.Male)
                .build();
    }

    public static InputFormModel blankRequiredFieldsData() {
        return InputFormModel.builder()
                .name("")
                .email("")
                .gender(Gender.Female)
                .build();
    }
}
