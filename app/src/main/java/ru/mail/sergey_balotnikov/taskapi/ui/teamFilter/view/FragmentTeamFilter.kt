package ru.mail.sergey_balotnikov.taskapi.ui.teamFilter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_filter.view.*
import ru.mail.sergey_balotnikov.taskapi.R
import ru.mail.sergey_balotnikov.taskapi.ui.teamList.model.Team
import ru.mail.sergey_balotnikov.taskapi.ui.Router as Router

class FragmentTeamFilter : Fragment() {
    companion object {
        fun newInstance() =
            FragmentTeamFilter()
    }

    private lateinit var checkDivision: RadioGroup
    private lateinit var checkConference: RadioGroup
    private lateinit var cancel: Button
    private lateinit var apply: Button
    private lateinit var router: Router
    private lateinit var filter: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = activity as Router
        filter = router.getFilter().copy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDivision = view.rgDivision
        checkConference = view.rgConference
        cancel = view.btnCancel
        cancel.setOnClickListener { cancel() }
        apply = view.btnApply
        apply.setOnClickListener { applyFilter() }

    }

    override fun onResume() {
        super.onResume()
        initFilter()
    }

    private fun initFilter() {

        if (filter.teamDivision != "") {
            for (rb in getArrayFromRadioGroup(checkDivision)) {
                if (rb.text.toString().toLowerCase() == filter.teamDivision.toLowerCase()) {
                    rb.isChecked = true
                }
            }
        } else {
            checkDivision.rbNoDivision.isChecked = true
        }
        if (filter.teamConference != "") {
            for (rb in getArrayFromRadioGroup(checkConference)) {
                if (rb.text.toString().toLowerCase() == filter.teamConference.toLowerCase()) {
                    rb.isChecked = true
                }
            }
        } else {
            checkConference.rbNoConference.isChecked = true
        }


    }

    private fun applyFilter() {
        onSaveFilter()
        router.updateFilter(filter)
        activity?.supportFragmentManager?.popBackStack()

    }

    private fun onSaveFilter() {
        for (rb in getArrayFromRadioGroup(checkDivision)) {
            if (rb.isChecked) {
                if (rb.text.toString().toLowerCase() == this.getString(R.string.none).toLowerCase()) {
                    filter.teamDivision = ""
                } else {
                    filter.teamDivision = rb.text.toString()
                }
            }
        }
        for (rb in getArrayFromRadioGroup(checkConference)) {
            if (rb.isChecked) {
                if (rb.text.toString().toLowerCase() == this.getString(R.string.none).toLowerCase()) {
                    filter.teamConference = ""
                } else {
                    filter.teamConference = rb.text.toString()
                }
            }
        }
    }

    override fun onPause() {
        onSaveFilter()
        super.onPause()
    }

    private fun cancel() {
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun getArrayFromRadioGroup(group: RadioGroup): List<RadioButton> {
        val buttons = ArrayList<RadioButton>()
        val count = group.childCount
        for (i in 0 until count) {
            if (group[i] is RadioButton) {
                buttons.add(group[i] as RadioButton)
            }
        }
        return buttons
    }

}