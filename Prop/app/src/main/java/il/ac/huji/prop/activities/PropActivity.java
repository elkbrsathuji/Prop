//package il.ac.huji.prop.activities;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import il.ac.huji.prop.R;
//import il.ac.huji.prop.adapters.AllServicesAdapter;
//import il.ac.huji.prop.adapters.PropListAdapter;
//import il.ac.huji.prop.models.ManagerModels.SharedPrefManager;
//import il.ac.huji.prop.models.PropList;
//import il.ac.huji.prop.models.SocialService;
//
///**
// * Created by Android- on 7/20/2015.
// */
//public class PropActivity extends Activity {
//
//    ListView propsList, servicesList;
//    LinearLayout propListContainer, allServicesContainer;
//    boolean addMode;
//    TextView allServicesTitle;
//    EditText propName;
////    ListView propService;
//    AllServicesAdapter allServicesAdapter;
//    PropListAdapter propsAdapter;
//    FloatingActionButton fab;
//    PropList currentProp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_prop);
//        initListProps();
//        initListServices();
//
//       fab = (FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               if (!addMode) {
//                   currentProp=null;
//                   moveSpecificPropMode();
//
//               }else{
//                   saveNewProp();
//
//                   allServicesTitle.setVisibility(View.VISIBLE);
//                   propName.setVisibility(View.GONE);
//                   allServicesAdapter.setSpecificProp(null);
//                   addMode=false;
//                   fab.setImageResource(android.R.drawable.ic_input_add);
//               }
//            }
//        });
//    }
//
//
////private void saveNewProp(){
////    if (TextUtils.isEmpty(propName.getText().toString())){
////        Toast.makeText(this,getString(R.string.msg_err_must_fill_name),Toast.LENGTH_SHORT);
////    }else if (currentProp!=null) { // indicate we edit prop and not create a new one
////        currentProp.setName(propName.getText().toString());
////        currentProp.setServices(allServicesAdapter.getCheckedServices());
////        SharedPrefManager.addProp(this,currentProp);
////    }else{
////
////            PropList prop = new PropList(allServicesAdapter.getCheckedServices(),propName.getText().toString() );
////        SharedPrefManager.addProp(this,prop);
//////        propsAdapter.notifyDataSetChanged();
////        }
////
////    propsAdapter.updateList();
////
////}
////    private void moveSpecificPropMode( ){
////        addMode=true;
////        allServicesTitle.setVisibility(View.GONE);
////        propName.setVisibility(View.VISIBLE);
////
////
////
////        fab.setImageResource(R.drawable.vi);
////
////
////        if (currentProp!=null){
////           propName.setText(currentProp.getName());
////
////            allServicesAdapter.setSpecificProp(currentProp);
////
////        }else{
////            propName.setText("");
////            allServicesAdapter.setSpecificProp(new PropList());
////        }
////        servicesList.setAdapter(allServicesAdapter);
////    }
////    private void initListProps(){
////        propsList=(ListView)findViewById(R.id.prop_list_of_props);
//////        propListContainer=(LinearLayout)findViewById(R.id.prop_list_of_props_container);
//////        propContainer=(LinearLayout)findViewById(R.id.prop_list_of_service_of_prop_container);
////
////        propsAdapter= new PropListAdapter(this,SharedPrefManager.getListOfProps(this));
////        propsList.setAdapter(propsAdapter);
////        propsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
////                currentProp=propsAdapter.getItem(position);
////                moveSpecificPropMode();
////
////                return false;
////            }
////        });
////
////    }
////
////    private void initListServices(){
////
////        allServicesContainer=(LinearLayout)findViewById(R.id.prop_all_services_list);
////        allServicesTitle=(TextView)findViewById(R.id.prop_all_services_title);
////        servicesList=(ListView)findViewById(R.id.prop_list_of_services);
////        propName=(EditText)findViewById(R.id.prop_specific_prop_name);
////
////        allServicesAdapter= new AllServicesAdapter(this);
////        servicesList.setAdapter(allServicesAdapter);
////    }
//
//
//
//}
