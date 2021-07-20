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
import com.example.jobplanettest.db.CompanyInfoEntity
import com.example.jobplanettest.db.CompanyInfoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    var simpleList = arrayListOf<ComapnyListSimpleData>()
    var listAdapter : ComapnyListAdapter? = null


    // ViewModel 통지 받고 화면 갱신
//    var comapnyInfoCountObserver = Observer<Int> { count ->
//
//
//
//
//        if (count == 0) {
//            Log.d("BugFix", "Observer chk 1")
//            simpleList.clear()
//        }
//        else
//            if (simpleList.isEmpty() == true){
//            Log.d("BugFix", "Observer chk 2")
//            simpleList = arrayListOf<ComapnyListSimpleData>(
//                ComapnyListSimpleData("포두주", "음..."),
//                ComapnyListSimpleData("사과주", "달콤"),
//                ComapnyListSimpleData("딸기주", "상큼"),
//                ComapnyListSimpleData("당근", "오잉?"),
//                ComapnyListSimpleData("당근", "당근?"),
//                ComapnyListSimpleData("당근", "아삭아삭아..."),
//                ComapnyListSimpleData("당근", "으...."),
//                ComapnyListSimpleData("당근 으....", "아직도 당근"),
//            )
//
//            listAdapter?.setData(simpleList)
//            listAdapter?.notifyDataSetChanged()
////            binding.companyList.adapter?.
////            binding.companyList.adapter?.notifyDataSetChanged()
//        }
//
//        Log.d("BugFix", "Observer count : " )
//        showWidget()
//
//    };

    var companyInfoListObserver = Observer< Array<CompanyInfoEntity> > { arrayList ->
        simpleList.clear();
        for (entity in arrayList) {
            if (entity.name.isNullOrEmpty() == false) {
                simpleList.add(ComapnyListSimpleData(entity.name!!, "음...", totalAvg = entity.rateTotalAvg, industryName = entity.industryName!!))
            }
            else {
                simpleList.add(ComapnyListSimpleData(entity.cellType.toString(), "sss"))
            }
        }

        listAdapter?.setData(simpleList)
        listAdapter?.notifyDataSetChanged()

        showWidget()
    }

    fun showWidget() {
        if (simpleList.isEmpty() == true) {
            binding.emptyMessageLayout.visibility = View.VISIBLE;
            binding.companyList.visibility = View.GONE;
        }
        else {
            binding.emptyMessageLayout.visibility = View.GONE;
            binding.companyList.visibility = View.VISIBLE;
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        CoroutineScope(Dispatchers.IO).launch {
            val companyInfoRepo = CompanyInfoRepo()
            companyInfoRepo.clearData(requireContext())
        }.start()


        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        //firstViewModel = ViewModelProvider(this).get(firstViewModel::class.java)
//        firstViewModel.getCount(requireContext()).observe(viewLifecycleOwner, comapnyInfoCountObserver)
        firstViewModel.getListAll(requireContext()).observe(viewLifecycleOwner, companyInfoListObserver)



//        signInViewModel.getCount(requireContext()).observe(viewLifecycleOwner, accountCountObserver)


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ComapnyListAdapter(requireContext(), simpleList)
        binding.companyList.adapter = listAdapter
        binding.companyList.layoutManager = LinearLayoutManager(requireContext())
        binding.companyList.setHasFixedSize(true)

        showWidget()

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