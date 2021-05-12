package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void shouldTestCorrectForm() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Екатерина Петрова");
        $("[data-test-id='phone'] input").setValue("+79875336565");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена!"));
    }

    @Test
    void shouldTestNotCorrectName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Ekaterina Petrova");
        $("[data-test-id='phone'] input").setValue("+79873356565");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно: допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNotCorrectPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Екатерина Петрова");
        $("[data-test-id='phone'] input").setValue("2324435");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно, пример +79012345678."));
    }

    @Test
    void shouldTestEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id='phone'] input").setValue("+79872226535");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Игорь Попов");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestEmptyNameAndPhone() {
        open("http://localhost:9999/");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNotAgreement() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Екатерина Петрова");
        $("[data-test-id='phone'] input").setValue("+79872256565");
        $(".button__content").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
