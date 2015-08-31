package il.ac.huji.prop.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.History;
import il.ac.huji.prop.models.ManagerModels.PropManager;
import il.ac.huji.prop.models.ManagerModels.SharedPrefManager;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;
import il.ac.huji.prop.models.services.Facebook;
import il.ac.huji.prop.models.services.TwitterService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment implements View.OnClickListener,SocialService.onFinishUploadListener {

    private static final int PIC_CODE = 1;
    private EditText post;
    private ImageView bPhoto, bLoc, bVid, bFile;
    private Button bProp,bChooseProp;
    private static final int REQUEST_IMAGE_CAPTURE = 2; //"pic" to hex to dec
    private static final int PICK_IMAGE = 100;
    private static final int PICK_VIDEO = 200;
    private static final int RAND = 100;

    private ProgressDialog progressDialog;

    private Uri mCurrentPath;
    private Post mPost;
    private PropList mProp;
    private History gHistory;

private int uploadedCounter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_post, container, false);
        post = (EditText) view.findViewById(R.id.post_text);
        bPhoto = (ImageView) view.findViewById(R.id.post_photo);
        bPhoto.setOnClickListener(this);
        bVid = (ImageView) view.findViewById(R.id.post_vid);
        bVid.setOnClickListener(this);
        bLoc = (ImageView) view.findViewById(R.id.post_location);
        bLoc.setOnClickListener(this);
        bFile = (ImageView) view.findViewById(R.id.post_file);
        bFile.setOnClickListener(this);
        bChooseProp = (Button) view.findViewById(R.id.post_choose_prop);
        bChooseProp.setOnClickListener(this);
        bProp = (Button) view.findViewById(R.id.post_prop);
        bProp.setOnClickListener(this);
        r = new Random();
        mPost = new Post();
        gHistory = History.getInstance();

        //to remove - JUST FOR DEBUG
//        mProp= chooseProp();



        return view;
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
                 chooseProp();
                break;
            }

            case (R.id.post_prop): {
                if (validate()) {
                    showLoader();
                    mPost.setTxt(post.getText().toString());
                    uploadedCounter=mProp.getServices().length;
                    PropManager.getInstance(getActivity()).propagate(mPost, mProp,this);
                    gHistory.addPost(mPost);
                }
                break;
            }

            default: {
                break;
            }

        }
    }

    private boolean validate(){
        if (mProp==null){
            Toast.makeText(getActivity(),getString(R.string.msg_must_choose_prop),Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }
    private Location uploadLoc() {
        return null;
    }

    private Uri uploadFile() {
        return null;
    }

    private void chooseProp() {
        final ArrayList<PropList> props=SharedPrefManager.getListOfProps(getActivity());
        String propNames[]= new String[props.size()];
        for(int i=0;i<props.size();i++) {
            propNames[i] = props.get(i).getName();
        }
        AlertDialog.Builder builder3=new AlertDialog.Builder(getActivity());
        builder3.setTitle("Pick your choice").setItems(propNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mProp=props.get(which);
            }
        });
        builder3.show();

    }

    private void postProp() {

    }

    private void showTakePicDialog(){
        final CharSequence[] items = { getString(R.string.lbl_take_photo), getString(R.string.lbl_choose_pic),
                getString(android.R.string.cancel) };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE &&data != null && data.getData() != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            final Uri imageFileUri = data.getData();

            Picasso.with(getActivity().getApplicationContext())
                    .load(imageFileUri)
                    .into(bPhoto);

            mPost.setPhotFile(imageFileUri);
        }else if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            final Uri imageFileUri = data.getData();
            mPost.setPhotFile(imageFileUri);
            Picasso.with(getActivity().getApplicationContext())
                    .load(imageFileUri)
                    .into(bPhoto);
        }
        else if (requestCode == PICK_VIDEO && data != null && data.getData() != null) {
            final Uri imageFileUri = data.getData();
            mPost.setVidFile(imageFileUri);
            Picasso.with(getActivity().getApplicationContext())
                    .load(imageFileUri)
                    .into(bVid);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void selectVideoFromGallery(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("video/*");
        startActivityForResult(photoPickerIntent, PICK_VIDEO);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFinishUpload() {
       uploadedCounter--;
        if (uploadedCounter==0){
removeLoader();
        }
    }

    public void showLoader() {
        try{
            if (progressDialog == null ) {
                progressDialog = new ProgressDialog(getActivity());


                progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.msg_upload_post));
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
            }
        }catch(Exception e){
            //TODO
        }
    }

    public void removeLoader() {
        if (progressDialog!=null && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog=null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
