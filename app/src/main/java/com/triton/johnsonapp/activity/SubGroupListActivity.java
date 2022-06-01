package com.triton.johnsonapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnsonapp.Forms.ImageBasedInputFormActivity;
import com.triton.johnsonapp.Forms.InputValueFormListActivity;
import com.triton.johnsonapp.R;
import com.triton.johnsonapp.activitybased.ActivityJobListActivity;
import com.triton.johnsonapp.adapter.FieldListAdapter;
import com.triton.johnsonapp.adapter.SelectEngineerAdapter;
import com.triton.johnsonapp.adapter.ServiceListAdapter;
import com.triton.johnsonapp.api.APIInterface;
import com.triton.johnsonapp.api.RetrofitClient;
import com.triton.johnsonapp.requestpojo.SelectEngineerRequest;
import com.triton.johnsonapp.requestpojo.SubGroupDetailManagementRequest;
import com.triton.johnsonapp.responsepojo.FormDataStoreResponse;
import com.triton.johnsonapp.responsepojo.GetFieldListResponse;
import com.triton.johnsonapp.responsepojo.SelectEnginnerResponse;
import com.triton.johnsonapp.responsepojo.SubGroupDetailManagementResponse;
import com.triton.johnsonapp.session.SessionManager;
import com.triton.johnsonapp.utils.ConnectionDetector;
import com.triton.johnsonapp.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubGroupListActivity extends AppCompatActivity {


    private final int jsoncode = 1;
    private static ProgressDialog mProgressDialog;
     private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner;
SelectEngineerAdapter selectEngineerAdapter;

    private String TAG ="SubGroupListActivity";

    String userid,username;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_logout)
    TextView txt_logout;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_subgrouplist)
    RecyclerView rv_subgrouplist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_submit)
    Button btn_submit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    Dialog  dialog;
    Dialog alertdialog;
    String networkStatus = "",message,activity_id,job_id,group_id,EmpNo;
    String status;

    int number=0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    public static String responsemessage;


    SelectEnginnerResponse selectenginnerresponse = new SelectEnginnerResponse();
     private AlertDialog.Builder alertDialogBuilder;
    private String search_string ="";
    private String fromactivity ;
    private String group_detail_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_group_list);
        ButterKnife.bind(this);
        Log.w(TAG,"Oncreate -->");
        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        userid = user.get(SessionManager.KEY_ID);

        username = user.get(SessionManager.KEY_USERNAME);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getString("status");
            group_id = extras.getString("group_id");
            activity_id = extras.getString("activity_id");
            job_id = extras.getString("job_id");
            fromactivity = extras.getString("fromactivity");
            group_detail_name = extras.getString("group_detail_name");
            Log.w(TAG,"activity_id -->"+activity_id);
            Log.w(TAG,"group_id -->"+group_id);
            Log.w(TAG,"job_id -->"+job_id);
            Log.w(TAG,"fromactivity -->"+fromactivity);
            Log.w(TAG,"group_detail_name -->"+group_detail_name);
            Log.w(TAG,"status -->"+status);

            if(group_detail_name != null){
                txt_toolbar_title.setText(""+group_detail_name);
            }

        }


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            Toasty.warning(getApplicationContext(),"No Internet",Toasty.LENGTH_LONG).show();

        }
        else {

            subgroupdetailmanagmentResponseCall();

           /* List<GetServiceListResponse.DataBean> dataBeanList = new ArrayList<>();


            for(int i=0;i<=3;i++){

                number++;
                GetServiceListResponse.DataBean dataBean = new  GetServiceListResponse.DataBean();

                Log.w(TAG,"number "+ number);

                dataBean.setService_name("Sub Group "+number);

                dataBeanList.add(dataBean);
            }


            if(dataBeanList != null && dataBeanList.size()>0){
                setView(dataBeanList);
            }*/
        }

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search_string = textView.getText().toString();
                    subgroupdetailmanagmentResponseCall();
                    return true;
                }
                return false;
            }
        });
