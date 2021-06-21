package com.example.chatterbox.objects;

public class ProfileDataModel {

    private String Bio;
    private String DOB;
    private String Email;
    private String Gender;
    private String Name;
    private String OnlineStatus;
    private String ProfilePic;
    private String UID;

    public ProfileDataModel(String bio, String DOB, String email, String gender, String name, String onlineStatus, String profilePic, String UID) {
        Bio = bio;
        this.DOB = DOB;
        Email = email;
        Gender = gender;
        Name = name;
        OnlineStatus = onlineStatus;
        ProfilePic = profilePic;
        this.UID = UID;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOnlineStatus() {
        return OnlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        OnlineStatus = onlineStatus;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
