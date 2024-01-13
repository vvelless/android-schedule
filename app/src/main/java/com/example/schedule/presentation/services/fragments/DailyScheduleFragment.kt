package com.example.schedule.presentation.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedule.databinding.DayScheduleFragmentBinding
import com.example.schedule.presentation.services.ScheduleService
import com.example.schedule.presentation.services.adapters.DayAdapter

class DailyScheduleFragment : Fragment() {
    private var binding: DayScheduleFragmentBinding? = null
    private var adapter: DayAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DayAdapter(true)

        val daySchedule = ScheduleService.getDayNow()

        binding?.dayLessonRecycler?.adapter = adapter
        binding?.dayTitle?.text = daySchedule.weekDay.value

        adapter?.submitList(daySchedule.lessons)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DayScheduleFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DailyScheduleFragment()
    }
}