package com.toly1994.vvi_mds.pkg5_coll_palette;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.toly1994.test.random.DataUtils;
import com.toly1994.vvi_mds.R;
import com.toly1994.vvi_mds.pkg4_app_coo.ACAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class V07_CollapsingPaletteActivity extends AppCompatActivity {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
//    @BindView(R.id.id_tv_content)
//    TextView mIdTvContent;
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    private CollapsingToolbarLayout mCTL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.a3_palette);
        ButterKnife.bind(this);
        //适配虚拟键盘
//        CompatNavigationBar.handle(this);
        setSupportActionBar(mToolbar);
        //显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ACAdapter ACAdapter = new ACAdapter(this, DataUtils.getRandomName(30, true));
        mRvContent.setAdapter(ACAdapter);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));

        //处理一下ScrollView
//        ScrollView contentSv = findViewById(R.id.sv_content);
//        ViewCompat.setNestedScrollingEnabled(contentSv, true);
        //设置字体---可无视
//        TextHelper.setFont(this, mIdTvContent, "fonts/ygyxsziti2.0.ttf");

//        mCTL = findViewById(R.id.ctl_toolbar);
//        mIvHeader.setImageResource(R.mipmap.bg_android);
//        palette(R.mipmap.bg_android, mCTL);
    }

    private void palette(int res, CollapsingToolbarLayout ctl) {
        Palette.from(BitmapFactory.decodeResource(getResources(), res)).generate(
                palette -> {
                    int color = palette.getDominantColor(
                            ContextCompat.getColor(V07_CollapsingPaletteActivity.this, R.color.blue));
                    int titleTextColor = palette.getDominantSwatch().getTitleTextColor();
                    ctl.setContentScrimColor(color);//设置toolBar颜色
                    ctl.setCollapsedTitleTextColor(titleTextColor);
                    ctl.setExpandedTitleColor(titleTextColor);
                    ToolbarColorizeHelper.colorizeToolbar(mToolbar, titleTextColor, V07_CollapsingPaletteActivity.this);
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
