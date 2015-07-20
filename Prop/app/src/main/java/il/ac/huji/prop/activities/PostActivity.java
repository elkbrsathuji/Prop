package il.ac.huji.prop.activities;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
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
import il.ac.huji.prop.models.Utils;

public class PostActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int PIC_CODE = 1;
    private EditText post;
    private ImageButton bPhoto, bLoc, bVid, bFile, bChooseProp, bProp;
    private Uri outputFileUri;
    private Bitmap bitmap;
    private ImageView imageView;

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
    }

    public void onClick(View v) {
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
    private void uploadVid() {
    }

    private void uploadLoc() {
    }

    private void uploadFile() {
    }

    private void chooseProp() {
    }

    private void postProp() {

    }

    private void uploadPhoto() {

        // Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = Integer.toString(android.os.Process.myPid());
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

        startActivityForResult(chooserIntent, PIC_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PIC_CODE) {
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
                } else {
                    selectedImageUri = data == null ? null : data.getData();
                }
            }
        }
    }
}
