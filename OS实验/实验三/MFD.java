import java.io.*;

public class MFD {
    private String username;
    private String address;
    private String password;

    public MFD(String username, String address, String password) {
        this.username = username;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MFD{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
