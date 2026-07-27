package com.example.motionwatch.firebase;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseCsvUploader {

    public static void uploadCsv(Uri csvUri, String sessionId) {
        Log.d("CSV_UPLOAD", "CALLED with: " + csvUri.getPath());

        StorageReference ref = FirebaseStorage.getInstance()
                .getReference("csv_sessions/" + sessionId + ".csv");

        ref.putFile(csvUri)
                .addOnSuccessListener(task ->
                        Log.d("CSV_UPLOAD", "UPLOAD SUCCESS"))
                .addOnFailureListener(e ->
                        Log.e("CSV_UPLOAD", "UPLOAD FAILED", e));
    }
}