package com.example.footballleaguemvp.ui.matchschedule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.footballleaguemvp.R
import kotlinx.android.synthetic.main.fragment_match_list.*


class MatchScheduleFragment : Fragment(),
    MatchScheduleContract.View {

    private var tabtype  = "belum"

    companion object {
        const val TAG_MATCH_TYPE = "match_type"

        fun newInstance(type: String): MatchScheduleFragment {
            val bundle = Bundle()
            bundle.putString(TAG_MATCH_TYPE, type)

            val fragment =
                MatchScheduleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null) {
            tabtype = bundle.getString(TAG_MATCH_TYPE, "belum")
        }
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun setupUi() {
        textView.text = tabtype
    }

    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {

    }


}
