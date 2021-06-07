package com.setschedule.setticket.view.fragment.settings.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences


class CategorySettingsFragment:Fragment()
{
    var categories:ArrayList<Category> = ArrayList()
    var rv_categories:RecyclerView? = null
    var categoryAdapter:CategoryAdapter? = null
    var layoutManager:LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutManager = LinearLayoutManager(requireActivity())
        return inflater.inflate(R.layout.fragment_settings_labels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_categories = view.findViewById(R.id.rv_categories)
        rv_categories!!.layoutManager = layoutManager
        rv_categories!!.addItemDecoration(DividerItemDecoration(context, 0))

        generateCategories()
    }


    fun generateCategories()
    {
        var sports:Category = Category("Sports", PredictHQPreferences.TAG_CAT_SPORTS)

        sports.addLabel(Label("Soccer",PredictHQPreferences.TAG_LABEL_SOCCER))
        sports.addLabel(Label("Basketball",PredictHQPreferences.TAG_LABEL_BASKETBALL))
        sports.addLabel(Label("Ice Hockey",PredictHQPreferences.TAG_LABEL_HOCKEY))
        sports.addLabel(Label("Rugby",PredictHQPreferences.TAG_LABEL_RUGBY))
        sports.addLabel(Label("Baseball",PredictHQPreferences.TAG_LABEL_BASEBALL))
        sports.addLabel(Label("NFL",PredictHQPreferences.TAG_LABEL_NFL))
        sports.addLabel(Label("MLB",PredictHQPreferences.TAG_LABEL_MLB))
        sports.addLabel(Label("NHL",PredictHQPreferences.TAG_LABEL_NHL))
        sports.addLabel(Label("NBA",PredictHQPreferences.TAG_LABEL_NBA))
        sports.addLabel(Label("Closed Door",PredictHQPreferences.TAG_LABEL_CLOSED_D))
        sports.addLabel(Label("Outdoor",PredictHQPreferences.TAG_LABEL_OUT_D))


        var conference:Category = Category("Conferences", PredictHQPreferences.TAG_CAT_CONFERENCE)
        conference.addLabel(Label("Business",PredictHQPreferences.TAG_LABEL_BUSINESS))
        conference.addLabel(Label("Education",PredictHQPreferences.TAG_LABEL_EDUCATION))
        conference.addLabel(Label("Health",PredictHQPreferences.TAG_LABEL_HEALTH))
        conference.addLabel(Label("Technology",PredictHQPreferences.TAG_LABEL_TECH))




        var expo:Category = Category("Expos", PredictHQPreferences.TAG_CAT_EXPO)
        expo.addLabel(Label("Education",PredictHQPreferences.TAG_LABEL_EXPO_EDU))
        expo.addLabel(Label("Technology",PredictHQPreferences.TAG_LABEL_EXPO_TEC))
        expo.addLabel(Label("Entertainment",PredictHQPreferences.TAG_LABEL_ENTERTAINMENT))
        expo.addLabel(Label("Career",PredictHQPreferences.TAG_LABEL_CAREER))
        expo.addLabel(Label("Performing Arts",PredictHQPreferences.TAG_LABEL_ARTS))

        var concert:Category = Category("Concerts", PredictHQPreferences.TAG_CAT_CONCERT)

        categories.add(sports)
        categories.add(conference)
        categories.add(expo)
        categories.add(concert)
        categoryAdapter = CategoryAdapter(categories)
        rv_categories!!.adapter = categoryAdapter
    }
}