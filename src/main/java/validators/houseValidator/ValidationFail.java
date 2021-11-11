package validators.houseValidator;

enum ValidationFail {
    ILLEGAL_NUMBER("Номер дома должен быть больше 0");

    String message;

    ValidationFail(String message) {
        this.message = message;
    }
}
