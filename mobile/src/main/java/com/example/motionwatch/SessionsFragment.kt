package com.example.motionwatch

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * SessionsFragment:
 * Displays monthly totals for all detected activities
 * and generates a dynamic analytics graph.
 */
class SessionsFragment : Fragment() {

    // Month label
    private lateinit var tvMonth: TextView

    // Activity totals
    private lateinit var tvRunningTotal: TextView
    private lateinit var tvStandingTotal: TextView
    private lateinit var tvTremorTotal: TextView
    private lateinit var tvFallingTotal: TextView
    private lateinit var tvWalkingTotal: TextView
    private lateinit var tvJerkingTotal: TextView
    private lateinit var tvTonicTotal: TextView
    private lateinit var tvSittingTotal: TextView
    private lateinit var tvLiveTotal: TextView

    // Dynamic graph container
    private lateinit var graphContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_sessions, container, false)

        // Header
        tvMonth = view.findViewById(R.id.tvMonth)

        // Totals
        tvRunningTotal = view.findViewById(R.id.tvRunningTotal)
        tvStandingTotal = view.findViewById(R.id.tvStandingTotal)
        tvTremorTotal = view.findViewById(R.id.tvTremorTotal)
        tvFallingTotal = view.findViewById(R.id.tvFallingTotal)
        tvWalkingTotal = view.findViewById(R.id.tvWalkingTotal)
        tvJerkingTotal = view.findViewById(R.id.tvJerkingTotal)
        tvTonicTotal = view.findViewById(R.id.tvTonicTotal)
        tvSittingTotal = view.findViewById(R.id.tvSittingTotal)
        tvLiveTotal = view.findViewById(R.id.tvLiveTotal)

        // Graph
        graphContainer = view.findViewById(R.id.graphContainer)

        // Current month display
        val monthStr = SimpleDateFormat(
            "MMMM yyyy",
            Locale.US
        ).format(Calendar.getInstance().time)

        tvMonth.text = monthStr.uppercase(Locale.US)

        // Load session counts
        updateDetectionTotals()

        // History button
        view.findViewById<MaterialButton>(R.id.btnHistory).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, HistoryPageFragment())
                .addToBackStack("history")
                .commit()
        }

        return view
    }

    /**
     * Reads session CSV files and counts activities.
     */
    private fun updateDetectionTotals() {

        // Log directories
        val base =
            requireContext().getExternalFilesDir(null)
                ?: requireContext().filesDir

        val phoneDir = File(base, "logs")
        val watchDir = File(base, "logs/watch")

        // Merge files
        val allFiles = mutableListOf<File>()

        if (phoneDir.exists()) {
            allFiles += phoneDir.listFiles()?.toList().orEmpty()
        }

        if (watchDir.exists()) {
            allFiles += watchDir.listFiles()?.toList().orEmpty()
        }

        // Current month/year
        val currentCal = Calendar.getInstance()
        val currentMonth = currentCal.get(Calendar.MONTH)
        val currentYear = currentCal.get(Calendar.YEAR)

        // Activity counts
        val counts = mutableMapOf(
            "RUNNING" to 0,
            "STANDING" to 0,
            "TREMOR" to 0,
            "FALLING" to 0,
            "WALKING" to 0,
            "JERKING" to 0,
            "TONIC" to 0,
            "SITTING" to 0,
            "LIVE" to 0
        )

        // Process CSV files
        allFiles
            .filter {
                it.isFile &&
                        it.name.lowercase(Locale.US).endsWith(".csv")
            }
            .forEach { file ->

                val fileCal = Calendar.getInstance()
                fileCal.timeInMillis = file.lastModified()

                val sameMonth =
                    fileCal.get(Calendar.MONTH) == currentMonth

                val sameYear =
                    fileCal.get(Calendar.YEAR) == currentYear

                if (sameMonth && sameYear) {

                    val label =
                        extractLabel(file.name)
                            .uppercase(Locale.US)

                    if (counts.containsKey(label)) {
                        counts[label] = counts[label]!! + 1
                    }
                }
            }

        // Update totals
        tvRunningTotal.text = counts["RUNNING"].toString()
        tvStandingTotal.text = counts["STANDING"].toString()
        tvTremorTotal.text = counts["TREMOR"].toString()
        tvFallingTotal.text = counts["FALLING"].toString()
        tvWalkingTotal.text = counts["WALKING"].toString()
        tvJerkingTotal.text = counts["JERKING"].toString()
        tvTonicTotal.text = counts["TONIC"].toString()
        tvSittingTotal.text = counts["SITTING"].toString()
        tvLiveTotal.text = counts["LIVE"].toString()

        // Update analytics graph
        updateGraph(counts)
    }

    /**
     * Builds the dynamic horizontal activity graph.
     */
    private fun updateGraph(counts: Map<String, Int>) {

        graphContainer.removeAllViews()

        // Sort highest -> lowest
        val topThree = counts.entries
            .filter { it.value > 0 } // remove empty activities
            .sortedByDescending { it.value }
            .take(3) // ONLY top 3

        val maxValue =
            topThree.maxOfOrNull { it.value } ?: 1

        topThree.forEach { (label, value) ->

            // Row
            val row = LinearLayout(requireContext()).apply {

                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL

                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = dpToPx(14)
                }
            }

            // Activity label
            val labelView = TextView(requireContext()).apply {

                text = label

                textSize = 11f

                setTypeface(null, Typeface.BOLD)

                setTextColor(
                    Color.parseColor("#374151")
                )

                layoutParams = LinearLayout.LayoutParams(
                    dpToPx(82),
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            // Gray background track
            val barTrack = LinearLayout(requireContext()).apply {

                setBackgroundColor(
                    Color.parseColor("#E5E7EB")
                )

                layoutParams = LinearLayout.LayoutParams(
                    0,
                    dpToPx(14),
                    1f
                )
            }

            // Colored activity bar
            val barWidth =
                ((220f * value) / maxValue).toInt()

            val bar = View(requireContext()).apply {

                layoutParams = LinearLayout.LayoutParams(
                    dpToPx(barWidth),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )

                setBackgroundColor(getBarColor(label))
            }

            barTrack.addView(bar)

            // Count label
            val countView = TextView(requireContext()).apply {

                text = value.toString()

                textSize = 11f

                gravity = Gravity.CENTER

                setTypeface(null, Typeface.BOLD)

                setTextColor(
                    Color.parseColor("#111827")
                )

                layoutParams = LinearLayout.LayoutParams(
                    dpToPx(32),
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = dpToPx(10)
                }
            }

            row.addView(labelView)
            row.addView(barTrack)
            row.addView(countView)

            graphContainer.addView(row)
        }
    }

    /**
     * Returns a color for each activity.
     */
    private fun getBarColor(label: String): Int {

        return when (label) {

            "WALKING" ->
                Color.parseColor("#2EA77A")

            "RUNNING" ->
                Color.parseColor("#60A5FA")

            "STANDING" ->
                Color.parseColor("#F59E0B")

            "TREMOR" ->
                Color.parseColor("#EF4444")

            "FALLING" ->
                Color.parseColor("#8B5CF6")

            "JERKING" ->
                Color.parseColor("#EC4899")

            "TONIC" ->
                Color.parseColor("#14B8A6")

            "SITTING" ->
                Color.parseColor("#6366F1")

            else ->
                Color.GRAY
        }
    }

    /**
     * Extracts the activity label from the filename.
     */
    private fun extractLabel(filename: String): String {

        val parts = filename.split("_")

        return if (parts.size >= 5) {
            parts[3]
        } else {
            "UNKNOWN"
        }
    }

    /**
     * Converts dp to pixels.
     */
    private fun dpToPx(dp: Int): Int {

        return (
                dp * resources.displayMetrics.density
                ).toInt()
    }
}
