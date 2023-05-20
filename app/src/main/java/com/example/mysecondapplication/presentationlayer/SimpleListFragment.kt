package com.example.mysecondapplication.presentationlayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecondapplication.businesslayer.CoroutineItemProvider
import com.example.mysecondapplication.datalayer.IItemAccessor2
import com.example.mysecondapplication.datalayer.RetrofitProvider
import com.example.mysecondapplication.presentationlayer.adapters.ItemAdapter
import com.example.mysecondapplication.R

class SimpleListFragment : Fragment() {
    private val provider by lazy { initializeProvider() }
    private val itemAdapter = ItemAdapter()

    private val accessor = RetrofitProvider().provide().create(IItemAccessor2::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
            itemAdapter.context = requireContext()
            adapter = itemAdapter
        }

        provider.load {
            itemAdapter.submitList(it)
        }
    }

    private fun initializeProvider(): CoroutineItemProvider {
        return CoroutineItemProvider(accessor)
    }


    companion object {
        protected const val COLUMN_COUNT = 2

        fun newInstance(): SimpleListFragment {
            return SimpleListFragment()
        }
    }
}