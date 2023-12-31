import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import com.example.madcamp_week1_tab1_try.CustomAdapter
import com.example.madcamp_week1_tab1_try.R


class FragmentD : Fragment() {

    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_d, container, false)

        val list: ListView = view.findViewById(R.id.listView)

        customAdapter = CustomAdapter(requireContext())
        list.adapter = customAdapter

        val plusButton: AppCompatImageButton = view.findViewById(R.id.plus_button)
        plusButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.custom_dialog)

            val name: EditText = dialog.findViewById(R.id.name) // 수정
            val num: EditText = dialog.findViewById(R.id.num)   // 수정

            val closeButton: Button = dialog.findViewById(R.id.btnAdd)
            closeButton.setOnClickListener {
                customAdapter.addItem(name.text.toString(), num.text.toString())
                dialog.dismiss()
            }
            dialog.show()
        }


        list.setOnItemLongClickListener { parent, view, position, id ->
            customAdapter.removeItem(position)
            false
        }

        return view
    }
//    private fun showCustomDialog() {
//        val dialog = Dialog(requireContext())
//        dialog.setContentView(R.layout.custom_dialog)
//
//        val name: EditText = view.findViewById(R.id.name)
//        val num: EditText = view.findViewById(R.id.num)
//
//        val closeButton: Button = dialog.findViewById(R.id.btnAdd)
//        closeButton.setOnClickListener {
//            customAdapter.addItem(name.text.toString(), num.text.toString())
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
}
