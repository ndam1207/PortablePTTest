package com.example.hihihahahehe.portablept.events;

import com.example.hihihahahehe.portablept.models.FaceBookModel;

/**
 * Created by hihihahahehe on 8/12/17.
 */

public class OnLoginEvent {
    private FaceBookModel faceBookModel;

    public OnLoginEvent(FaceBookModel faceBookModel) {
        this.faceBookModel = faceBookModel;
    }

    public FaceBookModel getFaceBookModel() {
        return faceBookModel;
    }

    public void setFaceBookModel(FaceBookModel faceBookModel) {
        this.faceBookModel = faceBookModel;
    }
}
