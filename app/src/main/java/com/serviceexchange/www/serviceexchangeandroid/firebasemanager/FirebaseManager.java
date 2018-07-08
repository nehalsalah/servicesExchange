package com.serviceexchange.www.serviceexchangeandroid.firebasemanager;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

public class FirebaseManager implements FirebaseInt {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference subRef;

    @Override
    public void uploadFile(Uri uri) {
        subRef = storageRef.child("files/"+uri.getLastPathSegment());
        UploadTask uploadTask = subRef.putFile(uri);
        uploadTask
                .addOnSuccessListener(new SuccessListener())
                .addOnFailureListener(new FailureListener());
        Task<Uri> task = uploadTask
                .continueWithTask(new Continuation())
                .addOnCompleteListener(new CompleteListener());
    }

    class SuccessListener implements OnSuccessListener<UploadTask.TaskSnapshot> {

        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            Log.i("file", "uploaded");
        }
    }

    class FailureListener implements OnFailureListener {

        @Override
        public void onFailure(@NonNull Exception e) {
            Log.i("file", e.getMessage());
        }
    }

    class Continuation implements com.google.android.gms.tasks.Continuation<UploadTask.TaskSnapshot, Task<Uri>> {

        @Override
        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return subRef.getDownloadUrl();
        }
    }

    class CompleteListener implements OnCompleteListener<Uri> {

        @Override
        public void onComplete(@NonNull Task<Uri> task) {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                EventBus.getDefault().post(new CustomEvent(CustomEventType.FILE_URL, downloadUri.toString()));
            } else {
                Log.i("file", "fail url");
            }
        }
    }
}
