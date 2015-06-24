package il.ac.huji.prop.activities;

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
import android.widget.ImageView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import il.ac.huji.prop.R;

public class PostActivity extends ActionBarActivity implements OnClickListener    {

    private static final int SELECT_PICTURE = 1;
    private EditText txt;
    private Button bPhoto;
    private Button bLoc;
    private Button bVid;
    private Button bFile;
    private Button bChooseProp;
    private Button bProp;
    private Bitmap bitmap;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        post = null;
        txt = (EditText) findViewById(R.id.post_text);

        bPhoto = (Button) findViewById(R.id.post_photo);
        bPhoto.setOnClickListener(this);
        bVid = (Button) findViewById(R.id.post_vid);
        bVid.setOnClickListener(this);
        bLoc = (Button) findViewById(R.id.post_location);
        bLoc.setOnClickListener(this);
        bFile = (Button) findViewById(R.id.post_file);
        bFile.setOnClickListener(this);
        bChooseProp = (Button) findViewById(R.id.post_choose_prop);
        bChooseProp.setOnClickListener(this);
        bProp = (Button) findViewById(R.id.post_prop);
        bProp.setOnClickListener(this);4
    }

    public void onClick(View v){
        switch(v.getId()){

            case (R.id.post_photo):{
                uploadPhoto();
                break;
            }
            case (R.id.post_vid):{
                uploaVid();
                break;
            }
            case (R.id.post_location):{
                uploadLoc();
                break;
                
            }
            case (R.id.post_file):{
                uploadFile();
                break;
            }
            case (R.id.post_choose_prop):{
                chooseProp();
                break;
            }
            case (R.id.post_prop):{
                postProp();
                break;
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
}

    private void postPhoto() {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
        Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
        chooserIntent.putExtra
                (
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[] { takePhotoIntent }
                );

        startActivityForResult(chooserIntent, SELECT_PICTURE);
    }
    private void postVid() {
    }
    private void postLocation() {
    }
    private void postfile() {
    }
    private void ChooseProp() {
    }
    private void postProp() {

    }
