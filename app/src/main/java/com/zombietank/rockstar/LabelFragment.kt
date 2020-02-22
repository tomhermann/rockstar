package com.zombietank.rockstar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
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
        fun newInstance(@StringRes label: Int) = LabelFragment().apply {
            arguments = bundleOf(LABEL_KEY to label)
        }
    }
}
