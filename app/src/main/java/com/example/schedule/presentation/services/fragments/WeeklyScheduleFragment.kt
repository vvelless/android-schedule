package com.example.schedule.presentation.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedule.databinding.WeekScheduleFragmentBinding
import com.example.schedule.presentation.services.ScheduleService
import com.example.schedule.presentation.services.adapters.WeekAdapter

class WeeklyScheduleFragment : Fragment() {
    private var binding: WeekScheduleFragmentBinding? = null
    private var adapter: WeekAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WeekAdapter()

        val weekSchedule = ScheduleService.getWeekNow()

        binding?.weeklyLessonRecycler?.adapter = adapter

        adapter?.submitWeek(weekSchedule)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeekScheduleFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeeklyScheduleFragment()
    }
}