package com.idisfkj.enhancerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idisfkj.enhancerecyclerview.adapter.RecyclerViewAdapter;
import com.idisfkj.enhancerecyclerview.view.EnhanceRecyclerView;
import com.idisfkj.enhancerecyclerview.view.MyItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    EnhanceRecyclerView mRecyclerView;
    private View view;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));

        list = new ArrayList<>();
        for (int i = 0; i < 13; i++)
            list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3434000068,2874621746&fm=116&gp=0.jpg");

        adapter = new RecyclerViewAdapter(this, list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new MyItemDecoration(this));

        mRecyclerView.setPullToRefreshListener(new EnhanceRecyclerView.PullToRefreshListener() {
            @Override
            public void onRefreshing() {
//                mHandler.sendEmptyMessageDelayed(2, 3000);
                refreshData();
            }
        });

        mRecyclerView.setLoadMoreListener(new EnhanceRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
//                mHandler.sendEmptyMessageDelayed(1,3000);
                loadMoreData();
            }
        });

    }

    public void refreshData(){
        for (int i = 0; i < 2; i++)
            list.add(0, "http://img15.3lian.com/2015/a1/16/d/202.jpg");
        mRecyclerView.setRefreshComplete();
    }

    public void loadMoreData(){
         for (int i = 0; i < 7; i++)
             list.add("http://a.hiphotos.baidu.com/image/pic/item/03087bf40ad162d96270c41b13dfa9ec8a13cd1f.jpg");
        mRecyclerView.setLoadMoreComplete();
    }

   /* Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                for (int i = 0; i < 7; i++)
                    list.add("http://a.hiphotos.baidu.com/image/pic/item/03087bf40ad162d96270c41b13dfa9ec8a13cd1f.jpg");
                mRecyclerView.setLoadMoreComplete();
            }
            if (msg.what == 2) {
                for (int i = 0; i < 2; i++)
                    list.add(0, "http://img15.3lian.com/2015/a1/16/d/202.jpg");
                mRecyclerView.setRefreshComplete();
            }
            super.handleMessage(msg);
        }
    };*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("add header");
        menu.add("add footer");
        menu.add("change");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //不能在onCreate()中获取View,防止添加时的无缘故的错乱.
        View Hview = LayoutInflater.from(this).inflate(R.layout.head_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Hview.setLayoutParams(params);
//        if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            view.setLayoutParams(params);
//        }
        if (item.getTitle().toString() == "add header") {
            mRecyclerView.addHeaderView(Hview);
        } else if (item.getTitle().toString() == "add footer") {
            mRecyclerView.addFooterView(view);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }
}
