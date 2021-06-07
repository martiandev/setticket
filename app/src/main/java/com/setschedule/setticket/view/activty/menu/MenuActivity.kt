package com.setschedule.setticket.view.activty.menu

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.setschedule.setticket.R

abstract class MenuActivity:AppCompatActivity()
{
    fun setActionBar()
    {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.title=getString(title())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_about-> {
                if(title()!=R.string.action_about)
                {
                    var intent: Intent = Intent(this, About::class.java)
                    startActivity(intent)
                }
                true
            }

            R.id.action_settings-> {
                if(title()!=R.string.action_settings)
                {
                    var intent: Intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                }
                true
            }
            android.R.id.home ->
            {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    abstract fun title():Int
}
