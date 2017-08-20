package com.example.hihihahahehe.portablept.utils;

import com.example.hihihahahehe.portablept.models.FaceBookModel;

import io.realm.Realm;

/**
 * Created by hihihahahehe on 8/14/17.
 */

public class RealmHandle {
    public static  Realm realm = Realm.getDefaultInstance();

    public static void addData(FaceBookModel faceBookModel){
        realm.beginTransaction();
        realm.copyToRealm(faceBookModel);
        realm.commitTransaction();
    }

    public static FaceBookModel getData(){
        return realm.where(FaceBookModel.class).findFirst();
    }
}
