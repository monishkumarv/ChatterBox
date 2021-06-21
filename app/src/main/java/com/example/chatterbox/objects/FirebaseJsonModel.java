package com.example.chatterbox.objects;

import java.util.List;

public class FirebaseJsonModel {

    private ProfileDataModel mProfileDataModel;
    private List<FriendModel> mfriendModel;

    public FirebaseJsonModel(ProfileDataModel mProfileDataModel, List<FriendModel> mfriendModel) {
        this.mProfileDataModel = mProfileDataModel;
        this.mfriendModel = mfriendModel;
    }
}
