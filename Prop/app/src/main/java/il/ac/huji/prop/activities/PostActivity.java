package il.ac.huji.prop.activities;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import il.ac.huji.prop.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.Prop;
import il.ac.huji.prop.models.Utils;

public class PostActivity extends ActionBarActivity implements View.OnClickListener {
    private static final int PIC_CODE = 1;
    private EditText post;
    private ImageButton bPhoto, bLoc, bVid, bFile, bChooseProp, bProp;
    private static final int PICTURE_REQUEST_CODE = 7367011; //"pic" to hex to dec
    private static final int RAND = 100;
    //    private EditText txt;
//    private Button bPhoto;
//    private Button bLoc;
//    private Button bVid;
//    private Button bFile;
//    private Button bChooseProp;
//    private Button bProp;
    private Bitmap bitmap;
    private ImageView imageView;
    private Random r;
    private Uri mCurrentPath;
    private Post mPost;
    private Prop mProp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        post = (EditText) findViewById(R.id.post_text);
        bPhoto = (ImageButton) findViewById(R.id.post_photo);
        bPhoto.setOnClickListener(this);
        bVid = (ImageButton) findViewById(R.id.post_vid);
        bVid.setOnClickListener(this);
        bLoc = (ImageButton) findViewById(R.id.post_location);
        bLoc.setOnClickListener(this);
        bFile = (ImageButton) findViewById(R.id.post_file);
        bFile.setOnClickListener(this);
        bChooseProp = (ImageButton) findViewById(R.id.post_choose_prop);
        bChooseProp.setOnClickListener(this);
        bProp = (ImageButton) findViewById(R.id.post_prop);
        bProp.setOnClickListener(this);
        r = new Random();
        mPost = new Post();

    }

    public void onClick(View v) {
        Location loc;
        switch (v.getId()) {

            case (R.id.post_photo): {
                uploadPhoto();
                mPost.setPhotFile(mCurrentPath);
                break;
            }

            case (R.id.post_vid): {
                uploadVid();
                mPost.setVidFile(mCurrentPath);
                break;
            }

            case (R.id.post_location): {
                loc = uploadLoc();
                mPost.setLoc(loc);
                break;
            }

            case (R.id.post_file): {
                uploadFile();
                mPost.setUplFile(mCurrentPath);
                break;
            }

            case (R.id.post_choose_prop): {
                mProp = chooseProp();
                break;
            }

            case (R.id.post_prop): {
                mProp.propagate(mPost);
                break;
            }

            default: {
                break;
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private Uri uploadVid() {
        return null;
    }

    private Location uploadLoc() {
        return null;
    }

    private Uri uploadFile() {
        return null;
    }

    private Prop chooseProp() {
        return null;
    }

    private void postProp() {

    }

    private Uri uploadPhoto() {
        Uri outputFileUri;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createFile("JPEG");
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, PICTURE_REQUEST_CODE);
            }
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case (PICTURE_REQUEST_CODE):
                    final boolean isCamera;
                    if (data == null) {
                        isCamera = true;
                    } else {
                        final String action = data.getAction();
                        if (action == null) {
                            isCamera = false;
                        } else {
                            isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        }
                    }

                    Uri selectedImageUri;
                    if (isCamera) {
                        selectedImageUri = mCurrentPath;
                    } else {
                        selectedImageUri = data == null ? null : data.getData();
                    }
                default:
                    return;
            }
        }
    }

    private File createFile(String type) throws IOException{
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = type.toUpperCase() + timeStamp + "_" + Integer.toString(android.os.Process.myTid());
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(
                fileName,  /* prefix */
                "."+type,         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPath = Uri.parse("file:" + f.getAbsolutePath());
        return f;
    }
}
