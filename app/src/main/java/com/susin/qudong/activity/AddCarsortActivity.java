package com.susin.qudong.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.susin.qudong.R;
import com.susin.qudong.adapter.GvZiAdapter;
import com.susin.qudong.application.Myapplication;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCarsortActivity extends AppCompatActivity {

    @BindView(R.id.tv_car_zi)
    TextView tv_car_zi;
    @BindView(R.id.tv_car_zimu)
    TextView tv_car_zimu;
    @BindView(R.id.tv_car_selectpai)
    TextView tv_car_selectpai;
    @BindView(R.id.tv_car_selectxi)
    TextView tv_car_selectxi;
    @BindView(R.id.tv_car_selectcolor)
    TextView tv_car_selectcolor;
    @BindView(R.id.ed_car_carnum)
    EditText ed_car_carnum;

    private View view;
    private Dialog dialog;
    String[] zimu = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "V", "U", "W", "X",
            "Y", "Z" };
    List list = Arrays.asList(zimu);
    String[] zi = { "京", "泸", "津", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁",
            "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", "蒙", "陕", "吉", "闽", "贵",
            "粤", "川", "青", "藏", "琼", "宁" };
    List list2 = Arrays.asList(zi);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carsort);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.addcarsort_back ,R.id.lv_zi ,R.id.lv_zimu ,R.id.tv_car_selectpai ,R.id.tv_car_selectxi ,R.id.tv_car_selectcolor ,R.id.bt_surecar})
    public void click(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.addcarsort_back:
                finish();
                break;
            case R.id.lv_zi:
                showDialog(true);
                break;
            case R.id.lv_zimu:
                showDialog(false);
                break;
            case R.id.tv_car_selectpai:
                Myapplication.carname = "";
                intent = new Intent(this,
                        ActivityChepinpai.class);
                startActivity(intent);
                break;
            case R.id.tv_car_selectxi:
                if (Myapplication.parentname2 == null
                        || Myapplication.parentname2.equals("")) {
                    Toast.makeText(this, "请先选择品牌车系", 0).show();
                } else {
                    intent = new Intent(this,
                            Activity_CheDetail.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_car_selectcolor:
                if (Myapplication.carId == null
                        || Myapplication.carId.equals("")) {
                    Toast.makeText(this, "请先选择品牌车型、车系", 0).show();
                } else {
                    intent=new Intent(this,Activity_selectcarcolor.class);
                    startActivity(intent);
                }
                break;
            case R.id.bt_surecar:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(Myapplication.parentname2 == null)
                && !Myapplication.parentname2.equals("")) {
            tv_car_selectpai.setText(Myapplication.parentname1 + Myapplication.parentname2jian);
        }

        if (!(Myapplication.carname == null)
                && !Myapplication.carname.equals("")) {
            // if (!(MLApplication.carname==null)) {
            tv_car_selectxi.setText(Myapplication.carname);
        }
    }

    /**
	 * 选择车牌号
	 */
    private void showDialog(final Boolean b) {
        view = getLayoutInflater().inflate(R.layout.car_zi, null);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏,gaokuan
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);

        GridView gv_carzi_select = (GridView) view
                .findViewById(R.id.gv_carzi_select);
        if (b) {
            GvZiAdapter adapter = new GvZiAdapter(this,
                    list2);
            gv_carzi_select.setAdapter(adapter);
        } else {
            GvZiAdapter adapter = new GvZiAdapter(this,
                    list);
            gv_carzi_select.setAdapter(adapter);
        }

        dialog.show();

        gv_carzi_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (b) {
                    tv_car_zi.setText(list2.get(position) + "");
                } else {
                    tv_car_zimu.setText(list.get(position) + "");
                }
                dialog.dismiss();
            }
        });
    }
}