btn_submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showPopupSelectEngineer();
    }
});
        //SelectEngineerResponseCall();

        }
    private void showPopupSelectEngineer() {

        try {
            alertdialog = new Dialog(SubGroupListActivity.this);
            alertdialog.setContentView(R.layout.select_engineer_popup);

            alertdialog.setCancelable(false);

            ImageView img_close = alertdialog.findViewById(R.id.img_close);




            img_close.setOnClickListener(v -> alertdialog.dismiss());
            Objects.requireNonNull(alertdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertdialog.show();


          /*  img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertdialog.dismiss();
                    Intent intent = new Intent(SubGroupListActivity.this, AllJobListActivity.class);
                    intent.putExtra("activity_id",activity_id);
                    intent.putExtra("status",status);
                    startActivity(intent);
                    overridePendingTransition(R.anim.new_right, R.anim.new_left);
                }
            });*/
            Objects.requireNonNull(alertdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertdialog.show();


        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }








    // default back button action
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SubGroupListActivity.this, GroupListActivity.class);
        intent.putExtra("activity_id",activity_id);
        intent.putExtra("job_id",job_id);
        intent.putExtra("status",status);
        startActivity(intent);
        overridePendingTransition(R.anim.new_right, R.anim.new_left);


    }
   /* private void SelectEngineerResponseCall() {
        dialog = new Dialog(SubGroupListActivity.
                this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        //Call<SelectEnginnerResponse> call = apiInterface.selectengineerResponseCall(RestUtils.getContentType(), selectEngineerRequest());
        Log.w(TAG,"SelectEnginnerResponse url  :%s"+" "+ call.request().url().toString());


        call.enqueue(new Callback<SelectEnginnerResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SelectEnginnerResponse> call, @NonNull Response<SelectEnginnerResponse> response) {

                Log.w(TAG, "SelectEnginnerResponsecall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            Toasty.success(getApplicationContext(), "" + message, Toasty.LENGTH_LONG).show();
                             Log.w(TAG,"SelectEnginnerResponse" + new Gson().toJson(response.body()));
                            List<SelectEnginnerResponse.DataBean> dataBeanList = response.body().getData();


                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<SelectEnginnerResponse> call, Throwable t) {
                        dialog.dismiss();
                        Log.e(TAG, "--->" + t.getMessage());
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }*/


    @SuppressLint("LogNotTimber")
    private void subgroupdetailmanagmentResponseCall() {
        dialog = new Dialog(SubGroupListActivity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SubGroupDetailManagementResponse> call = apiInterface.subgroupdetailmanagmentResponseCall(RestUtils.getContentType(), SubGroupDetailManagementRequest());
        Log.w(TAG,"SubGroupDetailManagementResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SubGroupDetailManagementResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SubGroupDetailManagementResponse> call, @NonNull Response<SubGroupDetailManagementResponse> response) {

                Log.w(TAG,"SubGroupDetailManagementResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            dialog.dismiss();
                            List<SubGroupDetailManagementResponse.DataBean> dataBeanList = response.body().getData();


                            if(dataBeanList != null && dataBeanList.size()>0){
                                rv_subgrouplist.setVisibility(View.VISIBLE);
                                setView(dataBeanList);
                                txt_no_records.setVisibility(View.GONE);
                            }
                           else  {
                                rv_subgrouplist.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Sub-Groups Available");
                            }

                        }


                    } else {
                        dialog.dismiss();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                        //showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SubGroupDetailManagementResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                Log.e("SubGroupDetailManagementResponse", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private SelectEngineerRequest selectEngineerRequest()
    {
       /* "EMPNO": "E9814",*/

        SelectEngineerRequest selectEngineerRequest=new SelectEngineerRequest();
        selectEngineerRequest.setJob_id(job_id);
        Log.w(TAG,"selectEngineerRequest "+ new Gson().toJson(selectEngineerRequest));
        return selectEngineerRequest;
    }
    private SubGroupDetailManagementRequest SubGroupDetailManagementRequest() {
        /*
         * group_id : 61c1e5e09934282617679543
         * search_string
         */
        SubGroupDetailManagementRequest SubGroupDetailManagementRequest = new SubGroupDetailManagementRequest();
        SubGroupDetailManagementRequest.setGroup_id(group_id);
        SubGroupDetailManagementRequest.setSearch_string(search_string);
        Log.w(TAG,"SubGroupDetailManagementRequest "+ new Gson().toJson(SubGroupDetailManagementRequest));
        return SubGroupDetailManagementRequest;
    }
    @SuppressLint("LogNotTimber")
    private void setView(List<SubGroupDetailManagementResponse.DataBean> dataBeanList) {
        rv_subgrouplist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv_subgrouplist.setItemAnimator(new DefaultItemAnimator());
        ServiceListAdapter serviceListAdapter = new ServiceListAdapter(this, dataBeanList,activity_id,job_id, group_id,TAG,status);
        rv_subgrouplist.setAdapter(serviceListAdapter);
    }

}