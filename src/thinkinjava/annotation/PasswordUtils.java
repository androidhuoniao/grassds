package thinkinjava.annotation;

/**
 * Created by grass on 8/11/15.
 */
public class PasswordUtils {

    @UseCase(id = 12, description = "passwords must be contain one number")
    public boolean validPasswords(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 14)
    public String encryptPassword(String password) {
        return new StringBuffer(password).reverse().toString();
    }
}
