package com.example.example10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CardActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TextView title_toolbar;
    private ImageButton more_vert;
    private ImageButton arrow_back;
    private ImageButton close_toolbar_edit_the_name_of_job,close_toolbar_labels;
    private ImageButton check_toolbar;
    private TextView the_name_of_job;
    private TextView list_name;
    private EditText edit_the_name_of_job, edit_the_name_of_job1;
    private TextView labels;
    private TextView members;
    private TextView end_date;
    private TextView list_job;
    private TextView attachments;
    private LinearLayout linearLayout_labels;
    private CheckBox checkbox_mau_vang,checkbox_mau_cam,checkbox_mau_tim,checkbox_mau_xanh,checkbox_mau_xam;
    private EditText mau_vang,mau_tim,mau_xanh,mau_cam,mau_xam;
    ImageButton but_mau_vang,but_mau_tim,but_mau_xanh,but_mau_cam,but_mau_xam;

    ListView listView_members;
    ArrayList<Item_members> arrayList_members;
    MenuAdapter_members adapter_members;

    ListView listView_list_job;
    ArrayList<Item_list_job> arrayList_list_job;
    MenuAdapter_list_job adapter_list_job;

    View edit_list_job;
    ImageButton close_list_job;
    ImageButton check_list_job;
    ImageButton more_vert_list_job;
    LinearLayout linearLayout_list_job;
    EditText ten_cong_viec_list_job;

    ListView listView_attachments;
    ArrayList<Item_attachments> arrayList_attachments;
    MenuAdapter_attachments adapter_attachments;

    View edit_list_attachments;
    ImageButton close_list_attachments;
    ImageButton check_list_attachments;
    ImageButton more_vert_list_attachments;
    LinearLayout linearLayout_list_attachments;
    EditText tep_dinh_kem_list_attachments;

    Card card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        AnhXa();
        linearLayout_labels.setVisibility(View.GONE);
        actionToolbar();//toolbar
        actionlabels();//labels
        action_popup_menu_toolbar();//more_vert
        action_end_date_time();//end_date
        action_edit_the_name_of_job();//edit_the_name_of_job;
        action_arrow_back();
        action_members();
        action_attachments();
        action_list_job();

        /*arrayList = new ArrayList<>();
        arrayList.add(new Item_members(R.drawable.ic_baseline_attach_file_24,"nguyentuan",
                "nguyentuan@gmail.com"));
        arrayList.add(new Item_members(R.drawable.ic_baseline_close_24,"hello","777"));
        adapter = new MenuAdapter_members(MainActivity.this,R.layout.dong_item_members,arrayList);
        listView.setAdapter(adapter);*/

    }

    private void action_list_job() {
        arrayList_list_job = new ArrayList<>();
        adapter_list_job = new MenuAdapter_list_job(CardActivity.this,R.layout.dong_item_list_job,arrayList_list_job);
        listView_list_job.setAdapter(adapter_list_job);

        list_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_list_job.setVisibility(View.VISIBLE);
                linearLayout_list_job.setVisibility(View.VISIBLE);
                check_list_job.setVisibility(View.VISIBLE);
                close_list_job.setVisibility(View.VISIBLE);
                ten_cong_viec_list_job.requestFocus();
                showSoftInput(ten_cong_viec_list_job);
            }
        });
        if (ten_cong_viec_list_job.getText().length() != 0) {
            check_list_job.setEnabled(true);
        }

        close_list_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ten_cong_viec_list_job.getText().length() ==0){
                    ten_cong_viec_list_job.setHint("Tên công việc");
                }
                linearLayout_list_job.setVisibility(View.GONE);
                ten_cong_viec_list_job.setText("");
                hideSoftInputFromWindow(ten_cong_viec_list_job);
            }
        });
        check_list_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ten_cong_viec_list_job.getText().length() !=0){
                    arrayList_list_job.add(new Item_list_job(ten_cong_viec_list_job.getText().toString()));
                    adapter_list_job.notifyDataSetChanged();
                }
                ten_cong_viec_list_job.setText("");
            }
        });
        more_vert_list_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CardActivity.this,more_vert_list_job);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_more_vert_list_job,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.an_more_vert_list_job:
                                close_list_job.setVisibility(View.INVISIBLE);
                                check_list_job.setVisibility(View.INVISIBLE);
                                linearLayout_list_job.setVisibility(View.GONE);
                                ten_cong_viec_list_job.setText("");
                                hideSoftInputFromWindow(ten_cong_viec_list_job);
                                break;
                            case R.id.xoa_more_vert_list_job:
                                arrayList_list_job.clear();
                                adapter_list_job.notifyDataSetChanged();
                                edit_list_job.setVisibility(View.GONE);
                                ten_cong_viec_list_job.setText("");
                                hideSoftInputFromWindow(ten_cong_viec_list_job);
                                break;
                            default:break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }
    private void action_attachments() {
        arrayList_attachments = new ArrayList<>();
        adapter_attachments = new MenuAdapter_attachments(CardActivity.this,R.layout.dong_item_attachments,arrayList_attachments);
        listView_attachments.setAdapter(adapter_attachments);
        attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_list_attachments.setVisibility(View.VISIBLE);
                linearLayout_list_attachments.setVisibility(View.VISIBLE);
                close_list_attachments.setVisibility(View.VISIBLE);
                check_list_attachments.setVisibility(View.VISIBLE);
                tep_dinh_kem_list_attachments.requestFocus();
                showSoftInput(tep_dinh_kem_list_attachments);
            }
        });
        if (tep_dinh_kem_list_attachments.getText().length() != 0) {
            check_list_attachments.setEnabled(true);
        }
        close_list_attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tep_dinh_kem_list_attachments.getText().length() ==0){
                    tep_dinh_kem_list_attachments.setHint("Tên công việc");
                }
                linearLayout_list_attachments.setVisibility(View.GONE);
                tep_dinh_kem_list_attachments.setText("");
                hideSoftInputFromWindow(tep_dinh_kem_list_attachments);
            }
        });
        check_list_attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tep_dinh_kem_list_attachments.getText().length() !=0){
                    arrayList_attachments.add(new Item_attachments(tep_dinh_kem_list_attachments.getText().toString()));
                    adapter_attachments.notifyDataSetChanged();
                }
                tep_dinh_kem_list_attachments.setText("");
            }
        });
        more_vert_list_attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CardActivity.this,more_vert_list_attachments);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_more_vert_list_attachments,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.an_more_vert_list_attachments:
                                close_list_attachments.setVisibility(View.INVISIBLE);
                                check_list_attachments.setVisibility(View.INVISIBLE);
                                linearLayout_list_attachments.setVisibility(View.GONE);
                                tep_dinh_kem_list_attachments.setText("");
                                hideSoftInputFromWindow(tep_dinh_kem_list_attachments);
                                break;
                            case R.id.xoa_more_vert_list_attachments:
                                arrayList_attachments.clear();
                                adapter_attachments.notifyDataSetChanged();
                                edit_list_attachments.setVisibility(View.GONE);
                                tep_dinh_kem_list_attachments.setText("");
                                hideSoftInputFromWindow(tep_dinh_kem_list_attachments);
                                break;
                            default:break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void action_members() {
        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(CardActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customer_dialog_members);
                dialog.show();
            }
        });
    }

    private void action_arrow_back() {
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void action_edit_the_name_of_job() {
        edit_the_name_of_job.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    arrow_back.setVisibility(View.GONE);
                    more_vert.setVisibility(View.GONE);
                    check_toolbar.setVisibility(View.VISIBLE);
                    close_toolbar_edit_the_name_of_job.setVisibility(View.VISIBLE);
                    title_toolbar.setText("Chỉnh sửa miêu tả");
                    edit_the_name_of_job.setVisibility(View.GONE);
                    edit_the_name_of_job1.setVisibility(View.VISIBLE);
                    edit_the_name_of_job1.requestFocus();
                    showSoftInput(edit_the_name_of_job1);
                }
            }
        });
        close_toolbar_edit_the_name_of_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrow_back.setVisibility(View.VISIBLE);
                more_vert.setVisibility(View.VISIBLE);
                check_toolbar.setVisibility(View.GONE);
                close_toolbar_edit_the_name_of_job.setVisibility(View.GONE);
                title_toolbar.setText("");
                edit_the_name_of_job.setVisibility(View.VISIBLE);
                edit_the_name_of_job1.setVisibility(View.GONE);
                if(edit_the_name_of_job1.getText().toString().equals("")){
                    edit_the_name_of_job.setText("");
                }
                hideSoftInputFromWindow(edit_the_name_of_job);
            }
        });
        check_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrow_back.setVisibility(View.VISIBLE);
                more_vert.setVisibility(View.VISIBLE);
                check_toolbar.setVisibility(View.GONE);
                close_toolbar_edit_the_name_of_job.setVisibility(View.GONE);
                title_toolbar.setText("");
                edit_the_name_of_job.setVisibility(View.VISIBLE);
                edit_the_name_of_job1.setVisibility(View.GONE);
                edit_the_name_of_job.setText(edit_the_name_of_job1.getText().toString());
                hideSoftInputFromWindow(edit_the_name_of_job);
            }
        });
    }

    private void action_popup_menu_toolbar() {
        more_vert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CardActivity.this,more_vert);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_toolbar,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.chia_se_lien_ket:
                                Toast.makeText(CardActivity.this,"Chia sẻ liên kết",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.xem:
                                Toast.makeText(CardActivity.this,"Xem",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.di_chuyen_the:
                                Toast.makeText(CardActivity.this,"Di chuyển thẻ",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.sao_chep_the:
                                Toast.makeText(CardActivity.this,"Sao chép thẻ",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.luu_tru:
                                Toast.makeText(CardActivity.this,"Lưu trữ",Toast.LENGTH_SHORT).show();
                                break;
                            default:break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void action_end_date_time(){
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year_now = calendar.get(Calendar.YEAR);
                int month_now = calendar.get(Calendar.MONTH);
                int day_now = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CardActivity.this, new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        end_date.append(simpleDateFormat.format(calendar.getTime()));
                    }
                },year_now , month_now, day_now);
                datePickerDialog.show();
                final int hour_now = calendar.get(Calendar.HOUR_OF_DAY);
                int minute_now = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CardActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        calendar.set(0,0,0,hourOfDay,minute);
                        end_date.setText(simpleDateFormat.format(calendar.getTime())+" ");
                    }
                } ,hour_now, minute_now,true);
                timePickerDialog.show();
            }
        });
    }
    private void actionlabels() {
        labels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout_labels.setVisibility(View.VISIBLE);
                arrow_back.setVisibility(View.GONE);
                close_toolbar_labels.setVisibility(View.VISIBLE);
                title_toolbar.setText("Chỉnh sửa nhãn");

                close_toolbar_labels.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout_labels.setVisibility(View.GONE);
                        arrow_back.setVisibility(View.VISIBLE);
                        close_toolbar_labels.setVisibility(View.GONE);
                        title_toolbar.setText("");
                        hideSoftInputFromWindow(mau_vang);
                        checkbox_mau(checkbox_mau_vang,mau_vang);
                        checkbox_mau(checkbox_mau_cam,mau_cam);
                        checkbox_mau(checkbox_mau_xanh,mau_xanh);
                        checkbox_mau(checkbox_mau_tim,mau_tim);
                        checkbox_mau(checkbox_mau_xam,mau_xam);
                        but_mau(but_mau_vang,mau_vang);
                        but_mau(but_mau_cam,mau_cam);
                        but_mau(but_mau_xanh,mau_xanh);
                        but_mau(but_mau_tim,mau_tim);
                        but_mau(but_mau_xam,mau_xam);
                    }
                });

            }
        });
    }
    private void checkbox_mau(CheckBox checkBox,EditText editText){
        if(checkBox.isChecked()){
            labels.setText(editText.getText().toString());
            labels.setBackground(editText.getBackground());
            editText.setEnabled(false);
        }
        else{
            labels.setText("Nhãn");
            labels.setBackground(members.getBackground());
        }
    }
    private void but_mau(ImageButton imageButton, EditText editText){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(true);
                editText.requestFocus();
                showSoftInput(editText);
            }
        });
    }
    private void hideSoftInputFromWindow(EditText hide){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(hide.getWindowToken(), 0);
    }
    private void showSoftInput(EditText show){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(show,InputMethodManager.SHOW_FORCED);
    }
    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        //getSupportActionBar().setTitle("Hello");
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        more_vert = findViewById(R.id.more_vert);
        arrow_back = findViewById(R.id.arrow_back);
        title_toolbar = findViewById(R.id.title_toolbar);
        close_toolbar_edit_the_name_of_job = findViewById(R.id.close_toolbar_edit_the_name_of_job);
        close_toolbar_labels = findViewById(R.id.close_toolbar_labels);
        check_toolbar = findViewById(R.id.check_toolbar);
        the_name_of_job = findViewById(R.id.the_name_of_job);
        list_name = findViewById(R.id.list_name);
        edit_the_name_of_job = findViewById(R.id.edit_the_name_of_job);
        edit_the_name_of_job1 = findViewById(R.id.edit_the_name_of_job1);
        labels = findViewById(R.id.labels);
        members = findViewById(R.id.members);
        end_date = findViewById(R.id.end_date);
        list_job = findViewById(R.id.list_job);
        attachments = findViewById(R.id.attachments);

        linearLayout_labels = findViewById(R.id.linearLayout_labels);

        checkbox_mau_cam = findViewById(R.id.checkbox_mau_cam);
        checkbox_mau_vang = findViewById(R.id.checkbox_mau_vang);
        checkbox_mau_tim = findViewById(R.id.checkbox_mau_tim);
        checkbox_mau_xam = findViewById(R.id.checkbox_mau_xam);
        checkbox_mau_xanh = findViewById(R.id.checkbox_mau_xanh);
        mau_vang = findViewById(R.id.mau_vang);
        mau_cam = findViewById(R.id.mau_cam);
        mau_xanh = findViewById(R.id.mau_xanh);
        mau_xam = findViewById(R.id.mau_xam);
        mau_tim = findViewById(R.id.mau_tim);
        but_mau_vang = findViewById(R.id.but_mau_vang);
        but_mau_cam = findViewById(R.id.but_mau_cam);
        but_mau_tim = findViewById(R.id.but_mau_tim);
        but_mau_xanh = findViewById(R.id.but_mau_xanh);
        but_mau_xam = findViewById(R.id.but_mau_xanh);

        //listView_members = findViewById(R.id.listview_members);

        edit_list_job = findViewById(R.id.edit_list_job);
        close_list_job = findViewById(R.id.close_list_job);
        check_list_job = findViewById(R.id.check_list_job);
        more_vert_list_job = findViewById(R.id.more_vert_list_job);
        linearLayout_list_job = findViewById(R.id.linearLayout_list_job);
        ten_cong_viec_list_job = findViewById(R.id.ten_cong_viec_list_job);

        listView_list_job = findViewById(R.id.listview_list_job);

        edit_list_attachments = findViewById(R.id.edit_list_attachments);
        close_list_attachments = findViewById(R.id.close_list_attachments);
        check_list_attachments = findViewById(R.id.check_list_attachments);
        more_vert_list_attachments = findViewById(R.id.more_vert_list_attachments);
        linearLayout_list_attachments = findViewById(R.id.linearLayout_list_attachments);
        tep_dinh_kem_list_attachments = findViewById(R.id.tep_dinh_kem_list_attachments);
        listView_attachments = findViewById(R.id.listview_list_attachments);

    }
}