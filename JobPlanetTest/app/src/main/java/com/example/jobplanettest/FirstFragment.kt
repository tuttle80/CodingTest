package com.example.jobplanettest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        firstViewModel.getListAll(requireContext()).observe(viewLifecycleOwner, companyInfoListObserver)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}