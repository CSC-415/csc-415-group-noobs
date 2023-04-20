package com.example.myapp.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentProgressBinding
import com.example.myapp.adapter.ProgressAdapter
import com.example.myapp.viewmodel.ProgressViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressAdapter: ProgressAdapter
    private lateinit var lineChart: LineChart

    private val viewModel: ProgressViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressAdapter = ProgressAdapter()

//        setIMAGE - not dynamic
        Glide
            .with(binding.root)
            .load(R.drawable.pro_pic)
            .error(R.drawable.baseline_bar_chart_24)
            .into(binding.profilePic)

//        setName - not dynamic
        binding.name.text = "John Smith"

//        show number of tasks and pomodoro completed
        binding.tasksButton.text = "${viewModel.getTotalTasks()}\nTasks"
        binding.pomodoroButton.text = "${viewModel.getTotalPomodoros()}\nPomodoros"

//        Initialize graph with random data
        lineChart = binding.lineChart
        setChartData(requireView(), viewModel.getDefaultTasksCompletedData(), getString(R.string.tasks_completed))

        setupClickListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setChartData(view: View, data: List<Entry>, label: String) {
        val mpLineChart = binding.lineChart
        val lineDataSet = LineDataSet(data, label)

        styleLineDataSet(lineDataSet)

        val lineData = LineData(lineDataSet)
        mpLineChart.apply {
            this.data = lineData

            styleChart(this)

            animateY(2000)
        }
    }
    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
//        color = ContextCompat.getColor(context, R.color.off_white)
//        valueTextColor = ContextCompat.getColor(context, R.color.black_75)
        setDrawValues(true)
        lineWidth = 3f
        isHighlightEnabled = true
        setDrawHighlightIndicators(true)
        setDrawCircles(true)
        mode = LineDataSet.Mode.LINEAR

        setDrawFilled(true)
        fillDrawable = context?.let { ContextCompat.getDrawable(it, R.drawable.bg_spark_line) }
    }
    fun styleChart(lineChart: LineChart) = lineChart.apply {

        setBackgroundColor(resources.getColor(R.color.white))

        axisRight.isEnabled = false
        axisLeft.apply {
            axisMinimum = 0f
        }

        xAxis.apply {
            isEnabled = false
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(false)
            setDrawAxisLine(false)
            position = XAxis.XAxisPosition.BOTTOM

            typeface = ResourcesCompat.getFont(context, R.font.barlow_semi_condensed_regular)
            textColor = ContextCompat.getColor(context, R.color.teal_700)
        }

        setTouchEnabled(true)
        isDragEnabled = true
        setScaleEnabled(false)
        setPinchZoom(false)
        description = null
    }


    private fun setupObservers() {
        viewModel.tasksCompletedData.observe(viewLifecycleOwner) { data ->
            setChartData(requireView(), data, getString(R.string.tasks_completed))
            lineChart.invalidate()
        }

        viewModel.pomodorosCompletedData.observe(viewLifecycleOwner) { data ->
            setChartData(requireView(), data, getString(R.string.pomodoros_completed))
            lineChart.invalidate()
        }
    }

    private fun setupClickListeners() {
        binding.tasksButton.setOnClickListener {
            viewModel.setTasksCompletedData()
            progressAdapter.setData(10, 0)
        }

        binding.pomodoroButton.setOnClickListener {
            viewModel.setPomodorosCompletedData()
            progressAdapter.setData(0, 5)
        }
    }
}
