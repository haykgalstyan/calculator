package galstyan.hayk.calculator.ui.util

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import galstyan.hayk.calculator.R


fun FragmentActivity.push(fragment: Fragment, addToBackStack: Boolean = false) =
    supportFragmentManager.commit {
        if (addToBackStack) addToBackStack(null)
        replace(R.id.fragment_container_view, fragment)
    }

fun Fragment.push(fragment: Fragment) = requireActivity().push(fragment, true)

fun Fragment.pop() = requireActivity().onBackPressed()

fun Fragment.toast(text: String) = Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
