package com.setschedule.setticket.view.activty.menu

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.setschedule.setticket.R
import com.setschedule.setticket.view.fragment.settings.category.CategorySettingsFragment
import com.setschedule.setticket.view.fragment.settings.MapSettingFragment
import com.setschedule.setticket.view.fragment.settings.SecuritySettingsFragment
import com.setschedule.setticket.view.fragment.settings.SettingsPageAdapter

class Settings:MenuActivity()
{
    var bottom_nav: BottomNavigationView? = null
    var menu:Menu? = null
    var vp: ViewPager2? = null
    var adapter:SettingsPageAdapter? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setActionBar()
        setBottomNavigation()
        setViewPager()
    }

    override fun title(): Int {
       return R.string.action_settings
    }
    fun setViewPager()
    {
        vp = findViewById(R.id.vp_main)
        var list:ArrayList<Fragment> = ArrayList()

        list.add(CategorySettingsFragment())
        list.add(MapSettingFragment())
        list.add(SecuritySettingsFragment())
        adapter = SettingsPageAdapter(this,list)
        vp!!.adapter=adapter
        vp!!.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottom_nav!!.selectedItemId=position
            }
        }
        )

    }

    fun setBottomNavigation()
    {
        bottom_nav = findViewById(R.id.bottom_nav)
        bottom_nav!!.setOnNavigationItemSelectedListener {
            vp!!.currentItem=it.itemId
            true
        }
        menu = bottom_nav!!.menu
        menu!!.add(Menu.NONE, 0, Menu.NONE, getString(R.string.settings_cat))
                .setIcon(R.drawable.cat_settings);
        menu!!.add(Menu.NONE,1, Menu.NONE,getString(R.string.settings_map))
                .setIcon(R.drawable.map_settings);
        menu!!.add(Menu.NONE,2, Menu.NONE, getString(R.string.settings_security))
                .setIcon(R.drawable.security_settings)

    }

}