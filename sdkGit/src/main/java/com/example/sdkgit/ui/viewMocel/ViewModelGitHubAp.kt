package com.example.sdkgit.ui.viewMocel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdkgit.data.model.GitHubModel
import com.example.sdkgit.data.model.GitHubOwner
import com.example.sdkgit.data.repository.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ViewModelGitHubAp(val gitrepository: GitHubRepository) : ViewModel() {

    val viewLiveDataList: MutableLiveData<MutableList<GitHubModel>> = MutableLiveData()
    private val errorGet: MutableLiveData<HttpException> = MutableLiveData()
    val viewLiveDataErrorGet: LiveData<HttpException> = errorGet

    fun getSelect(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retorno = gitrepository.execute()

                if(retorno.isSuccessful){
                     val list: MutableList<GitHubModel> = mutableListOf()
                     retorno.body()?.let { bodyResponse ->
                         for(item in bodyResponse.items){

                             val gitModel = GitHubModel(

                                 name = item.name,
                                 full_name = item.full_name,
                                 stargazers_count = item.stargazers_count,
                                 forks_count = item.forks_count,
                                 gitHubOwner = GitHubOwner(
                                     login = item.owner.login,
                                     avatar_url = item.owner.avatar_url
                                 )


                             )

                             list.add(gitModel)

                         }

                         gitrepository.excuteDelete(context)
                         gitrepository.excuteInsert(list,context)

                         viewLiveDataList.postValue(list)
                     }?:kotlin.run {
                         viewLiveDataList.postValue(null)
                     }


                }else
                {
                    viewLiveDataList.postValue(null)
                }

            } catch (e: HttpException) {
                errorGet.postValue(e)
            }
        }

    }


    fun getSelectAll(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retorno = gitrepository.excuteSelect(context)

                if(retorno != null) {
                    val list: MutableList<GitHubModel> = mutableListOf()


                    for (item in retorno) {

                        val gitModel = GitHubModel(

                            name = item.name,
                            full_name = item.full_name,
                            stargazers_count = item.stargazers_count,
                            forks_count = item.forks_count,
                            gitHubOwner = GitHubOwner(
                                login = item.login,
                                avatar_url = item.avatar_url
                            )


                        )

                        list.add(gitModel)

                    }

                    viewLiveDataList.postValue(list)
                }

            } catch (e: HttpException) {
                errorGet.postValue(e)
            }
        }

    }

}