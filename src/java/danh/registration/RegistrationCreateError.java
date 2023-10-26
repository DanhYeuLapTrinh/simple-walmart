package danh.registration;

import java.io.Serializable;

public class RegistrationCreateError implements Serializable{
    private String usernameLength;
    private String passwordLength;
    private String confirmMatch;
    private String fullNameLength;
    private String existedUsername;

    public RegistrationCreateError() {
    }

    /**
     * @return the usernameLength
     */
    public String getUsernameLength() {
        return usernameLength;
    }

    /**
     * @param usernameLength the usernameLength to set
     */
    public void setUsernameLength(String usernameLength) {
        this.usernameLength = usernameLength;
    }

    /**
     * @return the passwordLength
     */
    public String getPasswordLength() {
        return passwordLength;
    }

    /**
     * @param passwordLength the passwordLength to set
     */
    public void setPasswordLength(String passwordLength) {
        this.passwordLength = passwordLength;
    }

    /**
     * @return the confirmMatch
     */
    public String getConfirmMatch() {
        return confirmMatch;
    }

    /**
     * @param confirmMatch the confirmMatch to set
     */
    public void setConfirmMatch(String confirmMatch) {
        this.confirmMatch = confirmMatch;
    }

    /**
     * @return the fullNameLength
     */
    public String getFullNameLength() {
        return fullNameLength;
    }

    /**
     * @param fullNameLength the fullNameLength to set
     */
    public void setFullNameLength(String fullNameLength) {
        this.fullNameLength = fullNameLength;
    }

    /**
     * @return the existedUsername
     */
    public String getExistedUsername() {
        return existedUsername;
    }

    /**
     * @param existedUsername the existedUsername to set
     */
    public void setExistedUsername(String existedUsername) {
        this.existedUsername = existedUsername;
    }
}