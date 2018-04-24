package com.itfollowme.zhihu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.model.DetailBean;
import com.itfollowme.zhihu.util.HtmlUtil;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    private TextView mTvDetailBarCopyright;
    private ImageView mIvDetailBarImage;
    private WebView mWvBody;

    private DetailBean detailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mIvDetailBarImage = findViewById(R.id.iv_detail_bar_image);
        mTvDetailBarCopyright = findViewById(R.id.tv_detail_bar_copyright);
        mWvBody = findViewById(R.id.wv_body);

        int id = getIntent().getIntExtra("id",9666020);
        detailBean = parseJSON(id);

        String htmlData = HtmlUtil.createHtmlData(detailBean.getBody(),detailBean.getCss(),detailBean.getJs());
        mTvDetailBarCopyright.setText(detailBean.getImageSource());
        Glide.with(this).load(detailBean.getImage()).into(mIvDetailBarImage);
        mWvBody.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public DetailBean parseJSON(int id){
        DetailBean detailBean = null;
        try {
            InputStream inputStream = getAssets().open(id+".json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            detailBean = JSON.parseObject(text, DetailBean.class);
            //Log.i("zhihu",detailBean.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detailBean;
    }
}
