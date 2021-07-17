package com.example.jobplanettest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobplanettest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // ViewModel
    private lateinit var firstViewModel: FirstViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var simpleList = arrayListOf<ComapnyListSimpleData>(
        ComapnyListSimpleData("포두주", "음..."),
        ComapnyListSimpleData("사과주", "달콤"),
        ComapnyListSimpleData("딸기주", "상큼"),
        ComapnyListSimpleData("당근", "오잉?"),
        ComapnyListSimpleData("당근", "당근?"),
        ComapnyListSimpleData("당근", "아삭아삭아..."),
        ComapnyListSimpleData("당근", "으...."),
        ComapnyListSimpleData("당근 으....", "아직도 당근"),
    )



    // ViewModel 통지 받고 화면 갱신
    var comapnyInfoCountObserver = Observer<Int> { count ->

        Log.d("BugFix", "Observer count : " + count)

//        view?.let { view ->
//            if (0 < count) {
//                view.findViewById<ConstraintLayout>(R.id.verificationOKLayout).visibility = View.VISIBLE
//                view.findViewById<LinearLayoutCompat>(R.id.verificationLayout).visibility = View.GONE
//            }
//            else {
//                view.findViewById<ConstraintLayout>(R.id.verificationOKLayout).visibility = View.GONE
//                view.findViewById<LinearLayoutCompat>(R.id.verificationLayout).visibility = View.VISIBLE
//            }
//        }
    };


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        //firstViewModel = ViewModelProvider(this).get(firstViewModel::class.java)
        firstViewModel.getCount(requireContext()).observe(viewLifecycleOwner, comapnyInfoCountObserver)
//        signInViewModel.getCount(requireContext()).observe(viewLifecycleOwner, accountCountObserver)


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.companyList.adapter = ComapnyListAdapter(requireContext(), simpleList)
        binding.companyList.layoutManager = LinearLayoutManager(requireContext())
        binding.companyList.setHasFixedSize(true)

//        simpleRecyclerView.adapter = HomeListAdapter(requireContext(), simpleList)
//        simpleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        simpleRecyclerView.setHasFixedSize(true)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}