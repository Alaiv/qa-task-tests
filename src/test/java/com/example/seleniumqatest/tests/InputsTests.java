package com.example.seleniumqatest.tests;

import com.example.seleniumqatest.Factories.InputFormModelFactory;
import com.example.seleniumqatest.models.InputFormModel;
import com.example.seleniumqatest.pages.InputsPage;
import com.example.seleniumqatest.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InputsTests extends BaseTest {

    private InputsPage inputsPage;

    @BeforeEach
    public void init() {
        inputsPage = new LoginPage(driver)
                .login(settingsConfig.email(), settingsConfig.password());
    }

    @Test
    public void modalOpenCloseTest() {
        InputFormModel model = InputFormModelFactory.allFieldValidModel();

        inputsPage
                .sendEmail(model.getEmail())
                .sendName(model.getName())
                .selectGender(model.getGender().toString())
                .selectCheckbox(model.getCheckBoxValue1())
                .selectRadio(model.getRadioValue())
                .submitForm();

        inputsPage.assertModalIsVisible();
        inputsPage.assertModalHaveText("Данные добавлены.");

        inputsPage.closeModal();
        inputsPage.assertModalNotVisible();
    }


    @ParameterizedTest
    @MethodSource("validInputDataWithAllFields")
    public void addItemWithAllFilledValidInputs(InputFormModel model) {
        inputsPage
                .sendEmail(model.getEmail())
                .sendName(model.getName())
                .selectGender(model.getGender().toString())
                .selectCheckbox(model.getCheckBoxValue1())
                .selectRadio(model.getRadioValue())
                .submitForm()
                .closeModal()
                .assertThatItemAdded()
                .assertItemHaveGivenData(model);
    }

    private Stream<InputFormModel> validInputDataWithAllFields() {
        return Stream.of(
                InputFormModelFactory.allFieldValidModel(),
                InputFormModelFactory.allFieldValidOneCharNameModel(),
                InputFormModelFactory.allFieldValidLongNameModel()
        );
    }
}
