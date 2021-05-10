package com.example.sdkgit.data.repository


import android.content.Context
import com.example.sdkgit.data.RetrofitInstanceGit
import com.example.sdkgit.data.db.AppDatabase
import com.example.sdkgit.data.model.GitHubModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository {
    suspend fun execute()= withContext(Dispatchers.IO){
        val api = RetrofitInstanceGit.getRetrofit()
        return@withContext api.search()
    }
    suspend fun excuteInsert(gitHubModel: List<GitHubModel>, context: Context) =
        withContext(Dispatchers.IO) {
            var mDb: AppDatabase? = null

            mDb = AppDatabase.getInstance(context)

            val factoryDb = FactoryModel()

            val retornoList = factoryDb.factory(gitHubModel)
            for(item in retornoList)
            mDb?.gitHubDao()?.insert(item)

        }

    suspend fun excuteDelete(context: Context) =
        withContext(Dispatchers.IO) {
            var mDb: AppDatabase? = null

            mDb = AppDatabase.getInstance(context)
            mDb?.gitHubDao()?.deleteAll()
        }

    suspend fun excuteSelect(context: Context) =
        withContext(Dispatchers.IO) {
            var mDb: AppDatabase? = null

            mDb = AppDatabase.getInstance(context)
          return@withContext  mDb?.gitHubDao()?.selectAll()
        }
}