package com.example.sdkgit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.sdkgit.R
import com.example.sdkgit.data.adapter.GitHubAdapter
import com.example.sdkgit.ui.viewMocel.ViewModelGitHubAp
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val gitHubViewModel: ViewModelGitHubAp by viewModel()
    private lateinit var adapter: GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGitDate()
        getObservables()

        btnatualizar.setOnClickListener{
            getGitDateDb()

        }

    }


    fun getGitDate(){
        repository_progress.visibility = View.VISIBLE
        gitHubViewModel.getSelect(this)
    }

    fun getGitDateDb(){
        repository_progress.visibility = View.VISIBLE
        gitHubViewModel.getSelectAll(this)

        gitHubViewModel.viewLiveDataList.observe(this, Observer {
            adapter = GitHubAdapter(it)
            adapter.update(it)
        })
        repository_progress.visibility = View.GONE

    }

    fun getObservables() {
        gitHubViewModel.viewLiveDataList.observe(this, Observer {

            it?.let { gitHub ->

                with(recicleGit) {
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        this@MainActivity,
                        androidx.recyclerview.widget.RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = GitHubAdapter(gitHub)
                    repository_progress.visibility = View.GONE
                }
            } ?: run {
                Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
                repository_progress.visibility = View.GONE
            }
        })


        gitHubViewModel.viewLiveDataErrorGet.observe(this, Observer {
            Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
        })

    }
}
