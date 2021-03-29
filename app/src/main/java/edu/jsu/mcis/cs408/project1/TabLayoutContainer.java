package edu.jsu.mcis.cs408.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabLayoutContainer extends Fragment {

    private TabLayoutAdapter tabLayoutAdapter;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_container, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Create a TabLayoutAdapter (subclassed from FragmentStateAdapter) to return a fragment on request
        tabLayoutAdapter = new TabLayoutAdapter(this);

        // Create a ViewPager2 to facilitate switching between fragments
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(tabLayoutAdapter);

        // Create a TabLayoutMediator to provide a clickable list of tabs in the ViewPager
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText( ((TabFragment)tabLayoutAdapter.createFragment(position)).getTabTitle() );
            }
        }).attach();

    }

}