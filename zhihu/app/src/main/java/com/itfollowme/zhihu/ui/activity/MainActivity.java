package com.itfollowme.zhihu.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.adapter.ZhihuMainAdapter;
import com.itfollowme.zhihu.ui.fragment.DailyFragment;
import com.itfollowme.zhihu.ui.fragment.HotFragment;
import com.itfollowme.zhihu.ui.fragment.SectionFragment;
import com.itfollowme.zhihu.ui.fragment.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private ZhihuMainAdapter zhihuMainAdapter;

    private List<Fragment> fragments = new ArrayList<>();

    private String[] tabTitle = new String[]{"日报","主题","专栏","热门"};


    private Toolbar mToolbar;

    //private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mTabLayout = findViewById(R.id.tab_zhihu_main);
        mViewPager = findViewById(R.id.vp_zhihu_main);


        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        zhihuMainAdapter = new ZhihuMainAdapter(getSupportFragmentManager(),fragments);


        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        for(int i = 0;i<tabTitle.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }

        mViewPager.setAdapter(zhihuMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0;i<tabTitle.length;i++){
            mTabLayout.getTabAt(i).setText(tabTitle[i]);
        }
/*        for(int i=0;i<3;i++){
            BlankFragment fragment = BlankFragment.newInstance(""+i,"");
            fragments.add(fragment);
            titles.add(String.valueOf(i));



            mTabLayout.addTab(mTabLayout.newTab());
        }
        zhihuMainAdapter = new ZhihuMainAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(zhihuMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0;i<titles.size();i++){
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            textView.setText(String.valueOf(i));
            mTabLayout.getTabAt(i).setCustomView(textView);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("提示");

        builder.setMessage("确定退出知乎日报吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        AlertDialog dialog = builder.show();

    }


}
