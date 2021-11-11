package validators.apartmentValidator;

enum ValidationFail {
    ILLEGAL_NUMBER("Номер квартиры должен быть больше 0"),
    ILLEGAL_FLOOR("Номер этажа квартиры должен быть больше 0"),
    ILLEGAL_ROOMS("Кол-во комнат должно быть больше 0"),
    ILLEGAL_RESIDENTS("Кол-во жителей не может быть отрицательным"),
    ILLEGAL_SQUARE("Площадь не может быть меньше нуля");

    String message;

    ValidationFail(String message) {
        this.message = message;
    }
}
