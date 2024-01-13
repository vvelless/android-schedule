package com.example.schedule.presentation.services.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.Day
import com.example.schedule.data.Week
import com.example.schedule.databinding.DailyScheduleItemFragmentBinding

class WeekAdapter : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {
    private lateinit var week: Week
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DailyScheduleItemFragmentBinding.inflate(inflater, parent, false)
        return WeekViewHolder(binding)
    }

    override fun getItemCount(): Int = week.days.size

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(week.days[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitWeek(week: Week) {
        this.week = week
        notifyDataSetChanged()
    }

    class WeekViewHolder(
        private val binding: DailyScheduleItemFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Day) = with(binding) {
            val dayAdapter = DayAdapter()
            dayTitle.text = data.weekDay.value
            dailyLessonRecycler.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = dayAdapter
                dayAdapter.submitList(data.lessons)
            }
        }
    }
}