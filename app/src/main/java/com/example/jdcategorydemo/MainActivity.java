package com.example.jdcategorydemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.jdcategorydemo.adapter.OneTypeAdapter;
import com.example.jdcategorydemo.adapter.ThreeTypeAdapter;
import com.example.jdcategorydemo.adapter.TwoTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.shape.view.ShapeButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OneTypeAdapter oneTypeAdapter;
    private TwoTypeAdapter twoTypeAdapter;
    private ThreeTypeAdapter threeTypeAdapter;
    private List<GoodsTypeBN> typeOne;
    private List<GoodsTypeBN> typeTwo=new ArrayList<>();
    private List<GoodsTypeBN> typeThree=new ArrayList<>();
    private RecyclerView one,two,three;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        handler=new Handler();
        init();
    }

    private void init(){
        String json = getJson(this, "category.json");
        if (!TextUtils.isEmpty(json)){
            //解析数据
            List<GoodsTypeBN> typeBNBaseEN=new Gson().fromJson(json, new TypeToken<List<GoodsTypeBN>>(){}.getType());
            if (typeBNBaseEN!=null){
                typeOne=typeBNBaseEN;
                //设置一级类型
                oneTypeAdapter=new OneTypeAdapter(this,typeOne);
                one.setLayoutManager(new LinearLayoutManager(this));
                one.setAdapter(oneTypeAdapter);

                //设置初始二级类型
                typeTwo.clear();
                typeTwo.addAll(typeOne.get(0).getNextType());
                twoTypeAdapter=new TwoTypeAdapter(this,typeTwo);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                two.setLayoutManager(linearLayoutManager);
                two.setAdapter(twoTypeAdapter);

                //设置初始三级类型
                typeThree.clear();
                typeThree.addAll(typeTwo.get(0).getNextType());
                threeTypeAdapter=new ThreeTypeAdapter(this,typeTwo);
                three.setLayoutManager(new LinearLayoutManager(this));
                three.setAdapter(threeTypeAdapter);

                //一级类型条目点击
                oneTypeAdapter.setOnItemClickListener(new OneTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(View v, int position) {
                        //更换二级三级类型的数据
                        typeTwo.clear();
                        typeTwo.addAll(typeOne.get(position).getNextType());
                        twoTypeAdapter.selectPosition=0;
                        twoTypeAdapter.notifyDataSetChanged();
                        threeTypeAdapter.notifyDataSetChanged();
                    }
                });
                //二级类型条目点击
                twoTypeAdapter.setOnItemClickListener(new TwoTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(View v, int position) {
                        //将三级类型滑动到相应位置
                        LinearLayoutManager threeLayoutManager = ((LinearLayoutManager) three.getLayoutManager());
                        if (threeLayoutManager!=null){
                            threeLayoutManager.scrollToPositionWithOffset(position,0);
                        }
                    }
                });
                three.addOnScrollListener(onScrollListener);
            }
        }
    }
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        /**
         获取第一个可见的item的position
         */
        int currentPosition = 0;
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            LinearLayoutManager twoLayoutManager = (LinearLayoutManager) two.getLayoutManager();
            LinearLayoutManager threeLayoutManager = ((LinearLayoutManager) three.getLayoutManager());
            if (twoLayoutManager!=null&&threeLayoutManager!=null){
                currentPosition = threeLayoutManager.findFirstCompletelyVisibleItemPosition();
                /**
                 这地方需要进行判断，如果下面的Recycle在移动的时候，上面的RecyclerView也是需要进行移动的
                 上面的recyclerview有可能会不可见，这时候，我们必须去判断一下，上面最后的一个item是不是
                 小于下面滑动的位置，或上面第一个item是不是大于下面滑动的位置
                 */
                if (twoLayoutManager.findFirstCompletelyVisibleItemPosition() > currentPosition) {
                    twoLayoutManager.scrollToPositionWithOffset(currentPosition, 0);
                } else if (twoLayoutManager.findFirstCompletelyVisibleItemPosition() < currentPosition) {
                    twoLayoutManager.scrollToPositionWithOffset(currentPosition, 0);
                }

                // 判断滚动的方向和位置，判断是否触发了回弹效果
                if (dy < 0 && !recyclerView.canScrollVertically(-1)) {
                    // 触发了上拉的回弹效果
                    Log.e("TAG", "onScrolled: 触发了上拉的回弹效果" );
                } else if (dy > 0 && !recyclerView.canScrollVertically(1)) {
                    // 触发了下拉的回弹效果
                    currentPosition = typeTwo.size() - 1;
                    Log.e("TAG", "onScrolled: 触发了下拉的回弹效果" );
                }
                twoTypeAdapter.selectPosition=currentPosition;
                twoTypeAdapter.notifyDataSetChanged();
            }
        }
    };


    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}