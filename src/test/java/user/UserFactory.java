package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("sauce.user"), PropertyReader.getProperty("sauce.password"));
    }

    public static User withLockedUserPermission() {
        return new User(PropertyReader.getProperty("sauce.locked"), PropertyReader.getProperty("sauce.password"));
    }

    public static User withEmptyUserPermission() {
        return new User(PropertyReader.getProperty("sauce.empty"), PropertyReader.getProperty("sauce.password"));
    }

    public static User withEmptyPasswordPermission() {
        return new User(PropertyReader.getProperty("sauce.user"), PropertyReader.getProperty("sauce.empty"));
    }

    public static User withInCorrectPasswordPermission() {
        return new User(PropertyReader.getProperty("sauce.user"), PropertyReader.getProperty("sauce.incorrect_data"));
    }

    public static User withInCorrectUserPermission() {
        return new User(PropertyReader.getProperty("sauce.incorrect_data"), PropertyReader.getProperty("sauce.password"));
    }
}