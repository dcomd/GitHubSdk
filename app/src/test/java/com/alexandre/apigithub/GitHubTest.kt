package com.alexandre.apigithub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexandre.apigithub.data.model.GitHubModel
import com.alexandre.apigithub.data.model.GitHubOwner
import com.alexandre.apigithub.data.repository.GitHubRepository
import com.alexandre.apigithub.data.response.GitHubBodyResponse
import com.alexandre.apigithub.data.response.GitHubItemsResponse
import com.alexandre.apigithub.data.response.GitHubOwnerResponse
import com.alexandre.apigithub.ui.viewMocel.ViewModelGitHubAp
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response


class GitHubTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: ViewModelGitHubAp
    private lateinit var getGit: GitHubRepository

    @Before
    fun init() {
        getGit = mock()
        viewModel = ViewModelGitHubAp(getGit)

    }


    @Test
    fun get() = runBlocking {


        val items = listOf(
            GitHubItemsResponse(
                name = "architecture-samples",
                stargazers_count = 10,
                forks_count = 10,
                owner = GitHubOwnerResponse(
                    login = "android",
                    avatar_url = "https://avatars3.githubusercontent.com/u/32689599?v=4"
                )
            )
        )
        val gitHubBodyResponse = GitHubBodyResponse(items)

        Mockito.`when`(getGit.execute()).thenReturn(Response.success(gitHubBodyResponse))
        val retorno = getGit.execute()

        if (retorno.isSuccessful) {
            val list: MutableList<GitHubModel> = arrayListOf()
            retorno.body()?.let { bodyResponse ->
                for (item in bodyResponse.items) {

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
            }

            viewModel.viewLiveDataList.postValue(list)
            viewModel.viewLiveDataList.observeForever { }
            assert(viewModel.viewLiveDataList != null)
        }
    }


}