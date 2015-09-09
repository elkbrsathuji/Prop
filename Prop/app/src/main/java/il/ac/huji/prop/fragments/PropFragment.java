package il.ac.huji.prop.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import il.ac.huji.prop.R;
import il.ac.huji.prop.adapters.AllServicesAdapter;
import il.ac.huji.prop.adapters.PropListAdapter;
import il.ac.huji.prop.models.ManagerModels.SharedPrefManager;
import il.ac.huji.prop.models.PropList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropFragment extends Fragment {

    ListView propsList, servicesList;
    LinearLayout propListContainer, allServicesContainer;
    boolean addMode;
    TextView allServicesTitle;
    EditText propName;
    //    ListView propService;
    AllServicesAdapter allServicesAdapter;
    PropListAdapter propsAdapter;
    FloatingActionButton fab;
    PropList currentProp;
    View view;


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

     * @return A new instance of fragment PropFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropFragment newInstance() {
        PropFragment fragment = new PropFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public PropFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view=inflater.inflate(R.layout.activity_prop, container, false);
        initListProps();
        initListServices();

        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addMode) {
                    currentProp=null;
                    moveSpecificPropMode();

                }else{
                    saveNewProp();

                    allServicesTitle.setVisibility(View.VISIBLE);
                    propName.setVisibility(View.GONE);
                    allServicesAdapter.setSpecificProp(null);
                    addMode=false;
                    fab.setImageResource(android.R.drawable.ic_input_add);
                }
            }
        });
        return view;
    }

    private void saveNewProp(){
        if (TextUtils.isEmpty(propName.getText().toString())){
            Toast.makeText(getActivity(), getString(R.string.msg_err_must_fill_name), Toast.LENGTH_SHORT);
        }else if (currentProp!=null) { // indicate we edit prop and not create a new one
            currentProp.setName(propName.getText().toString());
            currentProp.setServices(allServicesAdapter.getCheckedServices());
            SharedPrefManager.addProp(getActivity(), currentProp);
        }else{

            PropList prop = new PropList(allServicesAdapter.getCheckedServices(),propName.getText().toString() );
            SharedPrefManager.addProp(getActivity(),prop);
//        propsAdapter.notifyDataSetChanged();
        }

        propsAdapter.updateList();

    }
    private void moveSpecificPropMode( ){
        addMode=true;
        allServicesTitle.setVisibility(View.GONE);
        propName.setVisibility(View.VISIBLE);



        fab.setImageResource(R.drawable.vi);


        if (currentProp!=null){
            propName.setText(currentProp.getName());

            allServicesAdapter.setSpecificProp(currentProp);

        }else{
            propName.setText("");
            allServicesAdapter.setSpecificProp(new PropList());
        }
        servicesList.setAdapter(allServicesAdapter);
    }
    private void initListProps(){
        propsList=(ListView)view.findViewById(R.id.prop_list_of_props);
//        propListContainer=(LinearLayout)findViewById(R.id.prop_list_of_props_container);
//        propContainer=(LinearLayout)findViewById(R.id.prop_list_of_service_of_prop_container);

        propsAdapter= new PropListAdapter(getActivity(),SharedPrefManager.getListOfProps(getActivity()));
        propsList.setAdapter(propsAdapter);
        propsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentProp=propsAdapter.getItem(position);
                moveSpecificPropMode();

                return false;
            }
        });

    }

    private void initListServices(){

        allServicesContainer=(LinearLayout)view.findViewById(R.id.prop_all_services_list);
        allServicesTitle=(TextView)view.findViewById(R.id.prop_all_services_title);
        servicesList=(ListView)view.findViewById(R.id.prop_list_of_services);
        propName=(EditText)view.findViewById(R.id.prop_specific_prop_name);

        allServicesAdapter= new AllServicesAdapter(getActivity());
        servicesList.setAdapter(allServicesAdapter);
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
