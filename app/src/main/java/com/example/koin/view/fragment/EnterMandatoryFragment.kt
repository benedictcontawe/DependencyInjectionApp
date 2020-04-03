package com.example.koin.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.koin.R
import kotlinx.android.synthetic.main.fragment_create_account.*
import org.koin.core.KoinComponent
import java.io.File

var onFileReceived: ((path: File?) -> Unit)? = null
var onSetMandatoryFailed: (() -> Unit)? = null

class EnterMandatoryFragment : Fragment(), KoinComponent {

    private var file: File? = null
    val TAG = EnterMandatoryFragment::class.java.simpleName
    private var originalMode: Int? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //android:inputType="textNoSuggestions|textVisiblePassword"
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPause() {
        super.onPause()
        file = null
    }

    private fun setUpView() {
        tvAddPhoto.visibility = View.VISIBLE
        tv_edt.visibility = View.INVISIBLE
        edittext_first_name.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_NAVIGATE_NEXT) {
                edittext_last_name.requestFocus()
            }
            false
        })
        edittext_first_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!edittext_first_name.text.isNullOrEmpty()) {
                    val firstname = edittext_first_name.text.toString()
                    Log.e(TAG,"signUpActivity.enableNextButton()")
                } else {
                    if (edittext_last_name.text.isNullOrEmpty() && edittext_first_name.text.isNullOrEmpty()) {
                        Log.e(TAG,"signUpActivity.disableNextButton()")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    Log.e(TAG,"signUpActivity.enableNextButton()")

                }
            }
        })
        edittext_last_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!edittext_last_name.text.isNullOrEmpty()) {
                    val lastname = edittext_last_name.text.toString()
                    Log.e(TAG,"signUpActivity.enableNextButton()")
                } else {
                    if (edittext_last_name.text.isNullOrEmpty() && edittext_first_name.text.isNullOrEmpty()) {
                        Log.e(TAG,"signUpActivity.disableNextButton()")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    Log.e(TAG,"signUpActivity.enableNextButton()")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        fun newInstance(): EnterMandatoryFragment {
            return EnterMandatoryFragment()
        }
    }
}