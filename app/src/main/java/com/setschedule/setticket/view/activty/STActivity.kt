package com.setschedule.setticket.view.activty

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.setschedule.setticket.R
import com.setschedule.setticket.view.activty.menu.About
import com.setschedule.setticket.view.activty.menu.Search
import com.setschedule.setticket.view.activty.menu.Settings
import com.setschedule.setticket.view.fragment.STMapFragment
import com.setschedule.setticket.view.fragment.home.HomePageAdapter
import com.setschedule.setticket.view.fragment.home.LikeFragment
import com.setschedule.setticket.view.fragment.home.MapFragment
import com.setschedule.setticket.view.fragment.home.UnlikeFragment
import com.setschedule.setticket.viewmodel.EventsViewModel

class STActivity:AppCompatActivity (){

    var bottom_nav: BottomNavigationView? = null
    var menu:Menu? = null
    var vp:ViewPager2? = null
    var adapter:HomePageAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setBottomNavigation()
        setActionBar()
        setViewPager()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            run {
                var intent: Intent = Intent(this, Search::class.java)
                startActivity(intent)
            }
        }
//        test()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun test()
    {
        var eventVM:EventsViewModel = ViewModelProvider(this).get(EventsViewModel::class.java)
        eventVM!!.getCount(this)
    }

    fun setActionBar()
    {
        setSupportActionBar(findViewById(R.id.toolbar))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_about-> {
                var intent: Intent = Intent(this, About::class.java)
                startActivity(intent)
                true
            }
            R.id.action_settings-> {
                var intent: Intent = Intent(this, Settings::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    fun setBottomNavigation()
    {
        bottom_nav = findViewById(R.id.bottom_nav)
        bottom_nav!!.setOnNavigationItemSelectedListener {
            vp!!.currentItem=it.itemId
            true
        }
        menu = bottom_nav!!.menu
        menu!!.add(Menu.NONE, 0, Menu.NONE, getString(R.string.main_calendar))
            .setIcon(R.drawable.calendar);
        menu!!.add(Menu.NONE,1, Menu.NONE,  getString(R.string.main_map))
            .setIcon(R.drawable.map);
        menu!!.add(Menu.NONE,2, Menu.NONE, getString(R.string.main_interested))
            .setIcon(R.drawable.accept)
        menu!!.add(Menu.NONE,3, Menu.NONE,getString(R.string.main_decline))
            .setIcon(R.drawable.unlike)
    }

    fun setViewPager()
    {
        vp = findViewById(R.id.vp_main)
        var list:ArrayList<Fragment> = ArrayList()
        list.add(STMapFragment())
        list.add(MapFragment())
        list.add(LikeFragment())
        list.add(UnlikeFragment())
        adapter = HomePageAdapter(this,list)
        vp!!.adapter=adapter
        vp!!.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottom_nav!!.selectedItemId=position
            }
            }
        )

    }

}