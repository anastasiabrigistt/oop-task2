import javax.swing.*;

public class Person {
    private String name;
    private int age;
    private String gender;
    private String interest;
    private String shortInfo;
    private ImageIcon avatar;

    public Person(String name, int age, String gender, String interest, String shortInfo, ImageIcon avatar) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.interest = interest;
        this.shortInfo = shortInfo;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
}