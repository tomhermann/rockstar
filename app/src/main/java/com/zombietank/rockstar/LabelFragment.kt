package com.zombietank.rockstar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dashboard.*

private const val LABEL_KEY: String = "label"

class LabelFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(LABEL_KEY)?.let { labelKey ->
            label.text = getString(labelKey)
        }
    }

    companion object {
        fun newInstance(label: Int): LabelFragment {
            val fragment = LabelFragment()
            val bundle = Bundle()
            bundle.putInt(LABEL_KEY, label)
            fragment.arguments = bundle
            return fragment
        }
    }
}
