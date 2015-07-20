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
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import il.ac.huji.prop.R;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.Prop;
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
import il.ac.huji.prop.models.Utils;

public class PostActivity extends ActionBarActivity implements View.OnClickListener {

<<<<<<< HEAD
    private static final int PIC_CODE = 1;
    private EditText post;
    private ImageButton bPhoto, bLoc, bVid, bFile, bChooseProp, bProp;
    private Uri outputFileUri;
    private Bitmap bitmap;
    private ImageView imageView;
=======
    private static final int PICTURE_REQUEST_CODE = 7367011; //"pic" to hex to dec
    private static final int RAND = 100;
    private EditText txt;
    private Button bPhoto;
    private Button bLoc;
    private Button bVid;
    private Button bFile;
    private Button bChooseProp;
    private Button bProp;
    private Bitmap bitmap;
    private ImageView imageView;
    private Random r;
    private String mCurrentPath;
    private Post post;
    private Prop mProp;
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab

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
        post = new Post();

    }

    public void onClick(View v) {
<<<<<<< HEAD
        switch (v.getId()) {

            case (R.id.post_photo): {
                uploadPhoto();
                break;
            }
            case (R.id.post_vid): {
                uploadVid();
                break;
            }
            case (R.id.post_location): {
                uploadLoc();
                break;

            }
            case (R.id.post_file): {
                uploadFile();
                break;
            }
            case (R.id.post_choose_prop): {
                chooseProp();
                break;
            }
            case (R.id.post_prop): {
                postProp();
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


=======
        Uri path = null;
        Location loc = null;
        switch (v.getId()) {

            case (R.id.post_photo): {
                path = uploadPhoto();
                post.setPhoto(path);
                break;
            }

            case (R.id.post_vid): {
                path = uploadVid();
                post.setVid();
                break;
            }

            case (R.id.post_location): {
                loc = uploadLoc();
                post.setLoc(path);
                break;
            }

            case (R.id.post_file): {
                path = uploadFile();
                post.setUplFile(path);
                break;
            }

            case (R.id.post_choose_prop): {
                mProp = chooseProp();
                break;
            }

            case (R.id.post_prop): {
                mProp.propagate(post);
                break;
            }

            default: {
                break;
            }

        }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_post, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
    }


>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
    //    private void uploadPhoto() {
//        Intent pickIntent = new Intent();
//        pickIntent.setType("image/*");
//        pickIntent.setAction(Intent.ACTION_GET_CONTENT);
//        pickIntent.addCategory(Intent.CATEGORY_OPENABLE);
//
//        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        String pickTitle = Resources.getSystem().getString(R.string.pick_pic);
//        Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
//        chooserIntent.putExtra
//                (
//                        Intent.EXTRA_INITIAL_INTENTS,
//                        new Intent[] { takePhotoIntent }
//                );
//
//        startActivityForResult(chooserIntent, SELECT_PICTURE);
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
//            try {
//                // We need to recycle unused bitmaps
//                if (bitmap != null) {
//                    bitmap.recycle();
//                }
//                InputStream stream = getContentResolver().openInputStream(
//                        data.getData());
//                bitmap = BitmapFactory.decodeStream(stream);
//                stream.close();
//                imageView.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
<<<<<<< HEAD
    private void uploadVid() {
    }

    private void uploadLoc() {
    }

    private void uploadFile() {
    }

    private void chooseProp() {
=======
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
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
    }

    private void postProp() {

    }
<<<<<<< HEAD

    private void uploadPhoto() {

        // Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = Integer.toString(android.os.Process.myPid());
=======

    private Uri uploadPhoto() {
        Uri outputFileUri;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
// Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "PropDir" + File.separator);
        root.mkdirs();
        final String fname = "pic" + Integer.toString(android.os.Process.myTid()) + Integer.toString(r.nextInt(RAND));
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

<<<<<<< HEAD
        startActivityForResult(chooserIntent, PIC_CODE);
=======
        startActivityForResult(chooserIntent, PICTURE_REQUEST_CODE);
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
<<<<<<< HEAD
            if (requestCode == PIC_CODE) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
=======
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
                        selectedImageUri = outputFileUri;
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
                    } else {
                        selectedImageUri = data == null ? null : data.getData();
                    }
                default:
                    return;
            }
        }
    }
<<<<<<< HEAD
=======
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
        mCurrentPath = "file:" + f.getAbsolutePath();
        return f;
    }
>>>>>>> 76d20d362bbc26d343ef4d131674568d311698ab
}
