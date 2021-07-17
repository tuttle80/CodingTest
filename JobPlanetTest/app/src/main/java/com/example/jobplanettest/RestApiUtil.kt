package com.example.jobplanettest

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import com.example.jobplanettest.db.CompanyInfoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestApiUtil {
    fun RefreshCompanyInfo(context: Context) {
        /* 새로운 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        CoroutineScope(Dispatchers.IO).launch {

            val companyInfoRepo = CompanyInfoRepo()



            companyInfoRepo.refreshRawData(context, "New Json data")

//            val accountRepo = AccountRepo()
//            val ret = accountRepo.isValidAccount(context, editEmail.text.toString(), editPassword.text.toString())
//
//            if (!ret) {
//                rootView.findViewById<AppCompatTextView>(R.id.signInMessage).text = requireContext().getText(R.string.error_sign_in_invalid_user)
//            }
///                Log.d("BugFix", "Account Ret = " + ret)
        }.start()
    }
}