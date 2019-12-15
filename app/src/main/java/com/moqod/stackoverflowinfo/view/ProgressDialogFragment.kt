package com.moqod.stackoverflowinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.moqod.stackoverflowinfo.R
import com.moqod.stackoverflowinfo.databinding.ProgressDialogFragmentBinding

class ProgressDialogFragment : DialogFragment() {

    private var binding: ProgressDialogFragmentBinding? = null

    private val style = DialogFragment.STYLE_NO_TITLE
    private val themeRes = R.style.ProgressDialog

    companion object {
        fun newInstance(): ProgressDialogFragment {
            val frag = ProgressDialogFragment()
            frag.isCancelable = false
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(style, themeRes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.progress_dialog_fragment, container, false)
        return binding?.root
    }

}