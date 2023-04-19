package models;
import java.util.*;

public class UserDto {
	public Long id;
	public String username;
    public String password;
    public String fullname;
    public String Address;
    public String Mobile_Number;
    public String Email;
    public List<Post> post;
    public UserDto() {}
}
