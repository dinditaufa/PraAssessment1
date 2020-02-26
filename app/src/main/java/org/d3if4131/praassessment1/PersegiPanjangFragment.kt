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
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import org.d3if4131.praassessment1.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {

    companion object {
        var KEYLUAPP = "luas"
        var KEYKELPP = "keli"
        var P = 0
        var L = 0
        var KelP = 0
        var LuaP = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragme
        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater, R.layout.fragment_persegi_panjang, container, false)

        if ( savedInstanceState != null ) {
            LuaP = savedInstanceState.getInt(KEYLUAPP)
            KelP = savedInstanceState.getInt(KEYKELPP)
            binding.apply {
                tvLuasPp.setText(LuaP.toString())
                tvKelPp.setText(KelP.toString())
            }
        }

        binding.btnHitungPp.setOnClickListener {
            if (edPanjangPp.text.isEmpty() && edLebarPp.text.isEmpty()) {
                Toast.makeText(context, "Harus Diisi !!", Toast.LENGTH_LONG).show()
            } else {
                P = edPanjangPp.text.toString().toInt()
                L = edLebarPp.text.toString().toInt()
                LuaP = (P * L)
                KelP = (P + P + L + L)
                tvLuasPp.setText(LuaP.toString())
                tvKelPp.setText(KelP.toString())
            }
        }

        binding.btnSharePp.setOnClickListener {
            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plaint")
                .setText(getString(R.string.share_PP, P, L, LuaP, KelP))
                .intent
            try {
                startActivity(shareIntent)
            }catch (ex: ActivityNotFoundException) {
                Toast.makeText(context, "Share Gagal!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEYLUAPP, LuaP)
        outState.putInt(KEYKELPP, KelP)
        super.onSaveInstanceState(outState)
    }
}
