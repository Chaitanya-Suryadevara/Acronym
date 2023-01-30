package com.android.acromine.ui.longform

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.acromine.R
import com.android.acromine.data.Status
import com.android.acromine.data.pojo.LongFormResponse
import com.android.acromine.databinding.ActivityLongformBinding
import com.android.acromine.ui.listview.CustomResultItemAdapter


class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName
    private lateinit var longFormViewModel: LongFormViewModel
    private lateinit var binding: ActivityLongformBinding
    private var shortFormStr: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLongformBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shortForm = binding.etShortForm
        val rvResultContainer = binding.rvResultContainer
        rvResultContainer.layoutManager = LinearLayoutManager(this)
        val btSearchLongForm = binding.btSearchLongForm

        btSearchLongForm.setOnClickListener(clickListener)

        longFormViewModel = ViewModelProvider(this, LongFormViewModelFactory())
            .get(LongFormViewModel::class.java)

        shortForm.afterTextChanged {
            btSearchLongForm.isEnabled = it.isNotEmpty()
            shortFormStr = it
        }
    }

    /**
     * to perform action based on the search button click after the input is entered
     * Based on the results from search response , SUCCESS, ERROR, LOADING screen will be displayed to user
     */
    private val clickListener = View.OnClickListener { view ->
        when (view?.id) {
            R.id.bt_search_long_form -> {
                hideKeyboard()
                Log.d(TAG, "onClick() called with: view = $view shortForm = $shortFormStr")
                longFormViewModel.getLongFormRetrofit(shortForm = shortFormStr).observe(this) {
                    Log.d(TAG, "getLongFormRetrofit(): Success() $it")
                    when (it.state) {
                        Status.SUCCESS -> {
                            Log.d(TAG, "getLongFormRetrofit(): Success() ${it.data}")
                            hideLoading()
                            it.data?.let { it1 -> showResult(it1) }
                        }
                        Status.ERROR -> {
                            Log.d(TAG, "getLongFormRetrofit(): Error() ${it.exception}")
                            hideLoading()
                            it.exception?.let { it1 -> showFailure(it1) }
                        }
                        Status.LOADING -> {
                            Log.d(TAG, "getLongFormRetrofit(): Loading() ${it.progress}")
                            showLoading()
                        }
                        else -> {
                            Log.d(TAG, "getLongFormRetrofit(): None state")
                        }
                    }
                }
            }
        }
    }

    /**
     * To show failures in a way of toast message to user based on the error type
     * @param exception : different error scenarios
     */
    private fun showFailure(exception: Exception) {
        Log.d(TAG, "showFailure() called with: exception = $exception")
        exception.printStackTrace()
        binding.rvResultContainer.visibility = View.GONE
        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Method is to show results to the view
     * @param longFormResponses list of responses received based on the input
     */
    private fun showResult(longFormResponses: List<LongFormResponse>) {
        Log.d(TAG, "showResult() called with: longFormResponses = $longFormResponses")
        val data = longFormResponses[0].lfs
        binding.rvResultContainer.visibility = View.VISIBLE
        binding.rvResultContainer.adapter = CustomResultItemAdapter(data)
    }

    /**
     * to show the loading image
     */
    private fun showLoading() {
        Log.d(TAG, "showLoading() called")
        binding.loading.visibility = View.VISIBLE
    }

    /**
     * to hide the loading image
     */
    private fun hideLoading() {
        Log.d(TAG, "hideLoading() called")
        binding.loading.visibility = View.GONE
    }

    /**
     * to hide the keyboard on the search button click.
     */
    @SuppressLint("SuspiciousIndentation")
    private fun hideKeyboard() {
        Log.d(TAG, "hideKeyboard() called")
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).getWindowToken(), 0)
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}