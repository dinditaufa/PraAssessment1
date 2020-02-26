package org.d3if4131.praassessment1


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3if4131.praassessment1.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {

    companion object {
        var KEYLUAS = "luas"
        var KEYKELS = "keli"
        var A = 0
        var T = 0
        var KelS = 0
        var LuaS = 0
        var PT = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(inflater, R.layout.fragment_segitiga, container, false)

        if ( savedInstanceState != null ){
            LuaS = savedInstanceState.getInt(KEYLUAS)
            KelS = savedInstanceState.getInt(KEYKELS)
            binding.apply {
                tvLuasS.setText(LuaS.toString())
                tvKelS.setText(KelS.toString())
            }
        }

        binding.btnHitungS.setOnClickListener {
            if (edAlasS.text.isEmpty() && edTinggiS.text.isEmpty()) {
                Toast.makeText(context, "Harus Diisi !!", Toast.LENGTH_LONG).show()
            } else {
                A = edAlasS.text.toString().toInt()
                T = edTinggiS.text.toString().toInt()
                LuaS = ((A*T)/2)
                val phyta = Math.sqrt((A.toDouble()*A.toDouble()+(T*T)))
                KelS = (A+T+phyta.toInt())
                PT = phyta.toInt()
                tvLuasS.setText(LuaS.toString())
                tvKelS.setText(KelS.toString())
            }
        }

        binding.btnShareS.setOnClickListener {
            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plaint")
                .setText(getString(R.string.share_S, A, T, PT, LuaS, KelS))
                .intent
            try {
                startActivity(shareIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(context, "Share Gagal !!", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEYLUAS, LuaS)
        outState.putInt(KEYKELS, KelS)
        super.onSaveInstanceState(outState)
    }

}
