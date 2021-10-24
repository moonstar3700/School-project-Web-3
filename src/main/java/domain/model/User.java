package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Group group;
    private Role role;

    public User(String email, String password, String firstName, String lastName, Group group) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGroup(group);
        setRole(Role.TRAINER);
    }

    public User(int userid, String email, String password, String firstName, String lastName, Group group) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this(email, password, firstName, lastName, group);
        this.setUserid(userid);
    }

    public User(String email, String password, String firstName, String lastName, String group, String role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGroup(group);
        setRole(role);
    }

    public User(int id, String email, String password, String firstName, String lastName, String group, String role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        setUserid(id);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGroup(group);
        setRole(role);
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = password;
    }

    private static String sha512(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();

        // encrypts
        crypt.update(password.getBytes("UTF-8"));

        //16 hexadecimal system the sixteen digits are "0–9" followed by "A–F".
        String hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
        System.out.println(hashedPassword.length());
        return hashedPassword;
    }

  /*  public void setPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = sha512(password);
    }

    public boolean isCorrectPassword(String p) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (p.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        String hashedPassword = sha512(p);
        return this.password.equals(hashedPassword);
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("No first name given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("No group given");
        }
        this.group = group;
    }

    public void setGroup(String group) {
        if (group == null) {
            throw new IllegalArgumentException("No group given");
        }
        try {
            this.group = Group.valueOf(group.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DomainException("There is no group with value " + group);
        }
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(String role) {
        if (role == null) {
            throw new IllegalArgumentException("No role given");
        }
        try {
            this.role = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DomainException("There is no role with value " + role);
        }
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail() + ", " + getRole() + ", " + getGroup();
    }
}
