package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import com.example.myapplication.adapter.CardsAdapter

class Fragment : Fragment(R.layout.fragment) {

    private var gridView: GridView? = null

    private val _columnsCountPortrait: Int = 3
    private val _columnsCountLandscape: Int = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, container, false)
        gridView = view.findViewById(R.id.cards_grid);

        val cards = ArrayList<Int>()

        val button: Button = view.findViewById(R.id.add_button)
        button.setOnClickListener {
            val adapter = CardsAdapter(requireContext(), cards)
            adapter.addCard()
            gridView!!.adapter = adapter
        }

        return view
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridView!!.numColumns = _columnsCountLandscape
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridView!!.numColumns = _columnsCountPortrait
        }
    }
}