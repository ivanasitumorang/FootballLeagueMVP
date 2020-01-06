package com.example.footballleaguemvp.ui.leagueclassement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Classement
import com.example.footballleaguemvp.network.AppNetworkServiceProvider
import com.example.footballleaguemvp.network.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_league_classement.*


class LeagueClassementFragment : Fragment(), LeagueClassementContract.View {

    private lateinit var leagueClassementPresenter: LeagueClassementPresenter
    private var leagueId = ""

    companion object {
        private const val TAG_LEAGUE_ID = "league_id"

        fun newInstance(leagueId: String): LeagueClassementFragment {
            val bundle = Bundle()
            bundle.putString(TAG_LEAGUE_ID, leagueId)

            val fragment =
                LeagueClassementFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null){
            leagueId = bundle.getString(TAG_LEAGUE_ID, "")
        }
        return inflater.inflate(R.layout.fragment_league_classement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        initializeData()
    }

    override fun setupUi() {
        setupPresenter()
    }

    override fun setupPresenter() {
        leagueClassementPresenter = LeagueClassementPresenter(this, AppSchedulerProvider(), AppNetworkServiceProvider())
    }

    override fun initializeData() {
        leagueClassementPresenter.getClassements(leagueId)
    }

    override fun populateData(classements: List<Classement>) {
        if (classements.isNullOrEmpty()){
            showNoData()
        } else {
            val classementAdapter = LeagueClassementAdapter(classements)
            rvClassement.adapter = classementAdapter
        }
    }

    override fun showLoadingIndicator() {
        ivClassementLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivClassementLoadingIndicator.visibility = View.GONE
    }

    override fun showNoData() {
        layoutNoData.visibility = View.VISIBLE
        rvClassement.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        leagueClassementPresenter.mDisposable.dispose()
    }

}
