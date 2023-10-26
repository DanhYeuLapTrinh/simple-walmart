package danh.registration;

import java.io.Serializable;

public class RegistrationDTO implements Serializable{
    //các thành phần của DTO
    //1. các properties cần thiết của 1 object (1 dòng trong db) (private hoặc protected)
    //2. phải implement Serializable
    //3. tạo các getter setter như dưới
    //4. default constructor và constructor có tham số
    private String username;
    private String password;
    private String fullName;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String fullName, boolean role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
    
    // Ctrl + Alt + Shift + E -> select all -> refractor
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }
    
    
}
