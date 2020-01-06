package com.example.footballleaguemvp.ui.favoriteteamlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R

class FavoriteTeamFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteTeamFragment {
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }


}
