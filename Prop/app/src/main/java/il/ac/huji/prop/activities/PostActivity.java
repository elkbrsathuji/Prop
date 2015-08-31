package il.ac.huji.prop.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import il.ac.huji.prop.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import il.ac.huji.prop.models.History;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.ManagerModels.PropManager;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;
import il.ac.huji.prop.models.services.Facebook;
import il.ac.huji.prop.models.services.TwitterService;

public class PostActivity extends ActionBarActivity implements View.OnClickListener {
    private static final int PIC_CODE = 1;
    private EditText post;
    private ImageView bPhoto, bLoc, bVid, bFile, bChooseProp, bProp;
    private static final int REQUEST_IMAGE_CAPTURE = 2; //"pic" to hex to dec
    private static final int PICK_IMAGE = 100;
    private static final int PICK_VIDEO = 200;
    private static final int RAND = 100;

    private Bitmap bitmap;
    private ImageView imageView;
    private Random r;
    private Uri mCurrentPath;
    private Post mPost;
    private PropList mProp;
    private History gHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        post = (EditText) findViewById(R.id.post_text);
        bPhoto = (ImageView) findViewById(R.id.post_photo);
        bPhoto.setOnClickListener(this);
        bVid = (ImageView) findViewById(R.id.post_vid);
        bVid.setOnClickListener(this);
        bLoc = (ImageView) findViewById(R.id.post_location);
        bLoc.setOnClickListener(this);
        bFile = (ImageView) findViewById(R.id.post_file);
        bFile.setOnClickListener(this);
        bChooseProp = (ImageView) findViewById(R.id.post_choose_prop);
        bChooseProp.setOnClickListener(this);
        bProp = (ImageView) findViewById(R.id.post_prop);
        bProp.setOnClickListener(this);
        r = new Random();
        mPost = new Post();
        gHistory = History.getInstance();

        //to remove - JUST FOR DEBUG
mProp= chooseProp();
    }

    public void onClick(View v) {
        Location loc;
        switch (v.getId()) {

            case (R.id.post_photo): {
                showTakePicDialog();
                break;
            }

            case (R.id.post_vid): {
                selectVideoFromGallery();
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
                mPost.setTxt(post.getText().toString());
                PropManager.getInstance(this).propagate(mPost, mProp);
                gHistory.addPost(mPost);
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





    private Location uploadLoc() {
        return null;
    }

    private Uri uploadFile() {
        return null;
    }

    private PropList chooseProp() {
        ArrayList<SocialService> services = new ArrayList<SocialService>();
        services.add(new Facebook(this,true));
        services.add(new TwitterService(this,true));
       int[] in= new int[2];

       in[0]=0;
        in[1]=1;
        return new PropList(in,"testList");
    }

    private void postProp() {

    }




    private void showTakePicDialog(){
        final CharSequence[] items = { getString(R.string.lbl_take_photo), getString(R.string.lbl_choose_pic),
                getString(android.R.string.cancel) };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.lbl_take_photo))) {

                    dispatchTakePictureIntent();
                } else if (items[item].equals(getString(R.string.lbl_choose_pic))) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                } else if (items[item].equals(getString(android.R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

private void selectVideoFromGallery(){
    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
    photoPickerIntent.setType("video/*");
    startActivityForResult(photoPickerIntent, PICK_VIDEO);
}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE &&data != null && data.getData() != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            final Uri imageFileUri = data.getData();

            Picasso.with(getApplicationContext())
                    .load(imageFileUri)
                    .into(bPhoto);

            mPost.setPhotFile(imageFileUri);
        }else if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            final Uri imageFileUri = data.getData();
            mPost.setPhotFile(imageFileUri);
            Picasso.with(getApplicationContext())
                    .load(imageFileUri)
                    .into(bPhoto);
        }
        else if (requestCode == PICK_VIDEO && data != null && data.getData() != null) {
            final Uri imageFileUri = data.getData();
            mPost.setVidFile(imageFileUri);
            Picasso.with(getApplicationContext())
                    .load(imageFileUri)
                    .into(bVid);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
