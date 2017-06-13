package viewpager.com.demoviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomViewPager headerPager, footerPager;
    TabLayout tabLayout;
    View tabVideo, tabImages, tabMilestone;
    ImageView imageTab0, imageTab1, imageTab2;
    TextView tabTxt0, tabTxt1, tabTxt2;
    CirclePageIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerPager = (CustomViewPager) findViewById(R.id.headerPager);
        headerPager.setSwipeable(true);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setRadius(10);
        mIndicator.setStrokeWidth(3);
        headerPager.setAdapter(new HeaderPagerAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(headerPager);
        headerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("position===header " + position);
                mIndicator.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        footerPager = (CustomViewPager) findViewById(R.id.footerPager);
        footerPager.setSwipeable(true);
        footerPager.setOffscreenPageLimit(3);
        setFooterPager(footerPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(footerPager);
        createTab();

        footerPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("position===footer " + tab.getPosition());
                if (tab.getPosition() == 0) {
                    tabTxt0.setTextColor(getResources().getColor(R.color.colorAccent));
                    imageTab0.setImageResource(R.drawable.select_video);
                    tabTxt1.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab1.setImageResource(R.drawable.image);
                    tabTxt2.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab2.setImageResource(R.drawable.milestone);
                    footerPager.setCurrentItem(0);
                } else if (tab.getPosition( )== 1) {
                    tabTxt1.setTextColor(getResources().getColor(R.color.colorAccent));
                    imageTab1.setImageResource(R.drawable.select_image);
                    tabTxt0.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab0.setImageResource(R.drawable.video);
                    tabTxt2.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab2.setImageResource(R.drawable.milestone);
                    footerPager.setCurrentItem(1);
                } else if (tab.getPosition() == 2) {
                    tabTxt2.setTextColor(getResources().getColor(R.color.colorAccent));
                    imageTab2.setImageResource(R.drawable.select_milestone);
                    tabTxt1.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab1.setImageResource(R.drawable.image);
                    tabTxt0.setTextColor(getResources().getColor(R.color.unselect_color));
                    imageTab0.setImageResource(R.drawable.video);
                    footerPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    /*
    * Create the three tab.
    * */
    private void createTab() {

        tabVideo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTxt0 = (TextView) tabVideo.findViewById(R.id.tab);
        tabTxt0.setTextColor(getResources().getColor(R.color.colorAccent));
        tabTxt0.setText("VIDEOS");
        imageTab0 = (ImageView) tabVideo.findViewById(R.id.image);
        imageTab0.setImageResource(R.drawable.select_video);
        tabLayout.getTabAt(0).setCustomView(tabVideo);

        tabImages = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTxt1 = (TextView) tabImages.findViewById(R.id.tab);
        tabTxt1.setTextColor(getResources().getColor(R.color.unselect_color));
        tabTxt1.setText("IMAGES");
        imageTab1 = (ImageView) tabImages.findViewById(R.id.image);
        imageTab1.setImageResource(R.drawable.image);
        tabLayout.getTabAt(1).setCustomView(tabImages);

        tabMilestone = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTxt2 = (TextView) tabMilestone.findViewById(R.id.tab);
        tabTxt2.setTextColor(getResources().getColor(R.color.unselect_color));
        tabTxt2.setText("MILESTONE");
        imageTab2 = (ImageView) tabMilestone.findViewById(R.id.image);
        imageTab2.setImageResource(R.drawable.milestone);
        tabLayout.getTabAt(2).setCustomView(tabMilestone);

    }

    /*
    * Set Pager for the Footer
    * */
    private void setFooterPager(CustomViewPager viewPager) {
        FooterPagerAdapter adapter = new FooterPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new VideoFragement());
        adapter.addFrag(new ImagesFragement());
        adapter.addFrag(new MilestonesFragement());
        viewPager.setAdapter(adapter);
    }

    /*
    * Footer Pager Adapter
    * */
    private class FooterPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        FooterPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

    /*
    * Header Pager Adapter
    * */
    private class HeaderPagerAdapter extends FragmentStatePagerAdapter {
        Fragment fragment;

        public HeaderPagerAdapter(FragmentManager fragmentManager) {
            // TODO Auto-generated constructor stub
            super(fragmentManager);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return fragment = new CommonHeaderFragement();
            if (position == 1)
                return fragment = new CommonHeaderFragement();
            if (position == 2)
                return fragment = new CommonHeaderFragement();
            if (position == 3)
                return fragment = new CommonHeaderFragement();
            if (position == 4)
                return fragment = new CommonHeaderFragement();
            return null;
        }
    }

}
