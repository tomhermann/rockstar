package com.zombietank.rockstar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

private const val LABEL_KEY: String = "label"

class SimpleLabelFragment : DaggerFragment() {

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
        fun newInstance(label: Int): SimpleLabelFragment {
            val fragment = SimpleLabelFragment()
            val bundle = Bundle()
            bundle.putInt(LABEL_KEY, label)
            fragment.arguments = bundle
            return fragment
        }
    }
}
