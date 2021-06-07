package com.setschedule.setticket.view.fragment.settings

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import kotlinx.android.synthetic.main.fragment_settings_map.*


class MapSettingFragment:Fragment(),LocationListener
{
    var sw_enable:SwitchMaterial? = null

    var tv_radius:TextView? = null
    var tv_lon:TextView? = null
    var tv_lat:TextView? = null
    var tv_unit:TextView? = null
    var seekBar:SeekBar? = null
    var bt_generate:Button? = null

    var locationManager: LocationManager? = null
    var provider:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sw_enable = view.findViewById(R.id.sw_enable)
        tv_radius = view.findViewById(R.id.tv_radius)
        tv_lon = view.findViewById(R.id.tv_lon)
        tv_lat = view.findViewById(R.id.tv_lat)
        if (PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LAT)!!.length<10)
        {
            tv_lat!!.text=""
        }
        else
        {
            tv_lat!!.text="Latitude: "+PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LAT)
        }
        if (PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LON)!!.length<10)
        {
            tv_lon!!.text=""
        }
        else
        {
            tv_lon!!.text="Longitude: "+PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LON)
        }
         tv_unit= view.findViewById(R.id.tv_unit)
         seekBar= view.findViewById(R.id.seekBar)
         bt_generate= view.findViewById(R.id.bt_generate)

        tv_radius!!.text=""+PredictHQPreferences.getAmount(requireActivity(),PredictHQPreferences.TAG_RADIUS)
        seekBar!!.progress=PredictHQPreferences.getAmount(requireActivity(),PredictHQPreferences.TAG_RADIUS)

        sw_enable!!.isChecked = PredictHQPreferences.isEnabled(
            requireActivity(),
            PredictHQPreferences.TAG_NEAR_ME
        )==true

        seekBar!!.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tv_radius!!.text=""+p0!!.progress
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                tv_radius!!.text=""+p0!!.progress
                PredictHQPreferences.update(requireActivity(),PredictHQPreferences.TAG_RADIUS,p0!!.progress)
            }

        })



        sw_enable!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            PredictHQPreferences.update(
                requireActivity(),
                PredictHQPreferences.TAG_NEAR_ME,
                b
            )
        }

        bt_generate!!.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                getCoordinates()
            }
        });

        setupGPS()

    }

    fun setupGPS()
    {
        locationManager =  requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        provider =  locationManager!!.getBestProvider(criteria, false)
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),
                303);
        }
        else
        {
            locationManager!!.getLastKnownLocation(provider!!)
        }

    }

    fun getCoordinates()
    {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),
                303);
        }
        else
        {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this);
            bt_generate!!.isEnabled = false
            bt_generate!!.text = "Please Wait"

        }



    }

    override fun onLocationChanged(p0: Location) {

        PredictHQPreferences.update(
            requireActivity(),
            PredictHQPreferences.TAG_LAT,
            p0.latitude.toString()
        )

        PredictHQPreferences.update(
            requireActivity(),
            PredictHQPreferences.TAG_LON,
            p0.longitude.toString()
        )
        tv_lon!!.text="Longitude: "+PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LON)
        tv_lat!!.text="Latitude: "+PredictHQPreferences.get(requireActivity(), PredictHQPreferences.TAG_LAT)

        locationManager!!.removeUpdates(this)
        bt_generate!!.isEnabled = true
        bt_generate!!.text = "Get Coordinates"
    }


}