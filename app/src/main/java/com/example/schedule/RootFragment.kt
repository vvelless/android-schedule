package com.example.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedule.databinding.RootFragmentBinding
import com.example.schedule.presentation.services.fragments.DailyScheduleFragment
import com.example.schedule.presentation.services.fragments.WeeklyScheduleFragment

class RootFragment : Fragment() {
    private var binding: RootFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RootFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.bottomNavView?.setOnItemSelectedListener {
            selectTab(it.itemId)
            true
        }

        selectTab(R.id.navigation_week_schedule)
    }

    private fun selectTab(itemId: Int) {
        val navHost = binding?.bottomNavHost?.id ?: return
        val transaction = childFragmentManager.beginTransaction()
        when (itemId) {
            R.id.navigation_week_schedule -> transaction.replace(
                navHost,
                WeeklyScheduleFragment.newInstance()
            )

            R.id.navigation_day_schedule -> transaction.replace(
                navHost,
                DailyScheduleFragment.newInstance()
            )
        }
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RootFragment()
    }
}