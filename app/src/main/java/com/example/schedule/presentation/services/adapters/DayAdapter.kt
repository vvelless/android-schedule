package com.example.schedule.presentation.services.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R

import com.example.schedule.data.Lesson
import com.example.schedule.databinding.LessonItemScheduleFragmentBinding
import java.util.Date

class DayAdapter(
    private val isShowLessonNow: Boolean = false
) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    private val lessons = mutableListOf<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayAdapter.DayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonItemScheduleFragmentBinding.inflate(inflater, parent, false)
        return when (viewType) {
            DAY_VIEW_TYPE -> DayViewHolderImpl(binding)
            DAY_WITH_HIGHLIGHT_VIEW_TYPE -> DayWithHighlightViewHolder(binding)
            else -> throw NotImplementedError()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowLessonNow) {
            DAY_WITH_HIGHLIGHT_VIEW_TYPE
        } else {
            DAY_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int = lessons.size

    override fun onBindViewHolder(holder: DayAdapter.DayViewHolder, position: Int) {
        holder.bind(lessons[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Lesson>) {
        this.lessons.addAll(list)
        notifyDataSetChanged()
    }

    abstract inner class DayViewHolder(
        protected val binding: LessonItemScheduleFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(data: Lesson)
    }

    inner class DayViewHolderImpl(
        binding: LessonItemScheduleFragmentBinding
    ) : DayViewHolder(binding) {
        override fun bind(data: Lesson) = with(binding) {
            startTime.text = "${data.startTime.hours}:${data.startTime.minutes}"
            endTime.text = "${data.endTime.hours}:${data.endTime.minutes}"
            numberClass.text = "Аудитория: ${data.classroom}"
            lessonName.text = data.name
            lessonProfessor.text = "Преподаватель: ${data.professor}"
        }
    }

    inner class DayWithHighlightViewHolder(
        binding: LessonItemScheduleFragmentBinding
    ) : DayViewHolder(binding) {
        override fun bind(data: Lesson) = with(binding) {

            startTime.text = "${data.startTime.hours}:${data.startTime.minutes}"
            endTime.text = "${data.endTime.hours}:${data.endTime.minutes}"
            numberClass.text = "Аудитория: ${data.classroom}"
            lessonName.text = data.name
            lessonProfessor.text = "Преподаватель: ${data.professor}"
        }
    }

    private fun Lesson.isNow(): Boolean {
        val now = Date()
        return now.before(this.endTime) && now.after(this.startTime)
    }

    companion object {
        private const val DAY_WITH_HIGHLIGHT_VIEW_TYPE = 1
        private const val DAY_VIEW_TYPE = 2
    }
}